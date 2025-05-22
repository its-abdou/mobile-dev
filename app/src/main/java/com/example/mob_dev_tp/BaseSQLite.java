package com.example.mob_dev_tp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseSQLite extends SQLiteOpenHelper {
    private  static final String DATABASE_NAME = "livreDB";
    public   static final String TABLE_NAME = "Livre";
    public   static final String COLUMN_ID = "id";
    public   static final String COLUMN_TITLE = "title";
    public   static final String COLUMN_AUTHOR = "author";
    public   static final String COLUMN_YEAR = "year";
    public BaseSQLite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     String query = "CREATE TABLE " + TABLE_NAME +
             " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
             COLUMN_TITLE + " TEXT, " +
             COLUMN_AUTHOR + " TEXT, " +
             COLUMN_YEAR + " INTEGER);";

     db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }
}
