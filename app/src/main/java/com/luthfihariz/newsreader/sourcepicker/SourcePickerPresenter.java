package com.luthfihariz.newsreader.sourcepicker;

import com.luthfihariz.newsreader.data.Source;
import com.luthfihariz.newsreader.data.source.NewsRepository;
import com.luthfihariz.newsreader.util.schedulers.BaseSchedulerProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;

/**
 * Created by luthfihariz on 5/22/17.
 */

public class SourcePickerPresenter implements SourcePickerContract.Presenter {

    private NewsRepository mRepository;
    private BaseSchedulerProvider mScheduler;
    private SourcePickerContract.View mView;

    public SourcePickerPresenter(NewsRepository repository, BaseSchedulerProvider schedulerProvider) {
        mRepository = repository;
        mScheduler = schedulerProvider;
    }

    @Override
    public void bind(SourcePickerContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void unbind() {
        mView.setPresenter(null);
        mView = null;
    }

    @Override
    public void getSources() {
        mView.showProgressBar();
        mRepository.getSources()
                .subscribeOn(mScheduler.io())
                .observeOn(mScheduler.ui())
                .subscribe(new Observer<List<Source>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Source> sources) {
                        if (mView != null) {
                            mView.hideProgressBar();
                            mView.showSources(sources);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView != null) {
                            mView.hideProgressBar();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void saveSelectedSources(List<Source> selectedSources) {
        mRepository.saveUserSelectedSources(selectedSources)
                .subscribeOn(mScheduler.io())
                .observeOn(mScheduler.ui())
                .doOnNext(aVoid -> updateView())
                .doOnError(throwable -> mView.showErrorView())
                .doOnComplete(this::updateView)
                .subscribe();
    }

    private void updateView() {
        if (mView != null) {
            mView.goToNewsReader();
        }

    }

}
