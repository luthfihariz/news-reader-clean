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
        return getArticles(sources, null);
    }

    @Override
    public Observable<List<Article>> getArticles(String sources, String sortBy) {
        return mApiService.getArticles(sources, sortBy)
                .map(new Function<ArticleResponse, List<Article>>() {
                    @Override
                    public List<Article> apply(@NonNull ArticleResponse articleResponse) throws Exception {
                        return articleResponse.getArticles();
                    }
                });
    }

    @Override
    public Observable<List<Source>> getSources() {
        return mApiService.getSources(null, null, null)
                .map(new Function<SourceResponse, List<Source>>() {
                    @Override
                    public List<Source> apply(@NonNull SourceResponse sourceResponse) throws Exception {
                        return sourceResponse.getSources();
                    }
                });
    }
}
