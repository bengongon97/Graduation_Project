package com.example.ens_tryouts_project.Shuttle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ens_tryouts_project.Network.RetrofitClientInstance;
import com.example.ens_tryouts_project.Network.RippleAPIService;
import com.example.ens_tryouts_project.databinding.ActivityShuttleBinding;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
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
        //setContentView(R.layout.activity_shuttle);
        binding = ActivityShuttleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        RippleAPIService service = RetrofitClientInstance.getRetrofitInstance().create(RippleAPIService.class);

        Call<List<ShuttleClass>> call = service.shuttleCall();
        call.enqueue(new Callback<List<ShuttleClass>>() {
            @Override
            public void onResponse(Call<List<ShuttleClass>> call, Response<List<ShuttleClass>> response) {
                //progressBar.dismiss();
                /*String xd = response.raw().request().url().toString();
                Log.i("URL of request is:", xd);*/

                if (response.isSuccessful()) {
                    actualList = response.body();
                    if(actualList != null){
                        destinationList = destinationListCalculator(actualList);
                    }
                    else{
                        destinationList.add("Trouble finding any available destinations.");
                    }
                    myShuttleAdapter = new ShuttleAdapter(destinationList);
                    binding.destinationsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    binding.destinationsRecyclerView.setAdapter(myShuttleAdapter);
                    myShuttleAdapter.setOnItemClickListener(ShuttleActivity.this);
                } else {
                    Toast.makeText(ShuttleActivity.this, "Unsuccessful response", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<ShuttleClass>> call, Throwable t) {
                //Call failed.
                //progressBar.dismiss();
                Toast.makeText(ShuttleActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                //Toast.makeText(ShuttleActivity.this, "Call failed for shuttle dest. list", Toast.LENGTH_LONG).show();
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
        //Toast.makeText(this,""+ destinationList.get(position),Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ShuttleAvailableDaysActivity.class);
        intent.putExtra("theDestinationObject", actualList.get(position));
        startActivity(intent);
    }
}
