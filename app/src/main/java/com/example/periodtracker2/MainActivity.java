package com.example.periodtracker2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity

{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public  void startdbapp(View view)
    {
        new dbmanage(this);
        startActivity(new Intent(this, insertdate.class));
    }

    public  void startdbapp2(View view)
    {
        startActivity(new Intent(this, Symptoms.class));
    }
}