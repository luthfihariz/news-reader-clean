package com.luthfihariz.newsreader.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.luthfihariz.newsreader.data.Source;

import org.reactivestreams.Publisher;

import java.util.List;


/**
 * Created by luthfihariz on 6/12/17.
 */

@Dao
public interface SourceDao {
    @Query("SELECT * FROM selected_source")
    Publisher<List<Source>> getSelectedSources();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Source> sources);

    @Query("DELETE FROM selected_source")
    void flushTable();

    @Query("SELECT COUNT(*) FROM selected_source")
    Publisher<Integer> count();
}
