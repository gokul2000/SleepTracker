package com.example.sleeptracker.sleepQuality;

import android.app.Application;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sleeptracker.R;
import com.example.sleeptracker.databinding.FragmentSleepQualityBinding;

import java.util.Objects;


public class SleepQualityFragment extends Fragment {



    public SleepQualityFragment() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentSleepQualityBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sleep_quality,container,false);
        Application application = Objects.requireNonNull(this).getActivity().getApplication();

        return binding.getRoot();
    }
}