package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class addassignment extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addassignment);
        e1=findViewById(R.id.editTextTextPersonName4);
        e2=findViewById(R.id.editTextTextPersonName5);
        e3=findViewById(R.id.editTextTextPersonName6);
        b1=findViewById(R.id.button3);
    }
}