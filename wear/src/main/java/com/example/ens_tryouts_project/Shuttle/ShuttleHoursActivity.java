package com.example.ens_tryouts_project.Shuttle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.wear.widget.CircularProgressLayout;
import androidx.wear.widget.WearableLinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ens_tryouts_project.R;
import com.example.ens_tryouts_project.databinding.ActivityShuttleHoursBinding;

import java.util.ArrayList;
import java.util.List;

public class ShuttleHoursActivity extends AppCompatActivity implements ShuttleAdapter.OnItemClickListener,
        CircularProgressLayout.OnTimerFinishedListener, View.OnClickListener {

    private CircularProgressLayout circularProgress;
    private ActivityShuttleHoursBinding binding;
    ShuttleAdapter myShuttleDaysAdapter;
    int flag = 1; //initially it is 0, meaning from_campus is set, due to first-click fix.
    ShuttleClass theDestinationObject;
    List<String> finalList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityShuttleHoursBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.innerConstraintLayout.setVisibility(View.GONE);

        Intent intent = getIntent();
        theDestinationObject = (ShuttleClass) intent.getSerializableExtra("theDestinationObject");
        final String theOnClickedDay = intent.getStringExtra("theClickedDay");

        final String currentLang = getResources().getConfiguration().locale.getLanguage();

        if(currentLang.equals("en")){
            String headerTextView = "To " + theDestinationObject.getRoute_name_eng();
            binding.destinationCampusTextView.setText(headerTextView); //Since it is the default, the name must be changed too.
        }
        else if(currentLang.equals("tr")){
            String headerTextView = theDestinationObject.getRoute_name_tr() + "e"; //TODO: -e, -a difference in Turkish should be fixed.
            binding.destinationCampusTextView.setText(headerTextView); //Since it is the default, the name must be changed too.
        }

        circularProgress = findViewById(R.id.circular_progress);
        circularProgress.setOnTimerFinishedListener(this);
        circularProgress.setOnClickListener(this);

        converterFactoryFromToTo(theOnClickedDay, flag);

        binding.switchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 0){
                    flag = 1;
                    if(currentLang.equals("en")){
                        String headerTextView = "To " + theDestinationObject.getRoute_name_eng();
                        binding.destinationCampusTextView.setText(headerTextView); //Since it is the default, the name must be changed too.
                    }
                    else if(currentLang.equals("tr")){
                        String headerTextView = theDestinationObject.getRoute_name_tr() + "e"; //-e, -a difference in Turkish should be fixed.
                        binding.destinationCampusTextView.setText(headerTextView); //Since it is the default, the name must be changed too.
                    }
                    converterFactoryFromToTo(theOnClickedDay, flag);
                }
                else if(flag == 1){
                    flag = 0;
                    if(currentLang.equals("en")){
                        String headerTextView = "From " + theDestinationObject.getRoute_name_eng();
                        binding.destinationCampusTextView.setText(headerTextView); //Since it is the default, the name must be changed too.
                    }
                    else if(currentLang.equals("tr")){
                        String headerTextView = theDestinationObject.getRoute_name_tr() + "ten"; //-e, -a difference in Turkish should be fixed.
                        binding.destinationCampusTextView.setText(headerTextView); //Since it is the default, the name must be changed too.
                    }
                    converterFactoryFromToTo(theOnClickedDay, flag);
                }
            }
        });
    }

    // IMPORTANT: I AM DOING THE SAME THING AFTER IT FINISHES AND USER CLICKS CANCEL, SINCE IT IS JUST INFORMING
    @Override
    public void onClick(View view) {
        if (view.equals(circularProgress)) {
            // User canceled, abort the action
            circularProgress.stopTimer();
            binding.innerConstraintLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onTimerFinished(CircularProgressLayout layout) {
        // User didn't cancel, perform the action.
        circularProgress.stopTimer();
        binding.innerConstraintLayout.setVisibility(View.GONE);
    }

    public static class CustomScrollingLayoutCallback extends WearableLinearLayoutManager.LayoutCallback {
        /** How much should we scale the icon at most. */
        private static final float MAX_ICON_PROGRESS = 1f;

        private float progressToCenter;

        @Override
        public void onLayoutFinished(View child, RecyclerView parent) {

            // Figure out % progress from top to bottom
            float centerOffset = ((float) child.getHeight() / 2.0f) / (float) parent.getHeight();
            float yRelativeToCenterOffset = (child.getY() / parent.getHeight()) + centerOffset;

            // Normalize for center
            progressToCenter = Math.abs(0.5f - yRelativeToCenterOffset);
            // Adjust to the maximum scale
            progressToCenter = Math.min(progressToCenter, MAX_ICON_PROGRESS);

            child.setScaleX(1 - progressToCenter);
            child.setScaleY(1 - progressToCenter);
        }
    }

    public void converterFactoryFromToTo(String theOnClickedDay, int flag){
        finalList  = new ArrayList();
        if(flag == 0){ //To campus from destination (also default)
            if(theOnClickedDay != null && !theOnClickedDay.equals("")){ //it is a special occasion
                if(theOnClickedDay.equals(getString(R.string.monday)) && theDestinationObject.getMonday().getTo_campus() != null){
                    finalList = theDestinationObject.getMonday().getTo_campus();
                    myShuttleDaysAdapter = new ShuttleAdapter(finalList);
                }
                else if(theOnClickedDay.equals(getString(R.string.friday)) && theDestinationObject.getFriday().getTo_campus() != null){
                    finalList = theDestinationObject.getFriday().getTo_campus();
                    myShuttleDaysAdapter = new ShuttleAdapter(finalList);
                }
                else if(theOnClickedDay.equals(getString(R.string.weekdays)) && theDestinationObject.getWeekdays().getTo_campus() != null){ //it is weekdays option
                    finalList = theDestinationObject.getWeekdays().getTo_campus();
                    myShuttleDaysAdapter = new ShuttleAdapter(finalList);
                }
                else if(theOnClickedDay.equals(getString(R.string.saturday)) && theDestinationObject.getSaturday().getTo_campus() != null){ //it is saturday option
                    finalList = theDestinationObject.getSaturday().getTo_campus();
                    myShuttleDaysAdapter = new ShuttleAdapter(finalList);
                }
                else if(theOnClickedDay.equals(getString(R.string.sunday)) && theDestinationObject.getSunday().getTo_campus() != null){ //it is sunday option
                    finalList = theDestinationObject.getSunday().getTo_campus();
                    myShuttleDaysAdapter = new ShuttleAdapter(finalList);
                }
                else{
                    if(finalList.size() == 0){
                        finalList.add(getString(R.string.no_shuttle_found));
                        myShuttleDaysAdapter = new ShuttleAdapter(finalList);
                    }
                }
            }
        }
        else if(flag == 1){ //From campus to destination
            if(theOnClickedDay != null && !theOnClickedDay.equals("")){ //it is a special occasion
                if(theOnClickedDay.equals(getString(R.string.monday)) && theDestinationObject.getMonday().getFrom_campus() != null){
                    finalList = theDestinationObject.getMonday().getFrom_campus();
                    myShuttleDaysAdapter = new ShuttleAdapter(finalList);
                }
                else if(theOnClickedDay.equals(getString(R.string.friday)) && theDestinationObject.getFriday().getFrom_campus() != null){
                    finalList = theDestinationObject.getFriday().getFrom_campus();
                    myShuttleDaysAdapter = new ShuttleAdapter(finalList);
                }
                else if(theOnClickedDay.equals(getString(R.string.weekdays)) && theDestinationObject.getWeekdays().getFrom_campus() != null){ //it is weekdays option
                    finalList = theDestinationObject.getWeekdays().getFrom_campus();
                    myShuttleDaysAdapter = new ShuttleAdapter(finalList);
                }
                else if(theOnClickedDay.equals(getString(R.string.saturday)) && theDestinationObject.getSaturday().getFrom_campus() != null){ //it is saturday option
                    finalList = theDestinationObject.getSaturday().getFrom_campus();
                    myShuttleDaysAdapter = new ShuttleAdapter(finalList);
                }
                else if(theOnClickedDay.equals(getString(R.string.sunday)) && theDestinationObject.getSunday().getFrom_campus() != null){ //it is sunday option
                    finalList = theDestinationObject.getSunday().getFrom_campus();
                    myShuttleDaysAdapter = new ShuttleAdapter(finalList);
                }
                else{
                    if(finalList.size() == 0){
                        finalList.add(getString(R.string.no_shuttle_found));
                        myShuttleDaysAdapter = new ShuttleAdapter(finalList);
                    }
                }
            }
        }

        binding.wearShuttleHourRecyclerView.setEdgeItemsCenteringEnabled(true); //if you want to change this later, just comment it
        ShuttleHoursActivity.CustomScrollingLayoutCallback customScrollingLayoutCallback = new CustomScrollingLayoutCallback();
        binding.wearShuttleHourRecyclerView.setLayoutManager(new WearableLinearLayoutManager(this, customScrollingLayoutCallback));
        binding.wearShuttleHourRecyclerView.setAdapter(myShuttleDaysAdapter);
        myShuttleDaysAdapter.setOnItemClickListener(ShuttleHoursActivity.this);
    }

    @Override
    public void onItemClick(int position) {
        //Toast.makeText(this,finalList.get(position),Toast.LENGTH_SHORT).show();
        // Two seconds to cancel the action
        if(!finalList.get(position).equals(getString(R.string.no_shuttle_found))){
            binding.innerConstraintLayout.setVisibility(View.VISIBLE);
            circularProgress.setTotalTime(5000);
            // Start the timer
            circularProgress.startTimer();
        }
    }
}
