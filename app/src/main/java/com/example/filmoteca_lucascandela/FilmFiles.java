package com.example.filmoteca_lucascandela;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FilmFiles {
    ToastCustom tc = new ToastCustom();
    private final String fileName = "films.txt";

    private void saveFilms(Context context, String data) {
        try (FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)) {
            fos.write(data.getBytes());

            tc.showCustomToast(null, "Archivo guardado correctamente");
        } catch (IOException e) {
            Log.e("MiAplicación", "Error al guardar el archivo: " + fileName, e);
            tc.showCustomToast(null, "Error al guardar los ficheros");
        }
    }

    private void readFilms(Context context, String data){
        File file = new File(context.getFilesDir(), fileName);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes());
            tc.showCustomToast(null, "Archivo guardado correctamente");
        } catch (IOException e) {
            Log.e("MiAplicación", "Error al guardar el archivo: " + fileName, e);
            tc.showCustomToast(null, "Error al guardar los ficheros");
        }
    }

}
