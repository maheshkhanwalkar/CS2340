package edu.gatech.buzzshelter.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.buzzshelter.R;
import edu.gatech.buzzshelter.model.control.Manager;
import edu.gatech.buzzshelter.model.user.Person;
import edu.gatech.buzzshelter.model.user.PersonType;

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

        Spinner types = null;

        /* All the text fields */
        EditText nameText = null;
        EditText userText = null;
        EditText passText = null;
        EditText retypeText = null;
        EditText emailText = null;

        /*Button register = findViewById(R.id.registerButton);
        Button cancel = findViewById(R.id.cancelButton);

        Spinner types = findViewById(R.id.accSpinner);

        /* All the text fields */
        /*EditText nameText = findViewById(R.id.nameText);
        EditText userText = findViewById(R.id.userText);
        EditText passText = findViewById(R.id.passText);
        EditText retypeText = findViewById(R.id.retypeText);
        EditText emailText = userText;*/

        /* Setup Retype Password */
        retypeText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(!s.toString().equals(passText.getText().toString()))
                {
                    retypeText.setError("Passwords don't match");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        /* Show error, if password is changed after retype pass is entered */
        passText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(!retypeText.getText().toString().equals("")
                        && !s.toString().equals(retypeText.getText().toString()))
                {
                    retypeText.setError("Passwords don't match");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        /* Setup spinner */
        PersonType[] people = PersonType.values();
        ArrayAdapter<PersonType> cs = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, people);

        types.setAdapter(cs);

        /* TODO plan out register */
        register.setOnClickListener(v -> {

        });


        cancel.setOnClickListener(v -> {
            Intent back = new Intent(this, WelcomeActivity.class);
            startActivity(back);
        });
    }

}
