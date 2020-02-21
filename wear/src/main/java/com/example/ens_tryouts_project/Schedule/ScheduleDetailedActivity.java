package com.example.ens_tryouts_project.Schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ens_tryouts_project.Network.RetrofitClientInstance;
import com.example.ens_tryouts_project.Network.RippleAPIService;
import com.example.ens_tryouts_project.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleDetailedActivity extends AppCompatActivity {

    ScheduleClass weeklySchedule;
    ScheduleDetailedAdapter myAdapter;
    RecyclerView detailsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_detailed);

        Intent intent = getIntent();
        final String theDay = intent.getStringExtra("theDay");

        TextView dayTextView = findViewById(R.id.dayTextView);
        dayTextView.setText(theDay);

        detailsRecyclerView = findViewById(R.id.detailsRecyclerView);
        detailsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        RippleAPIService service = RetrofitClientInstance.getRetrofitInstance().create(RippleAPIService.class);
        Call<ScheduleClass> call = service.scheduleCall();
        call.enqueue(new Callback<ScheduleClass>() {
            @Override
            public void onResponse(Call<ScheduleClass> call, Response<ScheduleClass> response) {
                //progressBar.dismiss();
                if (response.isSuccessful()) {
                    //CALL SUCCESSFUL
                    weeklySchedule = response.body();
                    ScheduleDaysSubClass tmp = classGetter(theDay);
                    List<List<String>> classListOfList = getThatList(tmp);

                    myAdapter = new ScheduleDetailedAdapter(classListOfList, theDay);

                    detailsRecyclerView.setAdapter(myAdapter);

                } else {
                    Toast.makeText(ScheduleDetailedActivity.this, "Unsuccessful response", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ScheduleClass> call, Throwable t) {
                //Call failed.
                //progressBar.dismiss();
                Toast.makeText(ScheduleDetailedActivity.this, "Call failed", Toast.LENGTH_SHORT).show();
                Log.d("Call failed message", t.getLocalizedMessage() + "");
            }
        });
    }

    public List<List<String>> getThatList(ScheduleDaysSubClass theFinalClassesOfTheDay) {
        List<List<String>> thatList = new ArrayList<>();
        List<String> classesList = new ArrayList<>();
        List<String> hourList = new ArrayList<>();

        if(theFinalClassesOfTheDay != null) {
            if(!theFinalClassesOfTheDay.getC840().equals("")) {
                classesList.add(theFinalClassesOfTheDay.getC840());
                hourList.add("8.40");
            }
            if(!theFinalClassesOfTheDay.getC940().equals("")) {
                classesList.add(theFinalClassesOfTheDay.getC940());
                hourList.add("9.40");
            }
            if(!theFinalClassesOfTheDay.getC1040().equals("")) {
                classesList.add(theFinalClassesOfTheDay.getC1040());
                hourList.add("10.40");
            }
            if(!theFinalClassesOfTheDay.getC1140().equals("")) {
                classesList.add(theFinalClassesOfTheDay.getC1140());
                hourList.add("11.40");
            }
            if(!theFinalClassesOfTheDay.getC1240().equals("")) {
                classesList.add(theFinalClassesOfTheDay.getC1240());
                hourList.add("12.40");
            }
            if(!theFinalClassesOfTheDay.getC1340().equals("")) {
                classesList.add(theFinalClassesOfTheDay.getC1340());
                hourList.add("13.40");
            }
            if(!theFinalClassesOfTheDay.getC1440().equals("")) {
                classesList.add(theFinalClassesOfTheDay.getC1440());
                hourList.add("14.40");
            }
            if(!theFinalClassesOfTheDay.getC1540().equals("")) {
                classesList.add(theFinalClassesOfTheDay.getC1540());
                hourList.add("15.40");
            }
            if(!theFinalClassesOfTheDay.getC1640().equals("")) {
                classesList.add(theFinalClassesOfTheDay.getC1640());
                hourList.add("16.40");
            }
            if(!theFinalClassesOfTheDay.getC1740().equals("")) {
                classesList.add(theFinalClassesOfTheDay.getC1740());
                hourList.add("17.40");
            }
            if(!theFinalClassesOfTheDay.getC1840().equals("")) {
                classesList.add(theFinalClassesOfTheDay.getC1840());
                hourList.add("18.40");
            }
            if(!theFinalClassesOfTheDay.getC1940().equals("")) {
                classesList.add(theFinalClassesOfTheDay.getC1940());
                hourList.add("19.40");
            }
            if(!theFinalClassesOfTheDay.getC2040().equals("")) {
                classesList.add(theFinalClassesOfTheDay.getC2040());
                hourList.add("20.40");
            }
            if(!theFinalClassesOfTheDay.getC2140().equals("")) {
                classesList.add(theFinalClassesOfTheDay.getC2140());
                hourList.add("21.40");
            }
            if(!theFinalClassesOfTheDay.getC2240().equals("")) {
                classesList.add(theFinalClassesOfTheDay.getC2240());
                hourList.add("22.40");
            }
            if(!theFinalClassesOfTheDay.getC2340().equals("")) {
                classesList.add(theFinalClassesOfTheDay.getC2340());
                hourList.add("23.40");
            }
            if(!theFinalClassesOfTheDay.getC2440().equals("")) {
                classesList.add(theFinalClassesOfTheDay.getC2440());
                hourList.add("24.40");
            }
        }
        thatList.add(classesList);
        thatList.add(hourList);
        return thatList;
    }

    public ScheduleDaysSubClass classGetter(String theDay){
        ScheduleDaysSubClass theClassesOfTheDay = null;
        switch (theDay) {
            case "mon":
                theClassesOfTheDay = weeklySchedule.getMon();
                break;
            case "tue":
                theClassesOfTheDay = weeklySchedule.getTue();
                break;
            case "wed":
                theClassesOfTheDay = weeklySchedule.getWed();
                break;
            case "thu":
                theClassesOfTheDay = weeklySchedule.getThu();
                break;
            case "fri":
                theClassesOfTheDay = weeklySchedule.getFri();
                break;

            case "sat":
                if (weeklySchedule.getSat() == null || weeklySchedule.getSat().isEmpty()) {
                    theClassesOfTheDay = null;
                }
                break;
            case "sun":
                if (weeklySchedule.getSun() == null || weeklySchedule.getSun().isEmpty()) {
                    theClassesOfTheDay = null;
                }
                break;
            default:
                theClassesOfTheDay = weeklySchedule.getMon(); //MIGHT WANNA CHANGE LATER!!
        }
        return theClassesOfTheDay;
    }
}
