package com.example.mob_dev_tp;

import android.os.Bundle;
import android.view.View;
import android.widget.*;


import androidx.appcompat.app.AppCompatActivity;


public class AddActivity extends AppCompatActivity {
    EditText title, author, year;
    LivreModel livre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // init Views

        title = findViewById(R.id.title_input);
        author = findViewById(R.id.author_input);
        year = findViewById(R.id.year_input);

    }

    public void addBook(View view) {
        BaseSQLite db = new BaseSQLite(AddActivity.this);
        LivreOperations lvo = new LivreOperations(AddActivity.this, db);

        try {
            livre = new LivreModel(-1, title.getText().toString(),
                    author.getText().toString(),
                    Integer.parseInt(year.getText().toString()));
            lvo.addBook(livre);
        } catch (Exception e) {
            Toast.makeText(this, "Invalid input!", Toast.LENGTH_SHORT).show();
        }
    }





}