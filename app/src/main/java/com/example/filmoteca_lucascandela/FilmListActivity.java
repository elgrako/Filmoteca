package com.example.filmoteca_lucascandela;

import static com.example.filmoteca_lucascandela.FilmDataSource.films;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class FilmListActivity extends AppCompatActivity {
    ListView filmListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peliculas);

        FilmDataSource.Initialize();

        filmListView = findViewById(R.id.filmListView);

        FilmAdapter adapter = new FilmAdapter(this, R.layout.item_film, films);

        filmListView.setAdapter(adapter);

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

        if (requestCode == 1 && data != null) {
            ((FilmAdapter) filmListView.getAdapter()).notifyDataSetChanged();
        }
    }
}