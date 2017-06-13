package com.luthfihariz.newsreader.data.source.local;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.luthfihariz.newsreader.data.Source;

/**
 * Created by luthfihariz on 6/12/17.
 */
@Database(entities = {Source.class}, version = 1)
public abstract class AppRoomDatabase extends RoomDatabase {

    private static AppRoomDatabase sInstance;

    public abstract SourceDao getSourceDao();

    public static AppRoomDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                    AppRoomDatabase.class, "news-reader-database").build();
        }

        return sInstance;
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    public static void destroyInstance() {
        sInstance = null;
    }
}
