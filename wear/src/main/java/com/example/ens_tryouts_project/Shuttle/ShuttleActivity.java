package com.example.ens_tryouts_project.Shuttle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.wear.widget.WearableLinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ens_tryouts_project.Network.RetrofitClientInstance;
import com.example.ens_tryouts_project.Network.RippleAPIService;
import com.example.ens_tryouts_project.R;
import com.example.ens_tryouts_project.databinding.ActivityShuttleBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShuttleActivity extends AppCompatActivity implements ShuttleAdapter.OnItemClickListener{

    List<ShuttleClass> actualList;
    List<String> destinationList = new ArrayList<>();
    ShuttleAdapter myShuttleAdapter;
    private ActivityShuttleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityShuttleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        RippleAPIService service = RetrofitClientInstance.getRetrofitInstance().create(RippleAPIService.class);

        binding.indeterminateBar.setVisibility(View.VISIBLE);

        Call<List<ShuttleClass>> call = service.shuttleCall();
        call.enqueue(new Callback<List<ShuttleClass>>() {
            @Override
            public void onResponse(Call<List<ShuttleClass>> call, Response<List<ShuttleClass>> response) {
                binding.indeterminateBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    actualList = response.body();
                    if(actualList != null){
                        destinationList = destinationListCalculator(actualList);
                    }
                    else{
                        destinationList.add(getString(R.string.no_destination_found));
                    }
                    myShuttleAdapter = new ShuttleAdapter(destinationList);

                    binding.wearShuttleRecyclerView.setEdgeItemsCenteringEnabled(true);
                    ShuttleHoursActivity.CustomScrollingLayoutCallback customScrollingLayoutCallback = new ShuttleHoursActivity.CustomScrollingLayoutCallback();
                    binding.wearShuttleRecyclerView.setLayoutManager(new WearableLinearLayoutManager(ShuttleActivity.this, customScrollingLayoutCallback));

                    /* HERE, THESE MIGHT BE UNCOMMENTED, IF IT IS WANTED TO CUSTOMIZE.
                    binding.wearShuttleRecyclerView.setCircularScrollingGestureEnabled(true);
                    binding.wearShuttleRecyclerView.setBezelFraction(0.5f);
                    binding.wearShuttleRecyclerView.setScrollDegreesPerScreen(180);
                    */

                    binding.wearShuttleRecyclerView.setAdapter(myShuttleAdapter);
                    myShuttleAdapter.setOnItemClickListener(ShuttleActivity.this);
                } else {
                    Toast.makeText(ShuttleActivity.this, getString(R.string.unsuccessful_response), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ShuttleClass>> call, Throwable t) {
                binding.indeterminateBar.setVisibility(View.GONE);
                Toast.makeText(ShuttleActivity.this, getString(R.string.call_failed), Toast.LENGTH_LONG).show();
            }
        });
    }

    public List<String> destinationListCalculator(List<ShuttleClass> actualList){
         List<String> tmpDestList = new ArrayList<>();

         for(int i = 0; i < actualList.size(); i++){
             tmpDestList.add(actualList.get(i).getRoute_name_eng());
         }
         return tmpDestList;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ShuttleAvailableDaysActivity.class);
        intent.putExtra("theDestinationObject", actualList.get(position));
        startActivity(intent);
    }
}
