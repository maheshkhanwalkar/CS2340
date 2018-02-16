package edu.gatech.buzzshelter.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.buzzshelter.R;
import edu.gatech.buzzshelter.model.control.Manager;

public class RegisterActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* TODO hook up to UI */
        Button register = null;
        Button cancel = null;

        /* TODO plan out register */
        register.setOnClickListener(v -> {

        });


        cancel.setOnClickListener(v -> {
            Intent back = new Intent(this, WelcomeActivity.class);
            startActivity(back);
        });
    }

}
