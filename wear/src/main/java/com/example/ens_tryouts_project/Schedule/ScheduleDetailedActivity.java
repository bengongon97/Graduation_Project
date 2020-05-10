package com.example.ens_tryouts_project.Schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ens_tryouts_project.Network.RetrofitClientInstance;
import com.example.ens_tryouts_project.Network.RippleAPIService;
import com.example.ens_tryouts_project.R;
import com.example.ens_tryouts_project.Shuttle.ShuttleClass;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleDetailedActivity extends AppCompatActivity {

    Map<String, Object> weeklySchedule;
    List<String> tmp = new ArrayList<>();
    ProgressBar indeterminateBar3;
    ScheduleDaysSubClass tryout = new ScheduleDaysSubClass(tmp,tmp,tmp,tmp,tmp,tmp,tmp,0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_detailed);

        Intent intent = getIntent();
        final String theDay = intent.getStringExtra("theDay");

        TextView dayTextView = findViewById(R.id.dayTextView);
        if (theDay != null)
            dayTextView.setText(theDay);

        indeterminateBar3 = findViewById(R.id.indeterminateBar3);

        RippleAPIService service = RetrofitClientInstance.getRetrofitInstance().create(RippleAPIService.class);
        indeterminateBar3.setVisibility(View.VISIBLE);
        Call<Map<String, Object>> call = service.scheduleCall();

        call.enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                indeterminateBar3.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    weeklySchedule = response.body();
                    if (weeklySchedule != null) {
                        for (Map.Entry<String, Object> entry : weeklySchedule.entrySet()) {
                            String theDayOfTheWeek = entry.getKey();

                            if (theDayOfTheWeek.equals(theDay.toLowerCase())) {
                                Map<String, Object> entry2 = (Map<String, Object>) entry.getValue();
                                for (Map.Entry<String, Object> entryInside : entry2.entrySet()) {
                                    String inKey = entryInside.getKey();
                                    if (inKey.equals("begintime")) {
                                        tmp = (ArrayList) entryInside.getValue();
                                        tryout.setBegintime(tmp);
                                    }
                                    else if(inKey.equals("endtime")){
                                        tmp = (ArrayList) entryInside.getValue();
                                        tryout.setEndtime(tmp);
                                    }
                                    else if(inKey.equals("uniqueall")){
                                        tmp = (ArrayList) entryInside.getValue();
                                        tryout.setClassCodeAndName(tmp);
                                    }
                                    else if(inKey.equals("buildingcode")){
                                        tmp = (ArrayList) entryInside.getValue();
                                        tryout.setBuildingcode(tmp);
                                    }
                                    else if(inKey.equals("roomcode")){
                                        tmp = (ArrayList) entryInside.getValue();
                                        tryout.setRoomcode(tmp);
                                    }
                                }
                                tryout.toString();

                                RecyclerView wear_detail_recycler_view = findViewById(R.id.wear_detail_recycler_view);
                                wear_detail_recycler_view.setLayoutManager(new LinearLayoutManager(ScheduleDetailedActivity.this));

                                ScheduleDetailedAdapter myAdapter = new ScheduleDetailedAdapter(tryout);
                                wear_detail_recycler_view.setAdapter(myAdapter);
                            }
                        }
                    }
                }
                else {
                    Toast.makeText(ScheduleDetailedActivity.this, getString(R.string.unsuccessful_response), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                indeterminateBar3.setVisibility(View.GONE);
                Toast.makeText(ScheduleDetailedActivity.this, getString(R.string.call_failed), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

