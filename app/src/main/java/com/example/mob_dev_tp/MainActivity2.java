package com.example.mob_dev_tp;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private TextView genderText, ageText, heightText, weightText, imcText, rangeText, interpretationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize views
        genderText = findViewById(R.id.gender);
        ageText = findViewById(R.id.age);
        heightText = findViewById(R.id.height);
        weightText = findViewById(R.id.weight);
        imcText = findViewById(R.id.imc);
        rangeText = findViewById(R.id.range);
        interpretationText = findViewById(R.id.interpretation);

        // Process intent data
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Extract data from intent
            String gender = extras.getString("gender", "");
            String age = extras.getString("age", "");
            String height = extras.getString("height", "");
            String weight = extras.getString("weight", "");
            String imc = extras.getString("imc", "0.0");

            // Display user data
            genderText.setText(getString(R.string.gender_format, gender));
            ageText.setText(getString(R.string.age) + " " + age);
            heightText.setText(getString(R.string.height_format, height));
            weightText.setText(getString(R.string.weight_format, weight));
            imcText.setText(getString(R.string.imc_format, imc));


            setIMCInterpretation(imc);
        }
    }


    private void setIMCInterpretation(String imcValue) {

            double imc = Double.parseDouble(imcValue);

            if (imc < 18.5) {
                setInterpretationUI("Maigreur", Color.parseColor("#ffc107"));
            } else if (imc >= 18.5 && imc <= 24.9) {
                setInterpretationUI("Normale", Color.parseColor("#28a745"));
            } else {
                setInterpretationUI("Surpoids", Color.parseColor("#dc3545"));
            }


    }

    private void setInterpretationUI(String text, int color) {
        rangeText.setText(text);
        rangeText.setTextColor(color);
        interpretationText.setTextColor(color);
    }
}