package edu.gatech.buzzshelter.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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

        Button register = findViewById(R.id.registerButton);
        Button cancel = findViewById(R.id.cancelButton);

        Spinner types = findViewById(R.id.accSpinner);

        /* All the text fields */
        EditText nameText = findViewById(R.id.nameText);
        EditText userText = findViewById(R.id.userText);
        EditText passText = findViewById(R.id.passText);
        EditText retypeText = findViewById(R.id.retypeText);
        EditText emailText = findViewById(R.id.emailText);

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

        register.setOnClickListener(v -> {
            /* Extract the information */

            String name = nameText.getText().toString();
            String username = userText.getText().toString();

            String password = passText.getText().toString();
            String retype = retypeText.getText().toString();

            String email = emailText.getText().toString();

            /* Make sure everything is filled */

            if(name.isEmpty() || username.isEmpty() ||
                    password.isEmpty() || retype.isEmpty() || email.isEmpty())
            {
                Snackbar.make(findViewById(android.R.id.content),
                        "Not all entries have been filled", Snackbar.LENGTH_SHORT)
                        .show();

                String msg = "Not filled";

                /* Mark which entries are not filled */

                if(name.isEmpty())
                    nameText.setError(msg);

                if(username.isEmpty())
                    userText.setError(msg);

                if(password.isEmpty())
                    passText.setError(msg);

                if(retype.isEmpty())
                    retypeText.setError(msg);

                if(email.isEmpty())
                    emailText.setError(msg);

                return;
            }

            /* Make sure password == retype */

            if(!password.equals(retype))
            {
                Snackbar.make(findViewById(android.R.id.content),
                        "Retype password does not match password", Snackbar.LENGTH_SHORT)
                        .show();

                return;
            }

            /* Pass to model */
            Manager manager = Manager.getInstance();
            boolean result = manager.register((PersonType)types.getSelectedItem(),
                    name, username, password, email);

            /* Perform transition or error reporting */
            if(!result)
            {
                /* Not very descriptive - TODO have error reporting codes from
                   manager.register(...) instead of boolean */

                Snackbar.make(findViewById(android.R.id.content),
                        "Registration failed", Snackbar.LENGTH_SHORT)
                        .show();

                return;
            }

            /* Perform auto-login */
            result = manager.login(username, password);

            if(!result)
            {
                Snackbar.make(findViewById(android.R.id.content),
                        "Auto-login failed", Snackbar.LENGTH_SHORT)
                        .show();

                return;
            }

            /* Success! */
            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);
        });


        cancel.setOnClickListener(v -> {
            Intent back = new Intent(this, WelcomeActivity.class);
            startActivity(back);
        });
    }

}
