package com.example.filmoteca_lucascandela;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FilmDataActivity extends AppCompatActivity {

    private int position;
    private TextView titleTextView, directorTextView, yearTextView, genreTextView, formatTextView, descriptionTextView;
    private ImageView imageView;
    private Button imdbButton, backButton, editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_data_activity);

        initialize();

        Intent intent = getIntent();
        position = intent.getIntExtra("FILM_POSITION", -1);
        Film film = FilmDataSource.films.get(position);

        titleTextView.setText(film.getTitle());
        directorTextView.setText(film.getDirector());
        yearTextView.setText(String.valueOf(film.getYear()));
        genreTextView.setText(getGenreString(film.getGenre()));
        formatTextView.setText(getFormatString(film.getFormat()));
        descriptionTextView.setText(film.getComments());
        imageView.setImageResource(film.getImageResId());

        imdbButton.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(film.getImdbUrl()));
            startActivity(browserIntent);
        });

        backButton.setOnClickListener(v -> finish());

        editButton.setOnClickListener(v -> {
            Intent editIntent = new Intent(FilmDataActivity.this, FilmEditActivity.class);
            editIntent.putExtra("FILM_POSITION", position);
            startActivityForResult(editIntent, 2);
        });
    }

    private void initialize() {
        titleTextView = findViewById(R.id.titleTextViewFilmActivitytitulo);
        directorTextView = findViewById(R.id.directorTextViewFilmActivity);
        yearTextView = findViewById(R.id.yearTextViewFilmActivity);
        genreTextView = findViewById(R.id.genreTextViewFilmActivity);
        formatTextView = findViewById(R.id.formatTextViewFilmActivity);
        descriptionTextView = findViewById(R.id.descriptionTextViewFilmActivity);
        imageView = findViewById(R.id.imageViewFilmActivity);
        imdbButton = findViewById(R.id.imdbBtn);
        backButton = findViewById(R.id.backBtn);
        editButton = findViewById(R.id.editBtn);
    }

    private String getGenreString(int genre) {
        switch (genre) {
            case Film.GENRE_ACTION:
                return "Acción";
            case Film.GENRE_COMEDY:
                return "Comedia";
            case Film.GENRE_DRAMA:
                return "Drama";
            case Film.GENRE_SCIFI:
                return "Ciencia Ficción";
            case Film.GENRE_HORROR:
                return "Terror";
            default:
                return "Desconocido";
        }
    }

    private String getFormatString(int format) {
        switch (format) {
            case Film.FORMAT_DVD:
                return "DVD";
            case Film.FORMAT_BLURAY:
                return "Blu-ray";
            case Film.FORMAT_DIGITAL:
                return "Digital";
            default:
                return "Desconocido";
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK) {
            Film film = FilmDataSource.films.get(position);

            titleTextView.setText(film.getTitle());
            directorTextView.setText(film.getDirector());
            yearTextView.setText(String.valueOf(film.getYear()));
            genreTextView.setText(getGenreString(film.getGenre()));
            formatTextView.setText(getFormatString(film.getFormat()));
            descriptionTextView.setText(film.getComments());
            imageView.setImageResource(film.getImageResId());
        }
    }
}
