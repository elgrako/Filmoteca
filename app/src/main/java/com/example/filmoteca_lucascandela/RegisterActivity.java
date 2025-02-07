package com.example.filmoteca_lucascandela;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText userField, passwordField;
    DataBaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Registro de Usuarios");
        }

        userField = findViewById(R.id.RegisterUserField);
        passwordField = findViewById(R.id.RegisterPasswordField);
        Button registerButton = findViewById(R.id.RegisterRegisterButton);

        dbHelper = new DataBaseHelper(this);

        registerButton.setOnClickListener(e -> registerUser());
    }

    private void registerUser() {
        String username = userField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dbHelper.userExists(username)) {
            Toast.makeText(this, "Este usuario ya esta registrado", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean registroExitoso = dbHelper.registerUser(username, password);

        if (registroExitoso) {
            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
        }
    }
}