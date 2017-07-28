package com.luthfihariz.newsreader.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.luthfihariz.newsreader.BaseBindingViewHolder;
import com.luthfihariz.newsreader.Injection;
import com.luthfihariz.newsreader.R;
import com.luthfihariz.newsreader.browser.NewsBrowserActivity;
import com.luthfihariz.newsreader.data.Article;
import com.luthfihariz.newsreader.databinding.ActivityMainBinding;
import com.luthfihariz.newsreader.sourcepicker.SourcePickerActivity;
import com.luthfihariz.newsreader.util.LogHandler;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainContract.View {

    private ActivityMainBinding mBinding;
    private MainContract.Presenter mPresenter;
    private ArticleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.rvNews.setLayoutManager(new LinearLayoutManager(this));

        // presenter initialization
        mPresenter = new MainPresenter(
                Injection.provideRepository(getApplicationContext()),
                Injection.provideScheduler());
        mPresenter.bind(this);

        // ask presenter if any source is picked
        mPresenter.isUserPickAnySource();

        mBinding.srlNews.setOnRefreshListener(() -> {
            mPresenter.refresh();
        });
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showArticles(List<Article> articles) {
        if (mAdapter == null) {
            mAdapter = new ArticleAdapter(this, articles, v -> {
                BaseBindingViewHolder holder = (BaseBindingViewHolder) v.getTag();
                Article article = articles.get(holder.getAdapterPosition());
                NewsBrowserActivity.intent(this, article.getUrl(), article.getTitle());
            });
            mBinding.rvNews.setAdapter(mAdapter);
        } else {
            mAdapter.updateList(articles);
        }
    }

    @Override
    public void goToSourcePicker() {
        finish();
        SourcePickerActivity.intent(this);
    }

    @Override
    public void showErrorView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }


    @Override
    public void hideProgress() {
        mBinding.srlNews.setRefreshing(false);
    }

    @Override
    public void showProgress() {
        mBinding.srlNews.setRefreshing(true);
    }

    public static void intent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_pick_channel) {
            goToSourcePicker();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
