package com.luthfihariz.newsreader.data.source;

import com.luthfihariz.newsreader.data.Article;
import com.luthfihariz.newsreader.data.Source;
import com.luthfihariz.newsreader.data.source.local.LocalDataSource;
import com.luthfihariz.newsreader.data.source.remote.RemoteDataSource;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static io.reactivex.Observable.fromIterable;


/**
 * Created by luthfihariz on 5/21/17.
 */

public class NewsRepository implements NewsDataSource {

    private LocalDataSource mLocalDataSource;
    private RemoteDataSource mRemoteDataSource;

    private static NewsRepository sInstance;

    private NewsRepository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static NewsRepository getInstance(LocalDataSource localDataSource,
                                             RemoteDataSource remoteDataSource) {
        if (sInstance == null) {
            sInstance = new NewsRepository(localDataSource, remoteDataSource);
        }
        return sInstance;
    }


    public Observable<List<Article>> getArticles() {
        Observable<List<Source>> userSourcesObservable = getUserSelectedSources();

        return userSourcesObservable.flatMap(sources -> Observable.fromIterable(sources)
                .flatMap(source -> mRemoteDataSource.getArticles(source.getId())));
    }

    @Override
    public Observable<List<Article>> getArticles(String sources) {
        return mRemoteDataSource.getArticles(sources);
    }

    @Override
    public Observable<List<Source>> getSources() {
        return mRemoteDataSource.getSources();
    }

    @Override
    public Observable<Void> saveUserSelectedSources(List<Source> sources) {
        return mLocalDataSource.saveUserSelectedSources(sources);
    }

    @Override
    public Observable<List<Source>> getUserSelectedSources() {
        return mLocalDataSource.getUserSelectedSources();
    }

    @Override
    public Observable<Boolean> isSelectedSourceEmpty() {
        return mLocalDataSource.isSelectedSourceEmpty();
    }
}
