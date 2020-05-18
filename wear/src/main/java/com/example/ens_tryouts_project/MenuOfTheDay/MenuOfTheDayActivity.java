package com.example.ens_tryouts_project.MenuOfTheDay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
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
        binding = ActivityMenuOfTheDayBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.menuTextView.setText(getString(R.string.menu_header));

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
                    menuArray = response.body();

                    String currentLang = getResources().getConfiguration().locale.getLanguage();
                    myMenuAdapter = new MenuOfTheDayAdapter(MenuOfTheDayActivity.this, menuArray, currentLang);
                    binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    binding.menuRecyclerView.setAdapter(myMenuAdapter);
                } else {
                    Toast.makeText(MenuOfTheDayActivity.this, getString(R.string.unsuccessful_response), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<MenuOfTheDayClass>> call, Throwable t) {
                binding.indeterminateBar2.setVisibility(View.GONE);
                Toast.makeText(MenuOfTheDayActivity.this, getString(R.string.call_failed), Toast.LENGTH_LONG).show();
            }
        });
    }
}
