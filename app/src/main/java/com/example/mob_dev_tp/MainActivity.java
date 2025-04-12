package com.example.mob_dev_tp;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView resultText;
    EditText inputValue;
    Spinner convertFromSpinner , convertToSpinner;
    String fromUnit, toUnit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        initViews();

        // Setup spinners
        setupSpinners();

    }
    private void initViews(){
        resultText = findViewById(R.id.resultText);
        convertToSpinner = findViewById(R.id.convertTo);
        convertFromSpinner = findViewById(R.id.convertFrom);
        inputValue = findViewById(R.id.inputValue);
    }
    private void setupSpinners(){
        ArrayAdapter<CharSequence> unitAdapter = ArrayAdapter.createFromResource(this,R.array.Unit, android.R.layout.simple_spinner_item);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Convert from spinner
        convertFromSpinner.setAdapter(unitAdapter);
        convertFromSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                fromUnit = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {

                fromUnit = unitAdapter.getItem(0).toString();
            }
        });

        // Convert to spinner
        convertToSpinner.setAdapter(unitAdapter);
        convertToSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                toUnit = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {

                toUnit = unitAdapter.getItem(0).toString();
            }
        });
    }
    public void convert(View view){
        try {
            double input = Double.parseDouble(inputValue.getText().toString());
            double result = 0;

            switch (fromUnit) {
                case "Celsius":
                    switch (toUnit) {
                        case "Celsius":
                            result = input; // No conversion
                            break;
                        case "Fahrenheit":
                            result = (input * 9/5) + 32;
                            break;
                    }
                    break;

                case "Fahrenheit":
                    switch (toUnit) {
                        case "Celsius":
                            result = (input - 32) * 5/9;
                            break;
                        case "Fahrenheit":
                            result = input; // No conversion
                            break;
                    }
                    break;
            }
            resultText.setText(String.format("Result: %.2f °%s", result, toUnit));


        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
        }
    }

}




