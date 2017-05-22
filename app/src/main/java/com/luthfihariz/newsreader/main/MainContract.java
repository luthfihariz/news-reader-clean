package com.luthfihariz.newsreader.main;

import com.luthfihariz.newsreader.BasePresenter;
import com.luthfihariz.newsreader.BaseView;
import com.luthfihariz.newsreader.data.Article;

import java.util.List;

/**
 * Created by luthfihariz on 5/20/17.
 */

public class MainContract {
    interface Presenter extends BasePresenter<View> {
        void getArticles();
    }

    interface View extends BaseView<Presenter> {
        void showArticles(List<Article> articles);

        void showErrorView();
    }
}
