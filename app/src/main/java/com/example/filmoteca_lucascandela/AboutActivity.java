package com.example.filmoteca_lucascandela;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutactivity);

        TextView creador = findViewById(R.id.creador);
        TextView aboutText = findViewById(R.id.aboutText);
        ImageView imgabout = findViewById(R.id.imgabout);
        imgabout.setImageResource(R.drawable.imgabout);
        Button webButton = findViewById(R.id.webButton);
        Button soporteButton = findViewById(R.id.soporteButton);
        Button volverButton = findViewById(R.id.volverButton);
    }
}
