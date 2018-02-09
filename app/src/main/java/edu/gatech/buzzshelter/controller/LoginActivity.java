package edu.gatech.buzzshelter.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.buzzshelter.R;

public class LoginActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button login = findViewById(R.id.loginButton);
        Button cancel = findViewById(R.id.cancelButton);

        /* Implement cancel feature */
        cancel.setOnClickListener(v -> {
            Intent back = new Intent(this, WelcomeActivity.class);
            startActivity(back);
        });

        /* Implement login feature */
        login.setOnClickListener(v -> {
            EditText userText = findViewById(R.id.userText);
            EditText passText = findViewById(R.id.passText);

            InputMethodManager manager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);

            /* Hide the 'input' virtual keyboard */
            if(getCurrentFocus() != null && manager != null)
            {
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }

            /* Hardcoded success */
            if(userText.getText().toString().equals("user")
                    && passText.getText().toString().equals("pass"))
            {
                /* TODO */
            }
            else
            {
                Snackbar.make(findViewById(android.R.id.content),
                        "Username or password incorrect", Snackbar.LENGTH_SHORT)
                        .show();

                /* Only clear out password, since that's most likely to be wrong
                 * as the user can't visually "see" the password */

                passText.setText("");
            }
        });
    }

}
