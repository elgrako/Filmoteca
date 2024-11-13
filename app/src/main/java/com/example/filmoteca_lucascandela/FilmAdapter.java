package com.example.filmoteca_lucascandela;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FilmAdapter extends ArrayAdapter<Film> {
    private int fResource;
    private List<Film> myfilms;

    public FilmAdapter(Context context, int resource, List<Film> objects) {
        super(context, resource, objects);
        fResource = resource;
        myfilms = objects;
    }

    private static class ViewHolder {
        TextView filmTitleView;
        TextView filmDirectorView;
        ImageView filmImageView;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater myInflater = LayoutInflater.from(getContext());
            convertView = myInflater.inflate(fResource, parent, false);

            holder = new ViewHolder();
            holder.filmTitleView = convertView.findViewById(R.id.titulo);
            holder.filmDirectorView = convertView.findViewById(R.id.directorFilmActivity);
            holder.filmImageView = convertView.findViewById(R.id.imageView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Film filmActual = myfilms.get(position);

        holder.filmTitleView.setText(filmActual.getTitle());
        holder.filmDirectorView.setText(filmActual.getDirector());
        holder.filmImageView.setImageResource(filmActual.getImageResId());

        return convertView;
    }
}
