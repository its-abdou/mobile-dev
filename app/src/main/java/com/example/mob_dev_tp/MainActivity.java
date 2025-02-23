package com.example.mob_dev_tp;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   Button incrementButton;
   Button decreaseButton;
   TextView text1;
   int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        incrementButton = findViewById(R.id.incrementButton);
        decreaseButton = findViewById(R.id.decrementButton);
        text1 = findViewById(R.id.text);
        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter<= 0){
                    decreaseButton.setEnabled(false);
                }
                else{
                    counter --;
                    text1.setText("Vous avez "+counter+" points actuellement");}

                }
        });
        };

    public void increment(View view) {
        counter++;
        if (counter > 0) {
            decreaseButton.setEnabled(true);
        }
        text1.setText("Vous avez "+counter+" points actuellement");
    }
    }
