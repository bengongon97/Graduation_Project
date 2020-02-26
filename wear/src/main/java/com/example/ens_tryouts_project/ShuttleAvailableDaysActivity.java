package com.example.ens_tryouts_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShuttleAvailableDaysActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuttle_available_days);

        Intent intent = getIntent();
        final String theDestination = intent.getStringExtra("theDestination");

        TextView availableDaysTextView = findViewById(R.id.availableDaysTextView);
        if(theDestination != null)
            availableDaysTextView.setText(theDestination + " - Avail. Days"); //change it later on, convert into translatable text
    }
}
