package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class STDviewfeedback extends AppCompatActivity {
    RadioButton r1,r2,r3,r4;
    Button b1,b2;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdviewfeedback);
        r1=findViewById(R.id.radioButton5);
        r2=findViewById(R.id.radioButton6);
        r3=findViewById(R.id.radioButton7);
        r4=findViewById(R.id.radioButton8);
        b1=findViewById(R.id.button21);
        b2=findViewById(R.id.button22);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}