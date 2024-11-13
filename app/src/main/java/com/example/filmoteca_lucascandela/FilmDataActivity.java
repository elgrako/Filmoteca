package com.example.filmoteca_lucascandela;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FilmDataActivity extends AppCompatActivity {
    private TextView titleTextView, directorTextView, yearTextView, descriptionTextView, formatTextView, genreTextView;
    private Button imdbButton, backButton, editButton;
    private ImageView filmImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_data_activity);
        titleTextView = findViewById(R.id.titleTextViewFilmActivitytitulo);
        directorTextView = findViewById(R.id.directorTextViewFilmActivity);
        yearTextView = findViewById(R.id.yearTextViewFilmActivity);
        descriptionTextView = findViewById(R.id.descriptionTextViewFilmActivity);
        imdbButton = findViewById(R.id.imdbBtn);
        backButton = findViewById(R.id.backBtn);
        editButton = findViewById(R.id.editBtn);
        filmImageView = findViewById(R.id.imageViewFilmActivity);
        genreTextView = findViewById(R.id.genreTextViewFilmActivity);
        formatTextView = findViewById(R.id.formatTextViewFilmActivity);

    }
}
