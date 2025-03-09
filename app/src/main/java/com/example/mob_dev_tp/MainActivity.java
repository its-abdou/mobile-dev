package com.example.mob_dev_tp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private EditText username, password;
    private TextView attemptNum;
    private String user = "admin", motDePasse = "admin";
    int attempts = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        attemptNum = findViewById(R.id.attemptNum);



    }


    public void Login(View view) {
        if (attempts > 0) {
            if (username.getText().toString().equals(user) && password.getText().toString().equals(motDePasse)) {
                Toast.makeText(this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
            } else {
                attempts--;
                Toast.makeText(this, "Nom d'utilisateur ou mot de passe incorrect.", Toast.LENGTH_SHORT).show();
                attemptNum.setText("Tentatives restantes : " + attempts);
                username.setText("");
                password.setText("");
            }
        }

        if (attempts == 0) {
            login.setEnabled(false); // Disable login button after 5 failed attempts
        }
    }


}

