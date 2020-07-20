package com.example.sleeptracker.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "daily_sleep_quality_table")
public class SleepNight {
    public SleepNight(){

    }
    @PrimaryKey(autoGenerate = true)
    public long  nightId=0L;
    @ColumnInfo(name = "start_time_milli")
    public long startmillisecs=System.currentTimeMillis();
    @ColumnInfo(name = "end_time_milli")
    public long endmillisecs=System.currentTimeMillis();
    @ColumnInfo(name = "quality_rating")
    public int sleepQuality=-1;

}
