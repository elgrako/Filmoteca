package com.example.filmoteca_lucascandela;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class FilmListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_film);

        List<Film> filmList = new ArrayList<>();
        filmList.add(new Film("Interestellar", "Christopher Nolan", R.drawable.interstellar));
        filmList.add(new Film("Back to the future", "Robert Zemeckis", R.drawable.backtothefuture));
        filmList.add(new Film("Gladiator", "Ridley Scott", R.drawable.gladiator));
        filmList.add(new Film("Cadena Perpetua", "Frank Darabont", R.drawable.cadenaperpetua));
        filmList.add(new Film("El Resplandor", "Stanley Kubrick", R.drawable.elresplandor));
        filmList.add(new Film("Robot salvaje", "Chris Sanders", R.drawable.robotsalvaje));
        filmList.add(new Film("Pesadilla antes de Navidad", "Tim Burton", R.drawable.pesadillaantesdenavidad));
        filmList.add(new Film("Los Cazafantasmas", "Ivan Reitman", R.drawable.cazafantasmas));
        filmList.add(new Film("Del revés 2", "Kelsey Mann", R.drawable.delreves2));
        filmList.add(new Film("Saturday Night", "Jason Reitman", R.drawable.saturdaynight));
        filmList.add(new Film("Scream: Vigila quién llama", "Wes Craven", R.drawable.scream));
        filmList.add(new Film("Deadpool y Lobezno", "Shawn Levy", R.drawable.deadpoollobezno));
        filmList.add(new Film("Transformers One", "Josh Cooley", R.drawable.transformersone));
        filmList.add(new Film("Wicked", "Jon M.Chu", R.drawable.wicked));
        filmList.add(new Film("MaXXXine", "Ti West", R.drawable.maxxxine));


        FilmAdapter adapter = new FilmAdapter(this, filmList);
        ListView listView = findViewById(R.id.filmListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FilmListActivity.this, FilmDataActivity.class);
                intent.putExtra("FILM_POSITION", position);
                startActivity(intent);
            }
        });
    }
}
