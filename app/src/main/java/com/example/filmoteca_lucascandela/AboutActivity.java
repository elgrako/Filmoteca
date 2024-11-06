package com.example.filmoteca_lucascandela;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutactivity);

        Button webButton = findViewById(R.id.webButton);
        Button soporteButton = findViewById(R.id.soporteButton);
        Button volverButton = findViewById(R.id.volverButton);


        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com"));
                startActivity(webIntent);
            }
        });

        soporteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent soporteIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:filmoteca@pmdm.es"));
                soporteIntent.putExtra(Intent.EXTRA_SUBJECT, "Soporte Filmoteca");
                soporteIntent.putExtra(Intent.EXTRA_TEXT, "Texto del correo de soporte");
                startActivity(Intent.createChooser(soporteIntent, "Enviar correo"));
            }
        });

        volverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }
}
