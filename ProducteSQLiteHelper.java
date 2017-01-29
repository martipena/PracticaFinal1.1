package com.example.marti.practicafinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProducteSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL per crear la taula de productes
    String sqlCreate = "CREATE TABLE Productes (id INTEGER, tipo TEXT, marca TEXT, nom TEXT, preu INTEGER)";

    public ProducteSQLiteHelper(Context contexto, String nom,
                                SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nom, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //S'executa la sentencia per crear la taula
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {

        //S'elimina la versió anterior de la taula
        db.execSQL("DROP TABLE IF EXISTS Productes");

        //Es crea la nova versió de la taula
        db.execSQL(sqlCreate);
    }
}
