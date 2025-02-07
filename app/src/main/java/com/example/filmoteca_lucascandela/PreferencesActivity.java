package com.example.filmoteca_lucascandela;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class PreferencesActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "AppPreferences";
    private static final String KEY_REGISTER_ENABLED = "registerEnabled";

    private CheckBox registerCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Ajustes");
        }

        registerCheckBox = findViewById(R.id.cb_preferences);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isRegisterEnabled = prefs.getBoolean(KEY_REGISTER_ENABLED, true);
        registerCheckBox.setChecked(isRegisterEnabled);

        registerCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(KEY_REGISTER_ENABLED, isChecked);
            editor.apply();
        });
    }
}
