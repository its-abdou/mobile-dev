package com.example.mob_dev_tp;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  Button button;
   TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        text = findViewById(R.id.text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("Hello, World!");
                Toast MsgToast = Toast.makeText(MainActivity.this, "message à afficher", Toast.LENGTH_SHORT);
                MsgToast.setGravity(Gravity.CENTER, 0,0);
                MsgToast.show();
            }
        });
    };


}
