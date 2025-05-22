package com.example.mob_dev_tp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BaseSQLiteAdapter extends SQLiteOpenHelper {
    private Context context;
    private  static final String DATABASE_NAME = "USERS_DB";
    public   static final String TABLE_NAME = "Users";
    public   static final String COLUMN_ID = "id";
    public   static final String COLUMN_USERNAME = "username";
    public   static final String COLUMN_PASSWORD = "password";
    public BaseSQLiteAdapter(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     String query = "CREATE TABLE "+TABLE_NAME
                    + " ( "+ COLUMN_ID  +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_USERNAME  + " TEXT, "
                    + COLUMN_PASSWORD  +" TEXT );"
                    ;

     db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public void addUser(String name , String password){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put(COLUMN_USERNAME,name);
         values.put(COLUMN_PASSWORD, password);

         long result = db.insert(TABLE_NAME,null,values);

         if (result==-1) {
             Toast.makeText(context, "Failed To insert", Toast.LENGTH_SHORT).show();
         }else {
             Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
         }
    }
    public Cursor getAllUsers(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
       }
        return cursor;
    }
    public void updateUser(String oldName, String newName){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,newName);

        long result = db.update(TABLE_NAME, values , COLUMN_USERNAME+" =?", new String[]{oldName});
        if (result==-1) {
            Toast.makeText(context, "Failed To update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteUser (String name){
        SQLiteDatabase db = getWritableDatabase();

        long result = db.delete(TABLE_NAME, COLUMN_USERNAME+" =?", new String[]{name});
        if (result==-1) {
            Toast.makeText(context, "Failed To delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}


