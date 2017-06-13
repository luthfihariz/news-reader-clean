package com.luthfihariz.newsreader.browser;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.luthfihariz.newsreader.R;
import com.luthfihariz.newsreader.databinding.ActivityNewsBrowserBinding;

/**
 * Created by luthfihariz on 6/13/17.
 */

public class NewsBrowserActivity extends AppCompatActivity implements NewsBrowserContract.View {

    public static final String KEY_URL = "url";
    private NewsBrowserContract.Presenter mPresenter;

    ActivityNewsBrowserBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_browser);
        setupWebView();
    }

    private void setupWebView(){
        String url = getIntent().getStringExtra(KEY_URL);
        mBinding.wvNewsBrowser.loadUrl(url);
    }

    @Override
    public void setPresenter(NewsBrowserContract.Presenter presenter) {
        mPresenter = presenter;
    }


    public static void intent(Context context, String url) {
        Intent intent = new Intent(context, NewsBrowserActivity.class);
        intent.putExtra(KEY_URL, url);
        context.startActivity(intent);
    }
}
