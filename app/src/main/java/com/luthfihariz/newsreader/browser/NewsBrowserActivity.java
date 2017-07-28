package com.luthfihariz.newsreader.browser;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.luthfihariz.newsreader.R;
import com.luthfihariz.newsreader.databinding.ActivityNewsBrowserBinding;

/**
 * Created by luthfihariz on 6/13/17.
 */

public class NewsBrowserActivity extends AppCompatActivity implements NewsBrowserContract.View {

    public static final String KEY_URL = "url";
    public static final String KEY_NEWS_TITLE = "newsTitle";

    private NewsBrowserContract.Presenter mPresenter;
    private ActivityNewsBrowserBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_browser);

        String url = getIntent().getStringExtra(KEY_URL);
        String title = getIntent().getStringExtra(KEY_NEWS_TITLE);
        String subtitle = Uri.parse(url).getHost();
        setupToolbar(title, subtitle);
        setupWebView(url);
    }

    private void setupToolbar(String title, String subtitle) {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setSubtitle(subtitle);
        }
    }

    private void setupWebView(String url) {
        mBinding.wvNewsBrowser.setWebViewClient(new BrowserWebViewClient());
        mBinding.wvNewsBrowser.loadUrl(url);

        mBinding.srlNewsBrowser.setOnRefreshListener(() -> {
            mBinding.wvNewsBrowser.loadUrl(url);
        });
    }

    @Override
    public void setPresenter(NewsBrowserContract.Presenter presenter) {
        mPresenter = presenter;
    }


    public static void intent(Context context, String url, String newsTitle) {
        Intent intent = new Intent(context, NewsBrowserActivity.class);
        intent.putExtra(KEY_URL, url);
        intent.putExtra(KEY_NEWS_TITLE, newsTitle);
        context.startActivity(intent);
    }

    private class BrowserWebViewClient extends WebViewClient {

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mBinding.srlNewsBrowser.setRefreshing(true);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mBinding.srlNewsBrowser.setRefreshing(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
