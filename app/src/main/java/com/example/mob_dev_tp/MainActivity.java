package com.example.mob_dev_tp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void SendMessage(View view){
        Uri uri = Uri.parse("smsto:0791384193");
        Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
        startActivity(intent);
    }
}




