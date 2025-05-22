package com.example.mob_dev_tp;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  ProgressBar progressBar;
  TextView progressTxt;
    boolean enExecution = false;
    int i = 0;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            i++;
            progressTxt.setText(10*i+"%");
            progressBar.incrementProgressBy(10);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        progressTxt = findViewById(R.id.progressTxt);


    };

    @Override
    protected void onStart() {
        super.onStart();


        Thread thread = new Thread() {
            public void run() {
                try {
                    for (int i = 0;enExecution && i < 10; i++) {
                        Thread.sleep(1000);
                        Message msg = handler.obtainMessage();
                        handler.sendMessage(msg);
                    }


                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        enExecution =true;
        thread.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        enExecution = false;

    }
}



