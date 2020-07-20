package com.example.sleeptracker;

import android.content.res.Resources;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;

import androidx.core.text.HtmlCompat;

import com.example.sleeptracker.database.SleepNight;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class Util {

    public static String convertnumerictoString(int quality ,Resources resources)
    {
        String QualityString = resources.getString(R.string.three_ok);
        switch (quality){
            case -1:
                QualityString="__";
                break;
            case 0:
                QualityString=resources.getString(R.string.zero_very_bad);
                break;
            case 1:
                QualityString=resources.getString(R.string.one_poor);
                break;
            case 2:
                QualityString=resources.getString(R.string.two_soso);
                break;
            case 4:
                QualityString=resources.getString(R.string.four_pretty_good);
                break;
            case 5:
                QualityString=resources.getString(R.string.five_excellent);
                break;


        }
        return QualityString;



    }
    public static String convertLongToDateString(long Systemtime)
    {
        return new SimpleDateFormat("EEE MMM-dd-yyyy 'Time:HH:mm", Locale.US).format(Systemtime);
    }
    public static Spanned formatNights(List<SleepNight> nightList, Resources resources)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(resources.getString(R.string.title));
        for(SleepNight night:nightList)
        {
            sb.append("<br").append(resources.getString(R.string.title)).append("\t"+convertLongToDateString(night.startmillisecs)+"<br>");
            if(night.endmillisecs!=night.startmillisecs)
            {
                sb.append("\t"+convertLongToDateString(night.endmillisecs)+"<br>");
            }
            sb.append(resources.getString(R.string.quality)).append("\t"+convertnumerictoString(night.sleepQuality,resources)+"<br>")
                    .append(resources.getString(R.string.hours_slept)+"<br>")
            .append("\t"+" "+((night.endmillisecs-night.startmillisecs)/1000/60/60)+":")
            .append(((night.endmillisecs-night.startmillisecs)/1000/60)+" "+":")
                    .append(((night.endmillisecs-night.startmillisecs)/1000)+"<br><br>");

        }
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
            return Html.fromHtml(sb.toString(),Html.FROM_HTML_MODE_LEGACY);
        else
            return HtmlCompat.fromHtml(sb.toString(),HtmlCompat.FROM_HTML_MODE_LEGACY);


    }



}
