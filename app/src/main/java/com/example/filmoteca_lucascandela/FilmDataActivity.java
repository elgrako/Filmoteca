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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_data_activity);

        Intent intent = getIntent();
        position = intent.getIntExtra("FILM_POSITION", 0);
        Film film = FilmDataSource.films.get(position);

        TextView titleTextView = findViewById(R.id.titleTextViewFilmActivitytitulo);
        TextView directorTextView = findViewById(R.id.directorTextViewFilmActivity);
        TextView yearTextView = findViewById(R.id.yearTextViewFilmActivity);
        TextView genreTextView = findViewById(R.id.genreTextViewFilmActivity);
        TextView formatTextView = findViewById(R.id.formatTextViewFilmActivity);
        TextView descriptionTextView = findViewById(R.id.descriptionTextViewFilmActivity);
        ImageView imageView = findViewById(R.id.imageViewFilmActivity);
        Button imdbButton = findViewById(R.id.imdbBtn);
        Button backButton = findViewById(R.id.backBtn);
        Button editButton = findViewById(R.id.editBtn);

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

        backButton.setOnClickListener(v -> {
            finish();
        });

        editButton.setOnClickListener(v -> {
            Intent editIntent = new Intent(FilmDataActivity.this, FilmEditActivity.class);
            editIntent.putExtra("FILM_POSITION", position);
            startActivity(editIntent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //case R.id.action_about:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                return true;

            //case R.id.action_add_film:
                Film newFilm = new Film(
                        "Nueva Película",
                        "Director por Defecto",
                        2024,
                        Film.GENRE_ACTION,
                        Film.FORMAT_DIGITAL,
                        "Descripción por defecto",
                        R.drawable.default_image,
                        "https://www.imdb.com"
                );
                FilmDataSource.films.add(newFilm);

                Intent refreshIntent = new Intent(this, MainActivity.class);
                refreshIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(refreshIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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
}
