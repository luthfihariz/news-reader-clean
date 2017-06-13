package com.luthfihariz.newsreader.data.source.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.luthfihariz.newsreader.BuildConfig;
import com.luthfihariz.newsreader.util.LogHandler;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;

/**
 * Created by luthfihariz on 10/23/16.
 */
public class RetrofitHelper {

    private static final String HTTP_TAG = "ApiLog";
    private static final String CACHE_CONTROL = "Cache-Control";
    private static final String HTTP_CACHE = "http-cache";
    private static final int CACHE_SIZE = 10 * 1024 * 2014;

    private Context mContext;
    private static RetrofitHelper sInstance = null;

    private RetrofitHelper(@NonNull Context context) {
        mContext = context.getApplicationContext();
    }

    static RetrofitHelper getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new RetrofitHelper(context);
        }

        return sInstance;
    }

    Retrofit provideRetrofit() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .addInterceptor(provideParamInterceptor())
                .addInterceptor(provideHttpLoggingInterceptor())
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);

        Gson gson = new GsonBuilder().setLenient().serializeNulls().create();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        LogHandler.logDebug(HTTP_TAG, message);
                    }
                });
        httpLoggingInterceptor.setLevel(HEADERS);
        return httpLoggingInterceptor;
    }

    private Interceptor provideParamInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("apiKey", BuildConfig.API_KEY)
                        .build();

                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }
}
