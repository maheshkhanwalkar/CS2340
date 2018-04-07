package edu.gatech.buzzshelter.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.buzzshelter.R;
import edu.gatech.buzzshelter.model.data.Reservation;
import edu.gatech.buzzshelter.model.facade.DataFacade;

/**
 * Reservation list activity
 */
public class ReserveListActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DataFacade facade = DataFacade.getInstance();
        facade.setup();

        Reservation current = facade.getReservation();

        TextView name = findViewById(R.id.nameView);
        TextView info = findViewById(R.id.detailView);

        Button cancel = findViewById(R.id.cancelButton);
        Button back = findViewById(R.id.backButton);

        /* Handle current status */
        if(current == null)
            setupNone(name, info, cancel);
        else
            extractInfo(name, info, current);

        setupButton(cancel, back, current);
    }

    private void setupNone(TextView name, TextView info, Button cancel)
    {
        name.setText(R.string.none);
        info.setText(R.string.none);

        cancel.setError("Nothing to cancel");
    }

    private void extractInfo(TextView name, TextView info, Reservation current)
    {
        String total = current.getAmt() + " " + current.getType();

        name.setText(current.getShelter());
        info.setText(total);
    }

    private void setupButton(Button cancel, Button back, Reservation current)
    {
        cancel.setOnClickListener(v -> {
            if(current == null)
            {
                Snackbar.make(v, "No reservation to cancel", Snackbar.LENGTH_SHORT)
                        .show();
                return;
            }

            DataFacade facade = DataFacade.getInstance();
            facade.cancel(current.getShelter(), current.getType(), current.getAmt());

            Intent intent = new Intent(this, ReserveListActivity.class);
            startActivity(intent);
        });

        back.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}
