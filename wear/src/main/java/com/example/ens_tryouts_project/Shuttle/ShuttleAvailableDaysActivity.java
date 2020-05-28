package com.example.ens_tryouts_project.Shuttle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.wear.widget.WearableLinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ens_tryouts_project.R;
import com.example.ens_tryouts_project.databinding.ActivityShuttleAvailableDaysBinding;

import java.util.ArrayList;
import java.util.List;

public class ShuttleAvailableDaysActivity extends AppCompatActivity implements ShuttleAdapter.OnItemClickListener{

    private ActivityShuttleAvailableDaysBinding binding;
    private List<String> availableDaysArray;
    ShuttleClass theDestinationObject;
    Intent intent;

    //Activity number 2 for shuttle, showing available days

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShuttleAvailableDaysBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        intent = new Intent(this, ShuttleHoursActivity.class);

        Intent intentSerial = getIntent();
        theDestinationObject = (ShuttleClass) intentSerial.getSerializableExtra("theDestinationObject");

        if(theDestinationObject != null){
            String header = theDestinationObject.getRoute_name_eng();// + " - Avail. Days";
            binding.availableDaysTextView.setText(header);

            availableDaysArray = new ArrayList<>();

            //processing exceptions
            if(theDestinationObject.getMonday() != null){
                availableDaysArray.add(getString(R.string.monday));
            }
            if(theDestinationObject.getFriday() != null){ //making this else if may provide a very slight performance increase but it is omitted to avoid possible errors.
                availableDaysArray.add(getString(R.string.friday));
            }
            //processing expected values
            if(theDestinationObject.getWeekdays() != null){
                availableDaysArray.add(getString(R.string.weekdays));
            }
            if(theDestinationObject.getSaturday() != null){
                availableDaysArray.add(getString(R.string.saturday));
            }
            if(theDestinationObject.getSunday() != null){
                availableDaysArray.add(getString(R.string.sunday));
            }
        }


        ShuttleAdapter myShuttleDaysAdapter = new ShuttleAdapter(availableDaysArray);

        binding.wearAvailableDaysRecyclerView.setEdgeItemsCenteringEnabled(true);
        ShuttleHoursActivity.CustomScrollingLayoutCallback customScrollingLayoutCallback = new ShuttleHoursActivity.CustomScrollingLayoutCallback();
        binding.wearAvailableDaysRecyclerView.setLayoutManager(new WearableLinearLayoutManager(this, customScrollingLayoutCallback));
        binding.wearAvailableDaysRecyclerView.setAdapter(myShuttleDaysAdapter);
        myShuttleDaysAdapter.setOnItemClickListener(ShuttleAvailableDaysActivity.this);
    }

    @Override
    public void onItemClick(int position) {
        intent.putExtra("theDestinationObject", theDestinationObject);
        intent.putExtra("theClickedDay", availableDaysArray.get(position));
        startActivity(intent);
    }
}
