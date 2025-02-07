package com.example.filmoteca_lucascandela;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "filmoteca.db";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateDatabase = "Create Table usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario TEXT UNIQUE," +
                "contrasena TEXT)";
        db.execSQL(CreateDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }


    public boolean registerUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String query = "INSERT INTO usuarios(usuario, contrasena) VALUES('" + username + "', '" + password + "')";
            db.execSQL(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM usuarios WHERE usuario = ? AND contrasena = ?";

        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean exists = cursor.moveToFirst() && cursor.getInt(0) > 0;
        cursor.close();
        db.close();

        return exists;
    }

    public boolean userExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM usuarios WHERE usuario = ?";

        Cursor cursor = db.rawQuery(query, new String[]{username});
        boolean exists = cursor.moveToFirst() && cursor.getInt(0) > 0;

        cursor.close();
        db.close();

        return exists;
    }

}
