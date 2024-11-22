package com.example.filmoteca_lucascandela;

import static com.example.filmoteca_lucascandela.FilmDataSource.films;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FilmListActivity extends AppCompatActivity {
    ListView filmListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peliculas);

        if( getSupportActionBar() != null){
            getSupportActionBar().setTitle("Filmoteca");
        }

        FilmDataSource.Initialize();

        filmListView = findViewById(R.id.filmListView);

        FilmAdapter adapter = new FilmAdapter(this, R.layout.item_film, films);
        filmListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        registerForContextMenu(filmListView);


        filmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FilmListActivity.this, FilmDataActivity.class);
                intent.putExtra("FILM_POSITION", position);
                startActivityForResult(intent, 1);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ((FilmAdapter) filmListView.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_bar_about) {
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
            return true;
        } else if (item.getItemId() == R.id.menu_addfilm) {
            addFilm();
            ((FilmAdapter) filmListView.getAdapter()).notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(android.view.ContextMenu menu, View v, android.view.ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
public boolean onContextItemSelected(MenuItem item) {
    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

    if (info != null) {
        int position = info.position;

        if (item.getItemId() == R.id.menu_delete) {
            new AlertDialog.Builder(this)
                    .setTitle("Borrar Pelicula")
                    .setMessage("Estas seguro de que quieres borrar esta pelicula?")
                    .setPositiveButton("Si", (dialogInterface, x) -> {
                        films.remove(position);
                        ((FilmAdapter) filmListView.getAdapter()).notifyDataSetChanged();
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        }
    }
    return super.onContextItemSelected(item);
}


    private void addFilm() {
        Film film = new Film(R.drawable.interstellar, "Titulo", "Director", 2024,
                1,2, "https://www.imdb.com/title/tt0816692/", "Comentarios");
        films.add(film);
        ((FilmAdapter) filmListView.getAdapter()).notifyDataSetChanged();
    }
}