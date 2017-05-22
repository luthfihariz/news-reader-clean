package com.luthfihariz.newsreader;

import android.content.Context;
import android.support.annotation.NonNull;

import com.luthfihariz.newsreader.data.source.NewsRepository;
import com.luthfihariz.newsreader.data.source.local.LocalDataSource;
import com.luthfihariz.newsreader.data.source.remote.RemoteDataSource;
import com.luthfihariz.newsreader.util.schedulers.BaseSchedulerProvider;
import com.luthfihariz.newsreader.util.schedulers.SchedulerProvider;

/**
 * Created by luthfihariz on 5/22/17.
 */

public class Injection {
    public static NewsRepository provideRepository(@NonNull Context context) {
        return NewsRepository.getInstance(LocalDataSource.getInstance(context.getApplicationContext()),
                RemoteDataSource.getInstance(context.getApplicationContext()));
    }

    public static BaseSchedulerProvider provideScheduler(){
        return SchedulerProvider.getInstance();
    }
}
