package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class addexam extends AppCompatActivity {
    EditText e1,e2;
    Spinner s1;
    Button b1;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexam);
        e1=findViewById(R.id.editTextTextPersonName10);
        e2=findViewById(R.id.editTextTextPersonName11);
        s1=findViewById(R.id.spinner5);
        b1=findViewById(R.id.button7);

    }
}