package com.example.sleeptracker;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.sleeptracker.database.SleepDataBase;
import com.example.sleeptracker.database.SleepDataBaseDao;
import com.example.sleeptracker.database.SleepNight;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private SleepDataBaseDao sleepDataBaseDao;
    private SleepDataBase db;
    @Before
    public void createDb()
    {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db= Room.inMemoryDatabaseBuilder(context,SleepDataBase.class).allowMainThreadQueries().build();
        sleepDataBaseDao=db.sleepDataBaseDao();

    }
    @After
    public void closeDb()
    {
        db.close();
    }
    @Test
    public void insertAndGetNight() {
        // Context of the app under test.
        SleepNight night=new SleepNight();
        sleepDataBaseDao.insert(night);
        SleepNight tonight=sleepDataBaseDao.getTonight();

        assertEquals(tonight.sleepQuality,-1);
    }
}