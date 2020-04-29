package com.example.ens_tryouts_project.MenuOfTheDay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ens_tryouts_project.Network.RetrofitClientInstance;
import com.example.ens_tryouts_project.Network.RippleAPIService;
import com.example.ens_tryouts_project.R;
import com.example.ens_tryouts_project.databinding.ActivityMenuOfTheDayBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuOfTheDayActivity extends AppCompatActivity {

    List<MenuOfTheDayClass> menuArray = new ArrayList<>();
    MenuOfTheDayAdapter myMenuAdapter;
    ActivityMenuOfTheDayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_menu_of_the_day);
        binding = ActivityMenuOfTheDayBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        RippleAPIService service = RetrofitClientInstance.getRetrofitInstance().create(RippleAPIService.class);
        binding.indeterminateBar2.setVisibility(View.VISIBLE);
        callRequest(service);
    }

    public void callRequest(RippleAPIService service) {

        Call<List<MenuOfTheDayClass>> call = service.menuCall();
        call.enqueue(new Callback<List<MenuOfTheDayClass>>() {

            @Override
            public void onResponse(Call<List<MenuOfTheDayClass>> call, Response<List<MenuOfTheDayClass>> response) {
                binding.indeterminateBar2.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    //Toast.makeText(MenuOfTheDayActivity.this, "We got your results!", Toast.LENGTH_LONG).show();
                    menuArray = response.body();

                    myMenuAdapter = new MenuOfTheDayAdapter(MenuOfTheDayActivity.this, menuArray);
                    binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    binding.menuRecyclerView.setAdapter(myMenuAdapter);
                } else {
                    Toast.makeText(MenuOfTheDayActivity.this, "Unsuccessful response", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<MenuOfTheDayClass>> call, Throwable t) {
                binding.indeterminateBar2.setVisibility(View.GONE);
                Toast.makeText(MenuOfTheDayActivity.this, "Call failed, please try again.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
