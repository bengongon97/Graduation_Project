package com.example.ens_tryouts_project.Schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import com.example.ens_tryouts_project.Network.RetrofitClientInstance;
import com.example.ens_tryouts_project.Network.RippleAPIService;
import com.example.ens_tryouts_project.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleActivity extends AppCompatActivity implements ScheduleAdapter.OnItemClickListener {

    private WearableRecyclerView wearableRecyclerView;

    List<String> daysOfTheWeek = new ArrayList<>();
    Map<String, Object> weeklySchedule;
    List<String> tmp = new ArrayList<>();
    ScheduleDaysSubClass tryout = new ScheduleDaysSubClass(tmp,tmp,tmp,tmp,tmp,tmp,tmp,0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        daysOfTheWeek.add("Monday");
        daysOfTheWeek.add("Tuesday");
        daysOfTheWeek.add("Wednesday");
        daysOfTheWeek.add("Thursday");
        daysOfTheWeek.add("Friday");
        daysOfTheWeek.add("Saturday");
        daysOfTheWeek.add("Sunday");

        wearableRecyclerView = findViewById(R.id.wear_detail_recycler_view);
        // To align the edge children (first and last) with the center of the screen
        wearableRecyclerView.setEdgeItemsCenteringEnabled(true);
        wearableRecyclerView.setLayoutManager(new WearableLinearLayoutManager(ScheduleActivity.this));

        ScheduleAdapter myAdapter = new ScheduleAdapter(ScheduleActivity.this, daysOfTheWeek);
        wearableRecyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(ScheduleActivity.this);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ScheduleDetailedActivity.class);
        intent.putExtra("theDay", daysOfTheWeek.get(position));
        startActivity(intent);
    }
}
