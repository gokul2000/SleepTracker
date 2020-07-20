package com.example.sleeptracker.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities ={SleepNight.class},version = 1,exportSchema = false)
public abstract  class SleepDataBase extends RoomDatabase {
    public abstract SleepDataBaseDao sleepDataBaseDao();
    private static  SleepDataBase instance = null;
    public static synchronized SleepDataBase getInstance(Context context)
    {

        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(),SleepDataBase.class,"sleep_history_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }


        return instance;
    }
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Populate(instance).execute();

        }
    };
    private static class Populate extends AsyncTask<Void,Void,Void>
    {
        private SleepDataBaseDao sleepDataBaseDao;
        private Populate(SleepDataBase sleepDataBase)
        {
            sleepDataBaseDao = sleepDataBase.sleepDataBaseDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            sleepDataBaseDao.insert(new SleepNight());


            return null;
        }
    }
}
