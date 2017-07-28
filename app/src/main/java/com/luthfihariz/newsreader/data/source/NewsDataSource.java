package com.luthfihariz.newsreader.data.source;

import com.luthfihariz.newsreader.data.Article;
import com.luthfihariz.newsreader.data.Source;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by luthfihariz on 5/20/17.
 */

public interface NewsDataSource {

    Observable<List<Article>> getArticles(String sources);

    Observable<List<Article>> getArticles();

    Observable<List<Source>> getSources();

    Observable<Void> saveUserSelectedSources(List<Source> selectedSources);

    Observable<List<Source>> getUserSelectedSources();

    Observable<Boolean> isSelectedSourceEmpty();

    Observable<Integer> getUserSelectedSourceSize();
}
