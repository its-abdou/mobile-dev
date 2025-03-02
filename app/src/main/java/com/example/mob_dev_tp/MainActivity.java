package com.example.mob_dev_tp;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText num1, num2;
    private RadioGroup operationGroup;
    private TextView result;
    private Button validate, reset, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        operationGroup = findViewById(R.id.operationGroup);
        result = findViewById(R.id.result);
        validate = findViewById(R.id.validate);
        reset = findViewById(R.id.reset);
        exit = findViewById(R.id.exit);

        // Handle Calculation
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });


        // Handle Exit
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Closes the app
            }
        });
    }

    private void calculateResult() {
        String val1 = num1.getText().toString();
        String val2 = num2.getText().toString();

        if (val1.isEmpty() || val2.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer les deux valeurs.", Toast.LENGTH_SHORT).show();
            return;
        }

        double number1 = Double.parseDouble(val1);
        double number2 = Double.parseDouble(val2);
        double res = 0;

        int selectedId = operationGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Veuillez sélectionner une opération.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedId == R.id.addition) {
            res = number1 + number2;
        } else if (selectedId == R.id.subtraction) {
            res = number1 - number2;
        } else if (selectedId == R.id.multiplication) {
            res = number1 * number2;
        } else if (selectedId == R.id.division) {
            if (number2 == 0) {
                Toast.makeText(this, "Division par zéro impossible!", Toast.LENGTH_SHORT).show();
                return;
            }
            res = number1 / number2;
        }

        result.setText("Résultat : " + res);
    }

    // Handle Reset
    public void resetFields(View view) {
        num1.setText("");
        num2.setText("");
        operationGroup.clearCheck();
        result.setText("Résultat :");
    }
}

