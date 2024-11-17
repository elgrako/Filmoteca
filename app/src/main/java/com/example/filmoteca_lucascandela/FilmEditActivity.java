package com.example.filmoteca_lucascandela;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FilmEditActivity extends AppCompatActivity {

    private int position;
    private Film film;

    private ImageView filmImageView;
    private EditText titleEditText, directorEditText, yearEditText, urlEditText, commentsEditText;
    private Spinner genreSpinner, formatSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_edit_activity);

        position = getIntent().getIntExtra("FILM_POSITION", -1);

        if (position < 0 || position >= FilmDataSource.films.size()) {
            Toast.makeText(this, "Película no encontrada.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        film = FilmDataSource.films.get(position);

        initializeViews();
        setupSpinners();
        checkFields();

        Button saveButton = findViewById(R.id.saveButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        saveButton.setOnClickListener(v -> {
            if (saveChanges()) {
                Toast.makeText(this, "Cambios aplicados.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        cancelButton.setOnClickListener(v -> {
            Toast.makeText(this, "Cambios cancelados.", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void initializeViews() {
        filmImageView = findViewById(R.id.filmImageView);
        titleEditText = findViewById(R.id.titleEditText);
        directorEditText = findViewById(R.id.directorEditText);
        yearEditText = findViewById(R.id.yearEditText);
        urlEditText = findViewById(R.id.urlEditText);
        commentsEditText = findViewById(R.id.commentsEditText);
        genreSpinner = findViewById(R.id.genreSpinner);
        formatSpinner = findViewById(R.id.formatSpinner);
    }

    private void setupSpinners() {
        String[] genres = {"Acción", "Comedia", "Drama", "Ciencia Ficción", "Terror"};
        String[] formats = {"Digital", "DVD", "Blu-Ray"};

        ArrayAdapter<String> genreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genres);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(genreAdapter);

        ArrayAdapter<String> formatAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, formats);
        formatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        formatSpinner.setAdapter(formatAdapter);
    }

    private void checkFields() {
        filmImageView.setImageResource(film.getImageResId());
        titleEditText.setText(film.getTitle());
        directorEditText.setText(film.getDirector());
        yearEditText.setText(String.valueOf(film.getYear()));
        urlEditText.setText(film.getImdbUrl());
        commentsEditText.setText(film.getComments());

        int genreIndex = film.getGenre();
        if (genreIndex >= 0 && genreIndex < genreSpinner.getCount()) {
            genreSpinner.setSelection(genreIndex);
        }

        int formatIndex = film.getFormat();
        if (formatIndex >= 0 && formatIndex < formatSpinner.getCount()) {
            formatSpinner.setSelection(formatIndex);
        }
    }

    private boolean saveChanges() {
        try {
            String title = titleEditText.getText().toString().trim();
            String director = directorEditText.getText().toString().trim();
            String yearString = yearEditText.getText().toString().trim();
            String imdbUrl = urlEditText.getText().toString().trim();
            String comments = commentsEditText.getText().toString().trim();

            if (title.isEmpty() || director.isEmpty() || yearString.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos.", Toast.LENGTH_SHORT).show();
                return false;
            }

            int year = Integer.parseInt(yearString);

            film.setTitle(title);
            film.setDirector(director);
            film.setYear(year);
            film.setImdbUrl(imdbUrl);
            film.setComments(comments);
            film.setGenre(genreSpinner.getSelectedItemPosition());
            film.setFormat(formatSpinner.getSelectedItemPosition());

            return true;
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Pon un año valido.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
