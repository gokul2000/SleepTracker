package com.example.sleeptracker.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SleepDataBaseDao {

    @Insert
    void insert(SleepNight night);

    @Update
    void update(SleepNight night);
    @Query("SELECT * from daily_sleep_quality_table WHERE nightId=:key")
    SleepNight get(long key);
    @Query("DELETE FROM daily_sleep_quality_table")
    void clear();
    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC")
    LiveData<List<SleepNight>> getAllNights();
    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
    SleepNight getTonight();

}
