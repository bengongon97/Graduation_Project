package com.example.ens_tryouts_project.SUCard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ens_tryouts_project.Network_And_Settings.RetrofitMySUClientInstance;
import com.example.ens_tryouts_project.Network_And_Settings.RippleAPIService;
import com.example.ens_tryouts_project.R;
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
                if (response.isSuccessful()) {
                    suCardResponse = response.body();

                    if(suCardResponse != null){
                        String foodTextViewString = getString(R.string.food) + suCardResponse.get(0).getMeal().getSum() + "₺";
                        binding.foodTextView.setText(foodTextViewString);
                        String printTextViewString = getString(R.string.print) + suCardResponse.get(0).getPrint().getSum() + "₺";
                        binding.printTextView.setText(printTextViewString);
                        String transportationTextViewString = getString(R.string.transportation) + suCardResponse.get(0).getTransport().getSum() + "₺";
                        binding.transportationTextView.setText(transportationTextViewString);
                    }
                } else {
                    Toast.makeText(SUCardActivity.this, getString(R.string.unsuccessful_response), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<SUCardClass>> call, Throwable t) {
                Toast.makeText(SUCardActivity.this, getString(R.string.call_failed), Toast.LENGTH_LONG).show();
                binding.foodTextView.setText(getString(R.string.try_again));
                binding.printTextView.setText(getString(R.string.try_again));
                binding.transportationTextView.setText(getString(R.string.try_again));
            }
        });
    }
}
