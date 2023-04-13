package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;
import android.widget.Spinner;

public class viewstudents extends AppCompatActivity {
    Spinner s1;
    ListView l1;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstudents);
        s1=findViewById(R.id.spinner2);
        l1=findViewById(R.id.list20);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }
}