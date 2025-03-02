package com.example.mob_dev_tp;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText eurValue, daValue;
    private RadioGroup conversionGroup;
    private ImageButton flag1, flag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      eurValue = findViewById(R.id.eurValue);
      daValue = findViewById(R.id.daValue);
      conversionGroup = findViewById(R.id.conversionGroup);
      flag1 = findViewById(R.id.flag1);
      flag2 = findViewById(R.id.flag2);


        flag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String daValueStr = daValue.getText().toString();
                if (daValueStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Veuillez entrer une valeur.", Toast.LENGTH_SHORT).show();
                    return;
                }
                float valeurDinar = Float.parseFloat(daValueStr);
                float valeurEuro = dinarsToEuro(valeurDinar);
                eurValue.setText(String.valueOf(valeurEuro));


            }
        });



        flag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eurValueStr = eurValue.getText().toString();
                if (eurValueStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Veuillez entrer une valeur.", Toast.LENGTH_SHORT).show();
                    return;
                }
                float valeurEuro = Float.parseFloat(eurValueStr);
                float valeurDinar = euroToDinar(valeurEuro);
                daValue.setText(String.valueOf(valeurDinar));
            }
        });
    }
    private float dinarsToEuro(float valeurDinar) {
        double dinarPrice = 0;
        int selectedId = conversionGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Veuillez sélectionner une opération.", Toast.LENGTH_SHORT).show();

        }
        if (selectedId == R.id.parallèle) {
            dinarPrice = 0.004;

        } else if (selectedId == R.id.officiel) {
            dinarPrice = 0.0072;
        }
        return (float) (valeurDinar * dinarPrice);
    };

    private float euroToDinar(float valeurEuro) {
        double euroPrice = 0;
        int selectedId = conversionGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Veuillez sélectionner une opération.", Toast.LENGTH_SHORT).show();

        }
        if (selectedId == R.id.parallèle) {
            euroPrice = 250;

        } else if (selectedId == R.id.officiel) {
            euroPrice = 138.15;
        }
        return (float) (valeurEuro * euroPrice);
    };
   public void ResetFields(View view){
       eurValue.setText("");
       daValue.setText("");
       conversionGroup.clearCheck();
   }
}

