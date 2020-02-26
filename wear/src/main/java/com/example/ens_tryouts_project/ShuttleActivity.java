package com.example.ens_tryouts_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ens_tryouts_project.Schedule.ScheduleAdapter;
import com.example.ens_tryouts_project.Schedule.ScheduleDetailedActivity;

import java.util.ArrayList;
import java.util.List;


public class ShuttleActivity extends AppCompatActivity implements ShuttleAdapter.OnItemClickListener{

    List<String> destinationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuttle);

        destinationList.add("Taksim");
        destinationList.add("Kadıköy");
        destinationList.add("Çekmeköy");
        destinationList.add("");
        destinationList.add("Bu istikametler elle girilmiştir.");
        destinationList.add("");
        destinationList.add("Hizmetten çekilmemiştir.");

        TextView destinationsTextView = findViewById(R.id.destinationsTextView); //If i ever use it
        RecyclerView destinationsRecyclerView = findViewById(R.id.destinationsRecyclerView);

        destinationsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ShuttleAdapter myAdapter = new ShuttleAdapter(destinationList);
        destinationsRecyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this,""+ destinationList.get(position),Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ShuttleAvailableDaysActivity.class);
        intent.putExtra("theDestination", destinationList.get(position));
        startActivity(intent);
    }
}
