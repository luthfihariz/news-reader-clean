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

    Observable<List<Article>> getArticles(String sources, String sortBy);

    Observable<List<Source>> getSources();
}
