package com.luthfihariz.newsreader.data.source.remote;

import com.luthfihariz.newsreader.data.ArticleResponse;
import com.luthfihariz.newsreader.data.SourceResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by luthfihariz on 5/21/17.
 */

public interface RetrofitEndpointService {

    String apiVersion = "v1";

    @GET(apiVersion + "/articles")
    Observable<ArticleResponse> getArticles(@Query("source") String source,
                                            @Query("sortBy") String sortBy);

    @GET(apiVersion + "/sources")
    Observable<SourceResponse> getSources(@Query("category") String category,
                                          @Query("language") String language,
                                          @Query("country") String country);
}
