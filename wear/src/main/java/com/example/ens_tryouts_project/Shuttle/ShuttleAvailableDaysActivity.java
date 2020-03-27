package com.example.ens_tryouts_project.Shuttle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ens_tryouts_project.databinding.ActivityShuttleAvailableDaysBinding;

import java.util.ArrayList;
import java.util.List;

public class ShuttleAvailableDaysActivity extends AppCompatActivity implements ShuttleAdapter.OnItemClickListener{

    private ActivityShuttleAvailableDaysBinding binding;
    private List<String> availableDaysArray;
    ShuttleClass theDestinationObject;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_shuttle_available_days);
        binding = ActivityShuttleAvailableDaysBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        intent = new Intent(this, ShuttleHoursActivity.class);

        Intent intentSerial = getIntent();
        theDestinationObject = (ShuttleClass) intentSerial.getSerializableExtra("theDestinationObject");

        if(theDestinationObject != null){
            String header = theDestinationObject.getRoute_name_eng() + " - Avail. Days";
            binding.availableDaysTextView.setText(header);

            availableDaysArray = new ArrayList<>();

            //processing exceptions
            if(theDestinationObject.getMonday() != null){
                availableDaysArray.add("Monday");
            }
            if(theDestinationObject.getFriday() != null){ //making this else if may provide a very slight performance increase but it is omitted to avoid possible errors.
                availableDaysArray.add("Friday");
            }
            //processing expected values
            if(theDestinationObject.getWeekdays() != null){
                availableDaysArray.add("Weekdays");
            }
            if(theDestinationObject.getSaturday() != null){
                availableDaysArray.add("Saturday");
            }
            if(theDestinationObject.getSunday() != null){
                availableDaysArray.add("Sunday");
            }
        }

        ShuttleAdapter myShuttleDaysAdapter = new ShuttleAdapter(availableDaysArray);
        binding.availableDaysRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.availableDaysRecyclerView.setAdapter(myShuttleDaysAdapter);
        myShuttleDaysAdapter.setOnItemClickListener(ShuttleAvailableDaysActivity.this);
    }

    @Override
    public void onItemClick(int position) {
        //Toast.makeText(this,""+ availableDaysArray.get(position),Toast.LENGTH_SHORT).show();

        intent.putExtra("theDestinationObject", theDestinationObject);
        intent.putExtra("theClickedDay", availableDaysArray.get(position));
        startActivity(intent);
    }
}
