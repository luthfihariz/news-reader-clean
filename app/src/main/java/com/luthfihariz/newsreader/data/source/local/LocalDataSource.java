package com.luthfihariz.newsreader.data.source.local;

import android.content.Context;

import com.luthfihariz.newsreader.data.Article;
import com.luthfihariz.newsreader.data.Source;
import com.luthfihariz.newsreader.data.source.NewsDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by luthfihariz on 5/20/17.
 */

public class LocalDataSource implements NewsDataSource {

    private static LocalDataSource sInstance = null;
    private AppRoomDatabase mRoomDb;

    private LocalDataSource(Context context) {
        mRoomDb = AppRoomDatabase.getInstance(context);
    }

    public static LocalDataSource getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new LocalDataSource(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public Observable<List<Article>> getArticles(String sources) {
        return null;
    }

    @Override
    public Observable<List<Article>> getArticles() {
        return null;
    }

    @Override
    public Observable<List<Source>> getSources() {
        return null;
    }

    @Override
    public Observable<Void> saveUserSelectedSources(final List<Source> sources) {
        return Completable.fromAction(() -> {
            mRoomDb.getSourceDao().flushTable();
            mRoomDb.getSourceDao().insertAll(sources);
        }).toObservable();
    }

    @Override
    public Observable<List<Source>> getUserSelectedSources() {
        return Observable.fromPublisher(mRoomDb.getSourceDao().getSelectedSources());
    }

    @Override
    public Observable<Boolean> isSelectedSourceEmpty() {
        Observable<Integer> countObservable = Observable.fromPublisher(mRoomDb.getSourceDao().count());
        return countObservable.flatMap(new Function<Integer, Observable<Boolean>>() {
            @Override
            public Observable<Boolean> apply(@NonNull Integer integer) throws Exception {
                return Observable.just(integer == 0);
            }
        });
    }

    @Override
    public Observable<Integer> getUserSelectedSourceSize() {
        return Observable.fromPublisher(mRoomDb.getSourceDao().count());
    }
}
