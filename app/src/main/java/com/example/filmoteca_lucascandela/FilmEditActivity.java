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

        // Recuperar la posición de la película a editar
        position = getIntent().getIntExtra("FILM_POSITION", 0);
        film = FilmDataSource.films.get(position);

        // Referenciar los componentes
        filmImageView = findViewById(R.id.filmImageView);
        titleEditText = findViewById(R.id.titleEditText);
        directorEditText = findViewById(R.id.directorEditText);
        yearEditText = findViewById(R.id.yearEditText);
        urlEditText = findViewById(R.id.urlEditText);
        commentsEditText = findViewById(R.id.commentsEditText);
        genreSpinner = findViewById(R.id.genreSpinner);
        formatSpinner = findViewById(R.id.formatSpinner);
        Button captureImageButton = findViewById(R.id.captureImageButton);
        Button selectImageButton = findViewById(R.id.selectImageButton);
        Button saveButton = findViewById(R.id.saveButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        // Configurar spinners
        setupSpinners();

        // Rellenar campos con datos actuales
        populateFields();

        // Configurar acciones de los botones
        captureImageButton.setOnClickListener(v ->
                Toast.makeText(this, "Funcionalidad no implementada", Toast.LENGTH_SHORT).show());

        selectImageButton.setOnClickListener(v ->
                Toast.makeText(this, "Funcionalidad no implementada", Toast.LENGTH_SHORT).show());

        saveButton.setOnClickListener(v -> {
            saveChanges();
            Toast.makeText(this, "Cambios aplicados correctamente.", Toast.LENGTH_SHORT).show();
            finish();
        });

        cancelButton.setOnClickListener(v -> {
            Toast.makeText(this, "Los cambios han sido cancelados.", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void setupSpinners() {
        // Datos de ejemplo
        String[] genres = {"Acción", "Comedia", "Drama", "Sci-Fi"};
        String[] formats = {"Digital", "DVD", "Blu-Ray"};

        ArrayAdapter<String> genreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genres);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(genreAdapter);

        ArrayAdapter<String> formatAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, formats);
        formatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        formatSpinner.setAdapter(formatAdapter);
    }

    private void populateFields() {
        titleEditText.setText(film.getTitle());
        directorEditText.setText(film.getDirector());
        yearEditText.setText(String.valueOf(film.getYear()));
        urlEditText.setText(film.getUrl());
        commentsEditText.setText(film.getComments());

        // Seleccionar el género y formato actuales en los spinners
        genreSpinner.setSelection(film.getGenre());
        formatSpinner.setSelection(film.getFormat());
    }

    private void saveChanges() {
        film.setTitle(titleEditText.getText().toString());
        film.setDirector(directorEditText.getText().toString());
        film.setYear(Integer.parseInt(yearEditText.getText().toString()));
        film.setUrl(urlEditText.getText().toString());
        film.setComments(commentsEditText.getText().toString());
        film.setGenre(genreSpinner.getSelectedItemPosition());
        film.setFormat(formatSpinner.getSelectedItemPosition());
    }
}
