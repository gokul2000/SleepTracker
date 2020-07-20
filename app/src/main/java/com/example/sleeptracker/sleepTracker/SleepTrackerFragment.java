package com.example.sleeptracker.sleepTracker;

import android.app.Application;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sleeptracker.R;
import com.example.sleeptracker.database.SleepDataBase;
import com.example.sleeptracker.database.SleepDataBaseDao;
import com.example.sleeptracker.database.SleepNight;
import com.example.sleeptracker.databinding.FragmentSleepTrackerBinding;

import java.util.List;
import java.util.Objects;


public class SleepTrackerFragment extends Fragment {

    private SleepTrackerViewModel sleepTrackerViewModel;
    public SleepTrackerFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final FragmentSleepTrackerBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sleep_tracker,container,false);
        Application application = this.requireActivity().getApplication();
        SleepDataBaseDao dataSource = SleepDataBase.getInstance(application).sleepDataBaseDao();
        sleepTrackerViewModel= new ViewModelProvider(this).get(SleepTrackerViewModel.class);
        RecyclerView recyclerView = binding.RecycleView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(false);

        final SleepNightAdapter adapter = new SleepNightAdapter();
        recyclerView.setAdapter(adapter);
        final int i=0;
        sleepTrackerViewModel.getAllnights().observe(getViewLifecycleOwner(), new Observer<List<SleepNight>>() {
            @Override
            public void onChanged(List<SleepNight> sleepNights) {
                adapter.setNights(sleepNights);
                adapter.setSize(sleepNights.size());
                Toast.makeText(getParentFragment().getContext(),"Changed",Toast.LENGTH_SHORT).show();
            }
        });
        binding.setLifecycleOwner(this);
        binding.setSleeptrackviewmodel(sleepTrackerViewModel);


        return binding.getRoot();
    }
}