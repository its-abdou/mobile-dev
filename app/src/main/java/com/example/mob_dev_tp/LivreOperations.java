package com.example.mob_dev_tp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class LivreOperations {
    private BaseSQLite helper;
    private SQLiteDatabase db;
    private static final String TABLE_NAME = "Livre";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_YEAR = "year";

    Context context;

    public LivreOperations(Context context, BaseSQLite helper) {
        this.context = context;
        this.helper = helper;
    }

    void addBook(LivreModel livre) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, livre.getTitle());
        values.put(COLUMN_AUTHOR, livre.getAuthor());
        values.put(COLUMN_YEAR, livre.getYear());

        long result = db.insert(TABLE_NAME, null, values);

        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
