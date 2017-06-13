package com.luthfihariz.newsreader.data.source.remote;

import android.content.Context;

import com.luthfihariz.newsreader.data.Article;
import com.luthfihariz.newsreader.data.ArticleResponse;
import com.luthfihariz.newsreader.data.Source;
import com.luthfihariz.newsreader.data.SourceResponse;
import com.luthfihariz.newsreader.data.source.NewsDataSource;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * Created by luthfihariz on 5/20/17.
 */

public class RemoteDataSource implements NewsDataSource {

    private static RemoteDataSource sInstance = null;
    private RetrofitEndpointService mApiService;

    private RemoteDataSource(Context context) {
        mApiService = RetrofitHelper.getInstance(context).provideRetrofit()
                .create(RetrofitEndpointService.class);
    }

    public static RemoteDataSource getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new RemoteDataSource(context);
        }

        return sInstance;
    }

    @Override
    public Observable<List<Article>> getArticles(String sources) {
        return mApiService.getArticles(sources)
                .map(ArticleResponse::getArticles);
    }

    @Override
    public Observable<List<Article>> getArticles() {
        return mApiService.getArticles(null)
                .map(ArticleResponse::getArticles);
    }

    @Override
    public Observable<List<Source>> getSources() {
        return mApiService.getSources().map(SourceResponse::getSources);
    }

    @Override
    public Observable<Void> saveUserSelectedSources(List<Source> selectedSources) {
        throw new RuntimeException("Function not implemented");
    }

    @Override
    public Observable<List<Source>> getUserSelectedSources() {
        throw new RuntimeException("Function not implemented");
    }

    @Override
    public Observable<Boolean> isSelectedSourceEmpty() {
        throw new RuntimeException("Function not implemented");
    }
}
