package com.example.mob_dev_tp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LivreOperations {
    private BaseSQLite helper;
    private SQLiteDatabase db;


    Context context;

    public LivreOperations(Context context, BaseSQLite helper) {
        this.context = context;
        this.helper = helper;
    }

    public void addBook(LivreModel livre) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BaseSQLite.COLUMN_TITLE, livre.getTitle());
        values.put(BaseSQLite.COLUMN_AUTHOR, livre.getAuthor());
        values.put(BaseSQLite.COLUMN_YEAR, livre.getYear());

        long result = db.insert(BaseSQLite.TABLE_NAME, null, values);

        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public List<LivreModel>getLivres(){
        db = helper.getReadableDatabase();
        ArrayList livreList = new ArrayList<LivreModel>();

       Cursor cs = db.query(BaseSQLite.TABLE_NAME,null,null,null,null,null,null);
       if (cs.moveToFirst()){
           do {

           }while (cs.moveToNext());
       }
       db.close();
       return null;
    }
}
