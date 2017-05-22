package com.luthfihariz.newsreader.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.luthfihariz.newsreader.Injection;
import com.luthfihariz.newsreader.R;
import com.luthfihariz.newsreader.data.Article;
import com.luthfihariz.newsreader.databinding.ActivityMainBinding;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainContract.View {

    private ActivityMainBinding mBinding;
    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mPresenter = new MainPresenter(
                Injection.provideRepository(this),
                Injection.provideScheduler());
        mPresenter.bind(this);
        mPresenter.getArticles();

        mBinding.rvNews.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showArticles(List<Article> articles) {
        ArticleAdapter adapter = new ArticleAdapter(this, articles);
        mBinding.rvNews.setAdapter(adapter);
    }

    @Override
    public void showErrorView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }
}
