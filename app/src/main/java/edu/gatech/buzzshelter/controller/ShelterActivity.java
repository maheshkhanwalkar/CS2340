package edu.gatech.buzzshelter.controller;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Optional;

import edu.gatech.buzzshelter.R;
import edu.gatech.buzzshelter.model.control.Manager;
import edu.gatech.buzzshelter.model.user.Shelter;

public class ShelterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter);
        Toolbar toolbar = findViewById(R.id.toolbar);

        TextView restrictions = findViewById(R.id.restrictions);
        TextView capacity = findViewById(R.id.capacity);
        TextView address = findViewById(R.id.address);
        TextView notes = findViewById(R.id.notes);
        Button phone = findViewById(R.id.phone);

        Manager manager = Manager.getInstance();
        int id = getIntent().getIntExtra(ListActivity.ARG_SHELTER_ID, -1);

        /* Fatal error (shouldn't happen) */
        if (id == -1)
            return;

        Optional<Shelter> result = manager.getShelters()
                .stream().filter(x -> x.getKey() == id).findFirst();

        /* Fatal error (shouldn't happen) */
        if(!result.isPresent())
            return;

        Shelter s = result.get();

        /* Set the values appropriately */
        toolbar.setTitle(s.getName());
        restrictions.setText(s.getRestrict());
        capacity.setText(s.getCapacity());
        address.setText(s.getAddress());
        notes.setText(s.getNotes());
        phone.setText(s.getPhone());

        phone.setOnClickListener(v ->
        {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phone.getText()));

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
            }

            startActivity(callIntent);
        });

        /* Set toolbar (after title is set) */
        setSupportActionBar(toolbar);
    }

}
