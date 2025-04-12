package com.example.mob_dev_tp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Google(View view){
        Uri uri = Uri.parse("https://www.google.fr/");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
    public void SendMail(View view){
        Uri uri = Uri.parse("mailto:imsipromo23@gmail.com");
        Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
        startActivity(intent);
    }
}




