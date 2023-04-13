package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class addquestion extends AppCompatActivity {
    Spinner s1;
    EditText e1,e2,e3,e4,e5,e6;
    Button b1;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquestion);
        s1=findViewById(R.id.spinner6);
        e1=findViewById(R.id.editTextTextPersonName12);
        e2=findViewById(R.id.editTextTextPersonName13);
        e3=findViewById(R.id.editTextTextPersonName14);
        e4=findViewById(R.id.editTextTextPersonName15);
        e5=findViewById(R.id.editTextTextPersonName16);
        e6=findViewById(R.id.editTextTextPersonName17);
        b1=findViewById(R.id.button10);

    }
}