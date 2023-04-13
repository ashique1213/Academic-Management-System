package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class addstudymat extends AppCompatActivity {
    EditText e1,e2,e3;
    Spinner s1;
    Button b1;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudymat);
        e1=findViewById(R.id.editTextTextPersonName18);
        e2=findViewById(R.id.editTextTextPersonName19);
        e3=findViewById(R.id.editTextTextPersonName20);
        b1=findViewById(R.id.button12);
        s1=findViewById(R.id.spinner7);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}