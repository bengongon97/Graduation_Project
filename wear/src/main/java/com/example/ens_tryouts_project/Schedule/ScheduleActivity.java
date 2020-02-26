package com.example.ens_tryouts_project.Schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ens_tryouts_project.R;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements ScheduleAdapter.OnItemClickListener{

    List<String> daysOfTheWeek = new ArrayList<>();

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

        TextView courseScheduleTextView = findViewById(R.id.courseScheduleTextView); //If I ever need it...
        RecyclerView daysRecyclerView = findViewById(R.id.daysRecyclerView);

        daysRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ScheduleAdapter myAdapter = new ScheduleAdapter(daysOfTheWeek);
        daysRecyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this,""+daysOfTheWeek.get(position),Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ScheduleDetailedActivity.class);
        intent.putExtra("theDay", daysOfTheWeek.get(position));
        startActivity(intent);
    }
}
