package com.example.mob_dev_tp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BaseSQLite2 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "livre_db";
    private static final int DATABASE_VERSION = 1;

    // Constantes pour la table et ses colonnes - accessibles publiquement pour être utilisées dans LivreOperations
    private static final String TABLE_NAME = "Livre";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ISBN = "isbn";
    private static final String COLUMN_NAME = "nom";
    private SQLiteDatabase db;

    public BaseSQLite2(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

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

    public long insererLivre(LivreModel Livre){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ISBN, Livre.getIsbn());
        cv.put(COLUMN_NAME, Livre.getNom());
        long result = db.insert(TABLE_NAME,null, cv);
        return result;
    }

    public LivreModel getLivre(int isbn){
        db = this.getReadableDatabase();
        LivreModel livre = new LivreModel();
        Cursor c = db.query(TABLE_NAME, null, COLUMN_ISBN + " = ?", new String[]{String.valueOf(isbn)},null,null,null );//7 argument 3 tawala null daymen w null lwla tselectioni ge3 les colonnes
        if(c.moveToFirst()){
            String name = c.getString(2);
            livre.setNom(name);
            livre.setIsbn(isbn);
            return livre;
        }
        c.close();
        return null;
    }

    public ArrayList<LivreModel> getAllLivres(){
        db = this.getReadableDatabase();
        ArrayList<LivreModel> listLivres = new ArrayList<>();
        LivreModel livre = new LivreModel();
        Cursor c = db.query(TABLE_NAME, null, null, null,null,null,null );//7 argument 3 tawala null daymen w null lwla tselectioni ge3 les colonnes
        if(c.moveToFirst()){
            do{
                int isbn = c.getInt(1);
                String name = c.getString(2);
                livre.setNom(name);
                livre.setIsbn(isbn);
                listLivres.add(livre);
            }while(c.moveToNext());
            return listLivres;
        }
        c.close();
        return null;
    }

    public int updateLivre(int isbn, LivreModel livre){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ISBN, livre.getIsbn());
        cv.put(COLUMN_NAME,livre.getNom());
        int result = db.update(TABLE_NAME, cv, COLUMN_ISBN + " = ?", new String[]{String.valueOf(isbn)});
        return result;
    }

    public int deleteLivre(int isbn){
        db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COLUMN_ISBN + " = ?", new String[]{String.valueOf(isbn)});
        return result;
    }

}
