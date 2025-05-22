package com.example.mob_dev_tp;


import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText username_input, password_input, oldName_input, newName_input, delete_input;
    ListView userList;

    ArrayAdapter<String> adapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init Views
        username_input = findViewById(R.id.username_input);
        password_input = findViewById(R.id.password_input);
        oldName_input = findViewById(R.id.oldName_input);
        newName_input = findViewById(R.id.newName_input);
        delete_input = findViewById(R.id.delete_input);
        userList = findViewById(R.id.userList);

        // Create list & adapter

        dataList = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, dataList
        );
       userList.setAdapter(adapter);
    }


    private  void showUsers(){

        // clear old data
        dataList.clear();
        BaseSQLiteAdapter myDB= new BaseSQLiteAdapter(MainActivity.this);
        Cursor cursor = myDB.getAllUsers();
        if (cursor.moveToFirst()){
            do{
                String name = cursor.getString(1);
                String password = cursor.getString(2);

                dataList.add("Name: "+ name +"- Password: "+password );
            }while (cursor.moveToNext());
            cursor.close();
        }

        // tell adapter data changed
        adapter.notifyDataSetChanged();

    }

    public void viewAll(View view){
        showUsers();
    }
    public  void add(View view){
       String username = username_input.getText().toString();
       String password = password_input.getText().toString();

        BaseSQLiteAdapter myDB = new BaseSQLiteAdapter(MainActivity.this);

        myDB.addUser(username, password);

        // Optional Shit
        showUsers();
    }

    public  void update(View view){
        String oldName = oldName_input.getText().toString();
        String newName = newName_input.getText().toString();

        BaseSQLiteAdapter myDB = new BaseSQLiteAdapter(MainActivity.this);

        myDB.updateUser(oldName,newName );

        // Optional Shit
        showUsers();
    }

    public  void delete(View view){
        String name = delete_input.getText().toString();

        BaseSQLiteAdapter myDB = new BaseSQLiteAdapter(MainActivity.this);

        myDB.deleteUser(name);

        // Optional Shit
        showUsers();
    }


}




