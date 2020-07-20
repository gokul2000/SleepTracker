package com.example.sleeptracker.sleepTracker;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.sleeptracker.database.SleepDataBase;
import com.example.sleeptracker.database.SleepDataBaseDao;
import com.example.sleeptracker.database.SleepNight;

import java.util.List;

public class SleepNigthRepository {
    private SleepDataBaseDao sleepDataBaseDao;
    private LiveData<List<SleepNight>> allnights;
    public SleepNigthRepository(Application application)
    {
        SleepDataBase sleepDataBase = SleepDataBase.getInstance(application);
        sleepDataBaseDao=sleepDataBase.sleepDataBaseDao();
        allnights=sleepDataBaseDao.getAllNights();
    }
    public void insert(SleepNight night)
    {
        new InsertNight(sleepDataBaseDao).execute(night);

    }
    public void update(SleepNight night)
    {
        new UpdateNight(sleepDataBaseDao).execute(night);

    }
    public void clear()
    {
        new DeleteAllNights(sleepDataBaseDao).execute();

    }

    public LiveData<List<SleepNight>> getAllnights() {
        return allnights;
    }

    private static class InsertNight extends AsyncTask<SleepNight,Void,Void>
    {
        private SleepDataBaseDao sleepDataBaseDao;
        private InsertNight(SleepDataBaseDao sleepDataBaseDao){
            this.sleepDataBaseDao=sleepDataBaseDao;
        }
        @Override
        protected Void doInBackground(SleepNight... sleepNights) {
            sleepDataBaseDao.insert(sleepNights[0]);
            return null;
        }
    }
    private static class UpdateNight extends AsyncTask<SleepNight,Void,Void>
    {
        private SleepDataBaseDao sleepDataBaseDao;
        private UpdateNight(SleepDataBaseDao sleepDataBaseDao){
            this.sleepDataBaseDao=sleepDataBaseDao;
        }
        @Override
        protected Void doInBackground(SleepNight... sleepNights) {
            sleepDataBaseDao.update(sleepNights[0]);
            return null;
        }
    }
    private static class DeleteAllNights extends AsyncTask<Void,Void,Void>
    {
        private SleepDataBaseDao sleepDataBaseDao;
        private DeleteAllNights(SleepDataBaseDao sleepDataBaseDao){
           this.sleepDataBaseDao=sleepDataBaseDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            sleepDataBaseDao.clear();
            return null;
        }
    }
}
