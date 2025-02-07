package com.example.filmoteca_lucascandela;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "AppPreferences";
    private static final String KEY_LAST_USER = "lastUser";
    private static final String KEY_REGISTER_ENABLED = "registerEnabled";

    private EditText userField, passwordField;
    private Button registerButton, loginButton;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Inicio de Sesion");
        }

        userField = findViewById(R.id.UserField);
        passwordField = findViewById(R.id.PasswordField);
        registerButton = findViewById(R.id.RegisterButton);
        loginButton = findViewById(R.id.loginButton);
        dbHelper = new DataBaseHelper(this);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String lastUser = prefs.getString(KEY_LAST_USER, "");
        userField.setText(lastUser);

        boolean isRegisterEnabled = prefs.getBoolean(KEY_REGISTER_ENABLED, true);
        registerButton.setVisibility(isRegisterEnabled ? View.VISIBLE : View.GONE);

        loginButton.setOnClickListener(v -> loginUser());
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String username = userField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dbHelper.checkUser(username, password)) {
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(KEY_LAST_USER, username);
            editor.apply();

            Toast.makeText(this, "Inicio de sesion correcto", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.login_settings) {
            Intent intent = new Intent(this, PreferencesActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
