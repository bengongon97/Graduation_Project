package com.example.ens_tryouts_project.LoginAndSettings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ens_tryouts_project.MainActivity;
import com.example.ens_tryouts_project.R;
import com.example.ens_tryouts_project.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    public SessionManagerClass sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        sessionManager = new SessionManagerClass(getApplicationContext());

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.usernameEditText.getText().toString();
                String password = binding.passwordEditText.getText().toString();

                if(username.equals("menes")){
                    if(password.equals("12345")){
                        //successful login
                        Toast.makeText(LoginActivity.this, getString(R.string.success_sign_in),Toast.LENGTH_SHORT).show();
                        // Creating user login session
                        // Use user real data
                        sessionManager.createLoginSession("menes", "12345");

                        // Staring MainActivity
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, getString(R.string.invalid_password),Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, getString(R.string.invalid_username),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
