package com.example.ens_tryouts_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.wear.widget.SwipeDismissFrameLayout;

import com.example.ens_tryouts_project.LoginAndSettings.SessionManagerClass;
import com.example.ens_tryouts_project.MenuOfTheDay.MenuOfTheDayActivity;

import com.example.ens_tryouts_project.LoginAndSettings.SettingsActivity;
import com.example.ens_tryouts_project.SUCard.SUCardActivity;
import com.example.ens_tryouts_project.Schedule.ScheduleActivity;
import com.example.ens_tryouts_project.Shuttle.ShuttleActivity;

public class MainActivity extends WearableActivity {

    boolean doubleBackToExitPressedOnce = false;
    public SessionManagerClass sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enables Always-on
        setAmbientEnabled();

        sessionManager = new SessionManagerClass(getApplicationContext());
        //Toast.makeText(getApplicationContext(), "User Login Status: " + sessionManager.isLoggedIn(), Toast.LENGTH_LONG).show();

        /*
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        sessionManager.checkLogin();

        ImageView diningImage = findViewById(R.id.diningImage);
        diningImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuOfTheDayActivity.class);
                startActivity(intent);
            }
        });

        ImageView calenderImage = findViewById(R.id.calenderImage);
        calenderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });

        ImageView busImage = findViewById(R.id.busImage);
        busImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShuttleActivity.class);
                startActivity(intent);
            }
        });

        ImageView moneyImage = findViewById(R.id.moneyImage);
        moneyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SUCardActivity.class);
                startActivity(intent);
            }
        });

        ImageView settingsImage = findViewById(R.id.settingsImage);
        settingsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish();
            moveTaskToBack(true);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.click_back_twice), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
