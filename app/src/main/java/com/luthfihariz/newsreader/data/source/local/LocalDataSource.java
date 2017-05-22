package com.luthfihariz.newsreader.data.source.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.luthfihariz.newsreader.data.Article;
import com.luthfihariz.newsreader.data.Source;
import com.luthfihariz.newsreader.data.source.NewsDataSource;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by luthfihariz on 5/20/17.
 */

public class LocalDataSource implements NewsDataSource {

    private static LocalDataSource sInstance = null;

    private LocalDataSource(Context context) {
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
    public Observable<List<Article>> getArticles(String sources, String sortBy) {
        return null;
    }

    @Override
    public Observable<List<Source>> getSources() {
        return null;
    }
}
