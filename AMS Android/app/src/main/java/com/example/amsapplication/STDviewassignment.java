package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class STDviewassignment extends AppCompatActivity {
    EditText e1;
    Button  b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdviewassignment);
        e1=findViewById(R.id.editTextTextPersonName39);

    }
}