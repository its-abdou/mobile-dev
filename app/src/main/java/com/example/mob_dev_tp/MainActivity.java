package com.example.mob_dev_tp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sound = MediaPlayer.create(getApplicationContext(),R.raw.accueil);
        sound.start();

        Thread thread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(10000);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    Uri uri = Uri.parse("https://www.google.com/");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }


            }
        };
             thread.start();
        };



    @Override
    protected void onPause() {
        super.onPause();
        sound.release();
    }
}



