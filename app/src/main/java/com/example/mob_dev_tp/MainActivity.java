package com.example.mob_dev_tp;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  ProgressBar progressBar;
  TextView loadingTime;
  EditText name_input, speciality_input, age_input;
  RadioGroup genderGroup;
  String gender;
    boolean enExecution = false;
    int i = 0;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            i++;
            loadingTime.setText("Temps écoulé: "+ i+" seconds");
            progressBar.incrementProgressBy(10);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        loadingTime = findViewById(R.id.loadingTime);
        name_input = findViewById(R.id.name_input);
        speciality_input = findViewById(R.id.speciality_input);
        age_input = findViewById(R.id.age_input);
        genderGroup = findViewById(R.id.genderGroup);


    };


    public void submit(View view) {
        String name = name_input.getText().toString();
        String age = age_input.getText().toString();
        String speciality = speciality_input.getText().toString();

        if (name.isEmpty() || age.isEmpty()|| speciality.isEmpty()) {
            Toast.makeText(MainActivity.this, "Veuillez Remplir tous les champs.", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedId = genderGroup.getCheckedRadioButtonId();
        if (selectedId ==-1){
            Toast.makeText(MainActivity.this, "Veuillez sélectionner une genre.", Toast.LENGTH_SHORT).show();
            return;
        } else if (selectedId== R.id.Male) {
            gender = "Male";
        }else {
            gender = "Female";
        }

        Thread thread = new Thread() {
            public void run() {
                try {
                    for (int j = 0;enExecution && j < 10; j++) {
                        Thread.sleep(1000);
                        Message msg = handler.obtainMessage();
                        handler.sendMessage(msg);
                    }


                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {

                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("name", name);
                        intent.putExtra("specialty", speciality);
                        intent.putExtra("age", age);
                        intent.putExtra("gender", gender);

                        startActivity(intent);

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



