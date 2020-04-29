package com.example.ens_tryouts_project.SUCard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ens_tryouts_project.Network.RetrofitClientInstance;
import com.example.ens_tryouts_project.Network.RetrofitMySUClientInstance;
import com.example.ens_tryouts_project.Network.RippleAPIService;
import com.example.ens_tryouts_project.databinding.ActivitySuCardBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SUCardActivity extends AppCompatActivity {

    private ActivitySuCardBinding binding;
    private List<SUCardClass> suCardResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySuCardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        RippleAPIService service = RetrofitMySUClientInstance.getRetrofitInstance().create(RippleAPIService.class);

        Call<List<SUCardClass>> call = service.suCardCall();
        call.enqueue(new Callback<List<SUCardClass>>() {
            @Override
            public void onResponse(Call<List<SUCardClass>> call, Response<List<SUCardClass>> response) {
                //progressBar.dismiss();

                if (response.isSuccessful()) {
                    suCardResponse = response.body();

                    binding.foodTextView.setText("Food\n" + suCardResponse.get(0).getMeal().getSum() + "₺");
                    binding.printTextView.setText("Print\n" + suCardResponse.get(0).getPrint().getSum() + "₺");
                    binding.transportationTextView.setText("Transportation\n" + suCardResponse.get(0).getTransport().getSum() + "₺");
                } else {
                    Toast.makeText(SUCardActivity.this, "Unsuccessful response", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<SUCardClass>> call, Throwable t) {
                //Call failed.
                //progressBar.dismiss();
                String xd = call.request().url().toString();
                Toast.makeText(SUCardActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                //Toast.makeText(ShuttleActivity.this, "Call failed for shuttle dest. list", Toast.LENGTH_LONG).show();
            }
        });
    }
}
