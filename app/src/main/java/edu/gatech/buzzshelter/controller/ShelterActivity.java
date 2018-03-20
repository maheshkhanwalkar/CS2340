package edu.gatech.buzzshelter.controller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Optional;

import edu.gatech.buzzshelter.R;
import edu.gatech.buzzshelter.model.facade.DataFacade;
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
        TextView available = findViewById(R.id.available);
        TextView address = findViewById(R.id.address);
        TextView notes = findViewById(R.id.notes);
        Button phone = findViewById(R.id.phone);

        DataFacade data = DataFacade.getInstance();
        int id = getIntent().getIntExtra(ListActivity.ARG_SHELTER_ID, -1);

        /* Fatal error (shouldn't happen) */
        if (id == -1)
            return;

        Optional<Shelter> result = data.getShelters()
                .stream().filter(x -> x.getKey() == id).findFirst();

        /* Fatal error (shouldn't happen) */
        if(!result.isPresent())
            return;

        Shelter s = result.get();

        /* Set the values appropriately */
        toolbar.setTitle(s.getName());
        restrictions.setText(s.getRestrict());

        List<Shelter.Capacity> all = s.getCapacity();

        StringBuilder line = new StringBuilder();

        for(int i = 0; i < all.size(); i++)
        {
            Shelter.Capacity item = all.get(i);

            if(item.getCapacity() != -1)
            {
                String cap = Integer.toString(item.getCapacity());

                line.append(cap);
                line.append(" ");
                line.append(item.getCategory());

                /* Add comma, if there are more entries */
                if (i + 1 != all.size())
                    line.append(", ");
            }
            else
            {
                line.append("Unknown (?)");
            }
        }

        capacity.setText(line.toString());
        line.setLength(0);

        for(int i = 0; i < all.size(); i++)
        {
            Shelter.Capacity item = all.get(i);

            if(item.getAvailable() != -1)
            {
                String cap = Integer.toString(item.getCapacity());

                line.append(cap);
                line.append(" ");
                line.append(item.getCategory());

                /* Add comma, if there are more entries */
                if (i + 1 != all.size())
                    line.append(", ");
            }
            else
            {
                line.append("Unknown (?)");
            }
        }

        available.setText(line.toString());

        address.setText(s.getAddress());
        notes.setText(s.getNotes());
        phone.setText(String.format("  %s", s.getPhone()));

        phone.setOnClickListener(v ->
        {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phone.getText()));

            /* Check permissions */
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[] { Manifest.permission.CALL_PHONE }, 1);
            }

            startActivity(callIntent);
        });

        /* Set toolbar (after title is set) */
        setSupportActionBar(toolbar);
    }

}
