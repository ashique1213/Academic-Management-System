package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class addfeedback extends AppCompatActivity {
    EditText e1;
    Button b1;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfeedback);
        e1=findViewById(R.id.editTextTextPersonName21);
        b1=findViewById(R.id.button14);
    }
}