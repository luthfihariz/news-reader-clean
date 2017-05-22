package com.luthfihariz.newsreader.data.source;

import com.luthfihariz.newsreader.data.Article;
import com.luthfihariz.newsreader.data.Source;
import com.luthfihariz.newsreader.data.source.local.LocalDataSource;
import com.luthfihariz.newsreader.data.source.remote.RemoteDataSource;

import java.util.List;

import io.reactivex.Observable;


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

    @Override
    public Observable<List<Article>> getArticles(String sources) {
        return mRemoteDataSource.getArticles(sources);
    }

    @Override
    public Observable<List<Article>> getArticles(String sources, String sortBy) {
        return mRemoteDataSource.getArticles(sources, sortBy);
    }

    @Override
    public Observable<List<Source>> getSources() {
        return mRemoteDataSource.getSources();
    }
}
