package com.example.ens_tryouts_project.Schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.ens_tryouts_project.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ScheduleActivity extends AppCompatActivity implements ScheduleAdapter.OnItemClickListener {

    List<String> daysOfTheWeek = new ArrayList<>();
    List<Integer> idOfTheDaysArray = new ArrayList<>();

    /*
    *   PROBLEM: SINCE THE API SENDS THE INFORMATION IN ENGLISH AND CODE USES THE ENGLISH VERSION COMPARISON IN THE NEXT ACTIVITY,
    *   USING TURKISH (OR ANY OTHER) LOCALE MAKES THE IF CHECKS ALL FALSE AND IT RESULTS IN NO CLASSES IN THE NEXT ACTIVITY.
    *
    *   WORKAROUNDS:
    *
    *   1) SEND THEM ALL BY HAND. THERE ARE 7 DAYS AND THEY WON'T CHANGE. EXTRA WORK BUT RELIABLE.
    *   2) WHAT I DID. STORE THE IDs IN A SEPARATE ARRAY AND CONVERT TO ENGLISH TEXT ALWAYS.
    *
    *   ALSO, I ADDED THE TURKISH TEXT SO THAT I CAN DISPLAY IT IN THE HEADER. IT IS USED WHEN NECESSARY IN THE NEXT ACTIVITY.
    *
    *
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        //add the days
        daysOfTheWeek.add(getString(R.string.monday));
        daysOfTheWeek.add(getString(R.string.tuesday));
        daysOfTheWeek.add(getString(R.string.wednesday));
        daysOfTheWeek.add(getString(R.string.thursday));
        daysOfTheWeek.add(getString(R.string.friday));
        daysOfTheWeek.add(getString(R.string.saturday));
        daysOfTheWeek.add(getString(R.string.sunday));

        //add the id of the days' string
        idOfTheDaysArray.add(R.string.monday);
        idOfTheDaysArray.add(R.string.tuesday);
        idOfTheDaysArray.add(R.string.wednesday);
        idOfTheDaysArray.add(R.string.thursday);
        idOfTheDaysArray.add(R.string.friday);
        idOfTheDaysArray.add(R.string.saturday);
        idOfTheDaysArray.add(R.string.sunday);

        WearableRecyclerView wearableRecyclerView = findViewById(R.id.wear_detail_recycler_view);
        // To align the edge children (first and last) with the center of the screen
        wearableRecyclerView.setEdgeItemsCenteringEnabled(true);
        wearableRecyclerView.setLayoutManager(new WearableLinearLayoutManager(ScheduleActivity.this));

        ScheduleAdapter myAdapter = new ScheduleAdapter(ScheduleActivity.this, daysOfTheWeek);
        wearableRecyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(ScheduleActivity.this);
    }

    public static String getStringByLocal(Activity context, int id, String locale) {
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(new Locale(locale));
        return context.createConfigurationContext(configuration).getResources().getString(id);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ScheduleDetailedActivity.class);
        intent.putExtra("theDay", getStringByLocal(this, idOfTheDaysArray.get(position), "en")); //ALWAYS SEND THE ENGLISH STRING
        intent.putExtra("localizedTheDay", getStringByLocal(this, idOfTheDaysArray.get(position), "tr")); //send the Turkish in case it is needed.
        startActivity(intent);
    }
}
