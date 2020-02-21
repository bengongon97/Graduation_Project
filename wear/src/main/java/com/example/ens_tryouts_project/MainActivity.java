package com.example.ens_tryouts_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ens_tryouts_project.MenuOfTheDay.MenuOfTheDayActivity;
import com.example.ens_tryouts_project.Schedule.ScheduleActivity;

public class MainActivity extends WearableActivity {

    private TextView mTextView;
//this is the pushed new version
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enables Always-on
        setAmbientEnabled();

        ImageView diningImage = findViewById(R.id.diningImage);
        diningImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuOfTheDayActivity.class);
                //intent.putExtra("index", adapterLedgerResultList.get(position).getLedger_hash());
                startActivity(intent);
            }
        });

        ImageView calenderImage = findViewById(R.id.calenderImage);
        calenderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                //intent.putExtra("index", adapterLedgerResultList.get(position).getLedger_hash());
                startActivity(intent);
            }
        });
    }
}
