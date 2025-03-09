package com.example.mob_dev_tp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button calculateButton;
    private EditText heightEdit, weightEdit, ageEdit;
    private Spinner genderSpinner, kgSpinner, cmSpinner;
    private String genderSelected, weightUnitSelected, heightUnitSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        initViews();

        // Setup spinners
        setupSpinners();
    }

    private void initViews() {
        calculateButton = findViewById(R.id.Calculate);
        heightEdit = findViewById(R.id.height);
        weightEdit = findViewById(R.id.weight);
        ageEdit = findViewById(R.id.age);
        genderSpinner = findViewById(R.id.gender);
        kgSpinner = findViewById(R.id.kg);
        cmSpinner = findViewById(R.id.cm);


    }

    private void setupSpinners() {
        // Gender spinner
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(
                this, R.array.Gender, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);
        genderSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                genderSelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {

                genderSelected = genderAdapter.getItem(0).toString();
            }
        });

        // Weight unit spinner
        ArrayAdapter<CharSequence> weightAdapter = ArrayAdapter.createFromResource(
                this, R.array.Weight, android.R.layout.simple_spinner_item);
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kgSpinner.setAdapter(weightAdapter);
        kgSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                weightUnitSelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {

                weightUnitSelected = weightAdapter.getItem(0).toString();
            }
        });

        // Height unit spinner
        ArrayAdapter<CharSequence> heightAdapter = ArrayAdapter.createFromResource(
                this, R.array.Height, android.R.layout.simple_spinner_item);
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmSpinner.setAdapter(heightAdapter);
        cmSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                heightUnitSelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                heightUnitSelected = heightAdapter.getItem(0).toString();
            }
        });
    }

    public void Calcule(View view) {
        if (isEmpty(heightEdit) || isEmpty(weightEdit) || isEmpty(ageEdit)) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double height = Double.parseDouble(heightEdit.getText().toString()) / 100;
            double weight = Double.parseDouble(weightEdit.getText().toString());
            double age = Double.parseDouble(ageEdit.getText().toString());

            double imc = calculateIMC(weight, height);


            String formattedIMC = String.format("%.1f", imc);


            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("gender", genderSelected);
            intent.putExtra("age", String.valueOf(age));
            intent.putExtra("height", String.valueOf(height * 100));
            intent.putExtra("weight", String.valueOf(weight));
            intent.putExtra("imc", formattedIMC);
            startActivity(intent);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Veuillez entrer des valeurs numériques valides", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }

    private double calculateIMC(double weight, double heightInMeters) {
        return weight / (heightInMeters * heightInMeters);
    }
}