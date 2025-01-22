package com.example.filmoteca_lucascandela;

import static com.example.filmoteca_lucascandela.FilmDataSource.films;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class FilmListActivity extends AppCompatActivity {
    ListView filmListView;
    ToastCustom tc = new ToastCustom();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peliculas);

        if (getSupportActionBar() != null) {
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
                String deletedPelicula = films.get(position).getTitle();
                new AlertDialog.Builder(this)
                        .setTitle("Borrar Pelicula")
                        .setMessage("Estas seguro de que quieres borrar esta pelicula?")
                        .setPositiveButton("Si", (dialogInterface, x) -> {
                            films.remove(position);
                            ((FilmAdapter) filmListView.getAdapter()).notifyDataSetChanged();
                            showNotification(true, deletedPelicula, false);
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            } else if (item.getItemId() == R.id.share) {
                String peliculaTitle = films.get(position).getTitle();
                checkSmsPermissionAndShare(peliculaTitle);
                return true;
            }
        }
        return super.onContextItemSelected(item);
    }


    private void addFilm() {
        Film film = new Film(R.drawable.pelicula_new, "Titulo", "Director", 2024,
                1, 2, "https://www.imdb.com/title/tt0816692/", "Comentarios");
        films.add(film);
        ((FilmAdapter) filmListView.getAdapter()).notifyDataSetChanged();
        showNotification(false, film.getTitle(), true);
    }

    public void showNotification(boolean msgType, String peliculaTitle, boolean goActivity) {
        String Canal_Id = "filmoteca_canal";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Canal_Id)
                .setSmallIcon(R.drawable.cine_logo);

        if (msgType) {
            builder.setContentTitle("Pelicula Borrada")
                    .setContentText("Se borró la pelicula (" + peliculaTitle + ") de la lista");
        } else {
            NotificationCompat.InboxStyle estilo = new NotificationCompat.InboxStyle();
            builder.setContentTitle("Nueva Pelicula Agregada")
                    .setContentText("Se agregó la pelicula (" + peliculaTitle + ")");

            String[] linea = new String[6];
            linea[0] = "Titulo";
            linea[1] = "Director";
            linea[2] = "2024";
            linea[3] = "Digital";
            linea[4] = "Comedia";
            linea[5] = "Comentarios";

            for (String l : linea) {
                estilo.addLine(l);
            }
            builder.setStyle(estilo);
        }

        builder.setPriority(NotificationCompat.PRIORITY_HIGH);

        if (goActivity) {
            int lastFilm = films.size() - 1;
            Intent intent = new Intent(this, FilmEditActivity.class);
            intent.putExtra("FILM_POSITION", lastFilm);
            PendingIntent pending = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
            builder.setContentIntent(pending);
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel canal = new NotificationChannel(Canal_Id, "Notificaciones Filmoteca", NotificationManager.IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(canal);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 101);
                return;
            }
        }

        Notification notification = builder.build();
        notificationManager.notify((int) System.currentTimeMillis(), notification);
    }

    private void checkSmsPermissionAndShare(String peliculaTitle) {
        if (checkSelfPermission(android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            showShareDialog(peliculaTitle);
        } else {
            requestPermissions(new String[]{android.Manifest.permission.SEND_SMS}, 102);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 102) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                tc.showCustomToast(this, "Permiso concedido. Intenta compartir de nuevo.");
            } else {
                tc.showCustomToast(this, "Permiso denegado.");
            }
        }
    }

    private void showShareDialog(String peliculaTitle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Recomendar Película")
                .setItems(new String[]{"Compartir por SMS", "Compartir por WhatsApp"},
                        (dialog, selected) -> {
                            String phoneNumber = "+34 644465171";
                            String message = "Te recomiendo ver: " + peliculaTitle;

                            if (selected == 0) {
                                enviarViaAppMensajes(phoneNumber, message);
                            } else if (selected == 1) {
                                enviarViaWhatsApp(phoneNumber, message);
                            }
                        });
        builder.create().show();
    }

    private void enviarViaWhatsApp(String phoneNumber, String message) {
        try {
            String url = "https://api.whatsapp.com/send?phone=" + phoneNumber +
                    "&text=" + Uri.encode(message);
            Intent whatsappIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(whatsappIntent);
        } catch (ActivityNotFoundException e) {
            tc.showCustomToast(this, "WhatsApp no esta instalado");
        }
    }

    private void enviarViaAppMensajes(String phoneNumber, String message) {
        Uri smsUri = Uri.parse("smsto:" + phoneNumber);
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, smsUri);
        smsIntent.putExtra("sms_body", message);

        try {
            startActivity(smsIntent);
        } catch (ActivityNotFoundException e) {
            tc.showCustomToast(this, "No hay ninguna aplicación disponible");
        }
    }


}