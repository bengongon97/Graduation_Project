package com.example.ens_tryouts_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ens_tryouts_project.databinding.ActivityShuttleAvailableDaysBinding;

public class ShuttleAvailableDaysActivity extends AppCompatActivity {

    private ActivityShuttleAvailableDaysBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_shuttle_available_days);
        binding = ActivityShuttleAvailableDaysBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        final String theDestination = intent.getStringExtra("theDestination");

        if(theDestination != null)
            binding.availableDaysTextView.setText(theDestination + " - Avail. Days"); //change it later on, convert into translatable text
    }
}
