package com.example.ens_tryouts_project.MenuOfTheDay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ens_tryouts_project.Network.RetrofitClientInstance;
import com.example.ens_tryouts_project.Network.RippleAPIService;
import com.example.ens_tryouts_project.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuOfTheDayActivity extends AppCompatActivity {

    List<MenuOfTheDayClass> menuArray = new ArrayList<>();
    RecyclerView menuRecyclerView;
    MenuOfTheDayAdapter myMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_of_the_day);

        TextView menuTextView = findViewById(R.id.menuTextView); //if i ever need it

        menuRecyclerView = findViewById(R.id.menuRecyclerView);

        RippleAPIService service = RetrofitClientInstance.getRetrofitInstance().create(RippleAPIService.class);
        callRequest(service);
    }

    public void callRequest(RippleAPIService service) {

        Call<List<MenuOfTheDayClass>> call = service.menuCall();
        call.enqueue(new Callback<List<MenuOfTheDayClass>>() {

            @Override
            public void onResponse(Call<List<MenuOfTheDayClass>> call, Response<List<MenuOfTheDayClass>> response) {
                //progressBar.dismiss();
                String xd = response.raw().request().url().toString();
                Log.d("URL of request is:", xd);

                if (response.isSuccessful()) {
                    //Toast.makeText(MenuOfTheDayActivity.this, "We got your results!", Toast.LENGTH_LONG).show();
                    menuArray = response.body();
                    myMenuAdapter = new MenuOfTheDayAdapter(MenuOfTheDayActivity.this, menuArray);
                    menuRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    menuRecyclerView.setAdapter(myMenuAdapter);
                } else {
                    Toast.makeText(MenuOfTheDayActivity.this, "Unsuccessful response", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<MenuOfTheDayClass>> call, Throwable t) {
                //Call failed.
                //progressBar.dismiss();
                Toast.makeText(MenuOfTheDayActivity.this, "Call failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}
