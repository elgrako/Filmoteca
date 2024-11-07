package com.example.filmoteca_lucascandela;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class FilmAdapter extends ArrayAdapter<Film> {


    public FilmAdapter(Context context, List<Film> films) {
        super(context,0, films);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_film, parent, false);
        }
        Film currentFilm = getItem(position);

        ImageView filmImageView = convertView.findViewById(R.id.filmImage);
        TextView filmTitleView = convertView.findViewById(R.id.filmTitle);
        TextView filmDirectorView = convertView.findViewById(R.id.filmDirector);

        filmImageView.setImageResource(currentFilm.getImageResId());
        filmTitleView.setText(currentFilm.getTitle());
        filmDirectorView.setText(currentFilm.getDirector());


        return convertView;
    }
}
