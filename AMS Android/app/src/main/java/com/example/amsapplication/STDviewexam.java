package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;

public class STDviewexam extends AppCompatActivity {

    ListView l1;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdviewexam);
        l1=findViewById(R.id.list7);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }
}