package com.example.sleeptracker.sleepTracker;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sleeptracker.R;
import com.example.sleeptracker.database.SleepNight;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SleepNightAdapter extends RecyclerView.Adapter<SleepNightAdapter.NightHolder> {
    private List<SleepNight> sleepNights = new ArrayList<>();
    private int size=0;
    @NonNull
    @Override
    public NightHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards,parent,false);
        return new NightHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NightHolder holder, int position) {
        SleepNight current = sleepNights.get(position);
        holder.StartTime.setText(String.valueOf(current.startmillisecs));
        holder.EndTime.setText(String.valueOf(current.endmillisecs));

    }
    public void setSize(int s)
    {
        size=s;

    }

    @Override
    public int getItemCount() {
        return size;
    }
    public void setNights(List<SleepNight> nights)
    {
        sleepNights=nights;
        notifyDataSetChanged();
    }

    class NightHolder extends RecyclerView.ViewHolder{
       private TextView StartTime;
       private TextView EndTime;

       public NightHolder(@NonNull View itemView) {
           super(itemView);
           StartTime=itemView.findViewById(R.id.Start_Time);
           EndTime=itemView.findViewById(R.id.EndTime);
       }
   }
}
