package com.example.filmoteca_lucascandela;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText userField, passwordField;
    private Button loginButton, registerButton;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Inicio de Sesion");
        }

        userField = findViewById(R.id.userField);
        passwordField = findViewById(R.id.PasswordField);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        dbHelper = new DataBaseHelper(this);

        loginButton.setOnClickListener(e -> loginUser());

        registerButton.setOnClickListener(e -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String username = userField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        }

        boolean loginExitoso = dbHelper.checkUser(username, password);

        if (loginExitoso) {
            Toast.makeText(this, "Inicio de sesion correcto", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginActivity.this, FilmListActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}
