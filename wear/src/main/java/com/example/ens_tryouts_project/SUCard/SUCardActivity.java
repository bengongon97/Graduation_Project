package com.example.ens_tryouts_project.SUCard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import com.example.ens_tryouts_project.databinding.ActivitySuCardBinding;

public class SUCardActivity extends AppCompatActivity {

    private ActivitySuCardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_su_card);
        binding = ActivitySuCardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}
