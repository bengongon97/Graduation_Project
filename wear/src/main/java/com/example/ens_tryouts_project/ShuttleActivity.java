package com.example.ens_tryouts_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;


public class ShuttleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuttle);

        TextView destinationsTextView = findViewById(R.id.destinationsTextView); //If i ever use it
        RecyclerView destinationsRecyclerView = findViewById(R.id.destinationsRecyclerView);
    }
}
