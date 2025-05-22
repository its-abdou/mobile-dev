package com.example.mob_dev_tp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Classe de gestion de la base de données SQLite
 * Cette classe hérite de SQLiteOpenHelper qui facilite la création et la gestion de la BDD
 */
public class BaseSQLite extends SQLiteOpenHelper {
    // Constantes pour la base de données - utilisées pour éviter les erreurs de frappe et faciliter la maintenance
    private static final String DATABASE_NAME = "livre_db";
    private static final int DATABASE_VERSION = 1;

    // Constantes pour la table et ses colonnes - accessibles publiquement pour être utilisées dans LivreOperations
    public static final String TABLE_NAME = "Livre";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ISBN = "isbn";
    public static final String COLUMN_NAME = "nom";


    public BaseSQLite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //db : Un objet représentant une connexion active à la base de données.
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ISBN + " INTEGER, "
                + COLUMN_NAME + " TEXT);"
                ;

        // Exécution de la requête SQL
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }
}
