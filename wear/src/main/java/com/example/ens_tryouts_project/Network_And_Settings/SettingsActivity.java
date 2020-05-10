package com.example.ens_tryouts_project.Network_And_Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ens_tryouts_project.R;

import java.util.Locale;


public class SettingsActivity extends AppCompatActivity {

    public SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        final RadioGroup myRadioGroup = findViewById(R.id.myRadioGroup);
        Button change_lang_button = findViewById(R.id.changeLangButton);

        change_lang_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = myRadioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton radioButton = findViewById(selectedId);

                if(radioButton.getText().equals("English")){
                    String languageToLoad = "";
                    String country = "";
                    String appCurrent = getResources().getConfiguration().locale.getLanguage();

                    if (appCurrent.equals("tr")) {
                        languageToLoad = "en";
                        country = "US";

                        Locale locale = new Locale(languageToLoad, country);
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        Context context = getBaseContext();
                        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());


                        Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                        Toast.makeText(SettingsActivity.this, getString(R.string.lang_chg_successful), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(SettingsActivity.this, "Your app is already English.", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(radioButton.getText().equals("Türkçe")){
                    String languageToLoad = "";
                    String country = "";
                    String appCurrent = getResources().getConfiguration().locale.getLanguage();

                    if (appCurrent.equals("en")) {
                        languageToLoad = "tr";
                        country = "TR";

                        Locale locale = new Locale(languageToLoad, country);
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        Context context = getBaseContext();
                        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());


                        Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                        Toast.makeText(SettingsActivity.this, getString(R.string.lang_chg_successful), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(SettingsActivity.this, "Uygulama zaten Türkçe.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
