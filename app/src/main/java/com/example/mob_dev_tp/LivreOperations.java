package com.example.mob_dev_tp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LivreOperations {
    private BaseSQLite helper;
    private SQLiteDatabase db; //db : Un objet représentant une connexion active à la base de données.
                               //C’est lui que tu utilises pour faire des requêtes (query,insert,update,delete)
    Context context; // Contexte de l'application pour afficher des messages

    public LivreOperations(Context context) {
        this.context = context;
        this.helper = new BaseSQLite(context);
    }

    public void ajouterLivre(LivreModel livre){
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BaseSQLite.COLUMN_ISBN,livre.getIsbn());
        cv.put(BaseSQLite.COLUMN_NAME, livre.getNom());

        long result = db.insert(BaseSQLite.TABLE_NAME, null, cv);

        if(result == -1){
            Toast.makeText(context, "Erreur d'insertion", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Insertion reussite", Toast.LENGTH_SHORT).show();
        }
        helper.close();
    }
    //afficher tout les livres
    public List<LivreModel> afficherLivres(){
        db = helper.getReadableDatabase();

        Cursor c = db.query(BaseSQLite.TABLE_NAME, null,  null, null, null, null, null);

        List<LivreModel> listeLivres = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                int isbn = c.getInt(1); // isbn
                String nom = c.getString(2); // nom

                LivreModel livre = new LivreModel();
                livre.setIsbn(isbn);
                livre.setNom(nom);

                listeLivres.add(livre);

            } while (c.moveToNext());
        }
        c.close();
        return listeLivres;
    }

    //Afficher un livre
    public LivreModel afficherLivre(LivreModel livre){
        db = helper.getReadableDatabase();

        Cursor c = db.query(BaseSQLite.TABLE_NAME, null, BaseSQLite.COLUMN_NAME + " = ?", new String[]{livre.getNom()},null,null,null);

        if (c.moveToFirst()) {
            int isbn = c.getInt(1); // isbn
            String nom = c.getString(2); // nom

            livre.setIsbn(isbn);
            livre.setNom(nom);

            return livre;
        }
        c.close();
        return null;
    }

    public void updateLivre(LivreModel livre) {
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BaseSQLite.COLUMN_ISBN, livre.getIsbn());
        cv.put(BaseSQLite.COLUMN_NAME, livre.getNom());
        db.update(BaseSQLite.TABLE_NAME, cv, BaseSQLite.COLUMN_ID + " = ?", new String[]{String.valueOf(livre.getId())});
    }

    public void deleteLivre(LivreModel livre) {
        db = helper.getWritableDatabase();
        db.delete(BaseSQLite.TABLE_NAME, BaseSQLite.COLUMN_ID + " = ?", new String[]{String.valueOf(livre.getId())});
    }
}
