package com.example.ens_tryouts_project.Shuttle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ens_tryouts_project.databinding.ActivityShuttleBinding;

import java.util.ArrayList;
import java.util.List;


public class ShuttleActivity extends AppCompatActivity implements ShuttleAdapter.OnItemClickListener{

    List<String> destinationList = new ArrayList<>();
    private ActivityShuttleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_shuttle);
        binding = ActivityShuttleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        destinationList.add("Taksim");
        destinationList.add("Kadıköy");
        destinationList.add("Çekmeköy");
        destinationList.add("");
        destinationList.add("Bu istikametler elle girilmiştir.");
        destinationList.add("");
        destinationList.add("Hizmetten çekilmemiştir.");

        binding.destinationsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ShuttleAdapter myAdapter = new ShuttleAdapter(destinationList);
        binding.destinationsRecyclerView.setAdapter(myAdapter);
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
