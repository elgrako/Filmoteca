package com.example.filmoteca_lucascandela;

import android.content.Context;
import android.util.Log;

import java.io.*;
import java.util.ArrayList;

public class FilmFiles {
    private final String fileName = "films.txt";
    private final String DELIMITER = "||";
    private final String LINE_BREAK = "\n";

    public void saveFilms(Context context, ArrayList<Film> films) {
        StringBuilder data = new StringBuilder();

        for (Film film : films) {
            data.append(film.getImageResId()).append(DELIMITER)
                    .append(film.getTitle()).append(DELIMITER)
                    .append(film.getDirector()).append(DELIMITER)
                    .append(film.getYear()).append(DELIMITER)
                    .append(film.getGenre()).append(DELIMITER)
                    .append(film.getFormat()).append(DELIMITER)
                    .append(film.getImdbUrl()).append(DELIMITER)
                    .append(film.getComments()).append(LINE_BREAK);
        }

        try (FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)) {
            fos.write(data.toString().getBytes());
            Log.i("FilmFiles", "Archivo guardado correctamente.");
        } catch (IOException e) {
            Log.e("FilmFiles", "Error al guardar el archivo: " + e.getMessage(), e);
        }
    }

    public ArrayList<Film> readFilms(Context context) {
        ArrayList<Film> films = new ArrayList<>();
        File file = new File(context.getFilesDir(), fileName);

        if (!file.exists()) {
            Log.w("FilmFiles", "El archivo no existe.");
            return films;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|\\|");
                if (fields.length == 8) {
                    Film film = new Film(
                            Integer.parseInt(fields[0]),
                            fields[1],
                            fields[2],
                            Integer.parseInt(fields[3]),
                            Integer.parseInt(fields[4]),
                            Integer.parseInt(fields[5]),
                            fields[6],
                            fields[7]
                    );
                    films.add(film);
                }
            }
            Log.i("FilmFiles", "Archivo leído correctamente.");
        } catch (IOException e) {
            Log.e("FilmFiles", "Error al leer el archivo: " + e.getMessage(), e);
        }
        return films;
    }
}
