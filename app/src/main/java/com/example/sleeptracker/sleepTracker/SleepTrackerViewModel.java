package com.example.sleeptracker.sleepTracker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sleeptracker.database.SleepNight;

import java.util.List;

import static java.util.Objects.requireNonNull;


public class SleepTrackerViewModel extends AndroidViewModel {
    private SleepNigthRepository sleepNigthRepository;
    private LiveData<List<SleepNight>> allnights;

    public SleepTrackerViewModel(@NonNull Application application) {
        super(application);
        sleepNigthRepository=new SleepNigthRepository(application);
        allnights=sleepNigthRepository.getAllnights();
    }
    public void insert(SleepNight night)
    {
        sleepNigthRepository.insert(night);
    }
    public void update(SleepNight night)
    {
        sleepNigthRepository.update(night);
    }
    public void deleteAll()
    {
        sleepNigthRepository.clear();
    }
    public LiveData<List<SleepNight>> getAllnights()
    {
        return allnights;
    }
}







