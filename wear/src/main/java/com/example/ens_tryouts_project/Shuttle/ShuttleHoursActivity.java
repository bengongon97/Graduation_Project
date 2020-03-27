package com.example.ens_tryouts_project.Shuttle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ens_tryouts_project.R;
import com.example.ens_tryouts_project.databinding.ActivityShuttleAvailableDaysBinding;
import com.example.ens_tryouts_project.databinding.ActivityShuttleHoursBinding;

import java.util.ArrayList;
import java.util.List;

public class ShuttleHoursActivity extends AppCompatActivity {

    private ActivityShuttleHoursBinding binding;
    private List<String> hoursList;
    ShuttleAdapter myShuttleDaysAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityShuttleHoursBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        ShuttleClass theDestinationObject = (ShuttleClass) intent.getSerializableExtra("theDestinationObject");
        String theOnClickedDay = intent.getStringExtra("theClickedDay");


        if(theOnClickedDay != null && !theOnClickedDay.equals("")){ //it is a special occasion
            binding.destinationCampusTextView.setText(theOnClickedDay + " - Campus" );

            if(theOnClickedDay.equals("Monday")){
                myShuttleDaysAdapter = new ShuttleAdapter(theDestinationObject.getMonday().to_campus);
            }
            else if(theOnClickedDay.equals("Friday")){
                myShuttleDaysAdapter = new ShuttleAdapter(theDestinationObject.getFriday().to_campus);
            }
            else if(theOnClickedDay.equals("Weekdays")){ //it is weekdays option
                myShuttleDaysAdapter = new ShuttleAdapter(theDestinationObject.getWeekdays().to_campus);
            }
            else if(theOnClickedDay.equals("Saturday")){ //it is saturday option
                myShuttleDaysAdapter = new ShuttleAdapter(theDestinationObject.getSaturday().to_campus);
            }
            else if(theOnClickedDay.equals("Sunday")){ //it is sunday option
                myShuttleDaysAdapter = new ShuttleAdapter(theDestinationObject.getSunday().to_campus);
            }
        }

        binding.shuttleDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.shuttleDetailsRecyclerView.setAdapter(myShuttleDaysAdapter);
    }
}
