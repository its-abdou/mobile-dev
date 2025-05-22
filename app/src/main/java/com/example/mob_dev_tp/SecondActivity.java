package com.example.mob_dev_tp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {

    TextView Name, spéciality, age, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //init views
        Name = findViewById(R.id.Name);
        spéciality = findViewById(R.id.spéciality);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);

        // get Main Activity data
        Bundle extras = getIntent().getExtras();
        Name.setText("Name: "+ extras.getString("name"));
        spéciality.setText("Spéciality: "+ extras.getString("specialty"));
        age.setText("Age: "+ extras.getString("age"));
        gender.setText("Sex: "+ extras.getString("gender"));

    }
}