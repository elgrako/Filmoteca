package com.example.filmoteca_lucascandela;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class FilmEditActivity extends AppCompatActivity {

    private int position;
    private Film film;

    private ImageView filmImageView;
    private EditText titleEditText, directorEditText, yearEditText, urlEditText, commentsEditText;
    private Spinner genreSpinner, formatSpinner;
    private Button captureButton, selectButton;
    ToastCustom tc = new ToastCustom();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_edit_activity);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Filmoteca Editor");
        }

        position = getIntent().getIntExtra("FILM_POSITION", -1);

        if (position < 0 || position >= FilmDataSource.films.size()) {
            tc.showCustomToast(this, "Película no encontrada");
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
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Editar Pelicula")
                    .setMessage("¿Deseas guardar la pelicula?")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            saveChanges();
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("FILM_POSITION", position);
                            setResult(RESULT_OK, resultIntent);
                            finish();
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        });

        cancelButton.setOnClickListener(v -> {
            tc.showCustomToast(this, "Los cambios han sido cancelados");
            setResult(RESULT_CANCELED);
            finish();
        });

        captureButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(cameraIntent);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA}, 101);
            }
        });

        selectButton.setOnClickListener(v -> tc.showCustomToast(this, "Funcionalidad no Implementada"));
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
        captureButton = findViewById(R.id.captureImageButton);
        selectButton = findViewById(R.id.selectImageButton);
    }

    private void setupSpinners() {
        String[] genres = {"Acción", "Comedia", "Drama", "Ciencia Ficción", "Terror"};
        String[] formats = {"Digital", "DVD", "Blu-Ray"};

        ArrayAdapter<String> genreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genres);
        genreSpinner.setAdapter(genreAdapter);

        ArrayAdapter<String> formatAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, formats);
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
                tc.showCustomToast(this, "Rellena todos los campos");
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
            tc.showCustomToast(this, "Pon un año valido");
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(cameraIntent);
            } else {
                tc.showCustomToast(this, "Se necesita permiso de la camara");
            }
        }
    }

}
