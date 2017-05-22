package com.luthfihariz.newsreader.main;

import com.luthfihariz.newsreader.data.Article;
import com.luthfihariz.newsreader.data.source.NewsRepository;
import com.luthfihariz.newsreader.util.schedulers.BaseSchedulerProvider;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * Created by luthfihariz on 5/20/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private NewsRepository mRepository;
    private MainContract.View mView;
    private BaseSchedulerProvider mProvider;

    public MainPresenter(NewsRepository repository, BaseSchedulerProvider schedulerProvider) {
        mRepository = repository;
        mProvider = schedulerProvider;
    }

    @Override
    public void bind(MainContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void unbind() {
        mView.setPresenter(null);
        mView = null;
    }

    @Override
    public void getArticles() {
        mRepository.getArticles("techcrunch")
                .subscribeOn(mProvider.io())
                .observeOn(mProvider.ui())
                .subscribe(new Observer<List<Article>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Article> articles) {
                        mView.showArticles(articles);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}