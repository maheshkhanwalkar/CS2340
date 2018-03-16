package edu.gatech.buzzshelter.model.control;

import android.util.Log;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.buzzshelter.model.user.Shelter;

public class ShelterManager
{
    private List<Shelter> shelters;

    /* Parse shelter information from CSV */
    /* TODO abstract this out (DB ops) */
    public void parseShelter(InputStream stream)
    {
        try
        {
            CSVParser parser = new CSVParser(new InputStreamReader(stream), CSVFormat.EXCEL);
            List<CSVRecord> records = parser.getRecords();

            /* Initialize Shelter array */
            shelters = new ArrayList<>(records.size());

            /* Skip first record (header) */
            for(int i = 1; i < records.size(); i++)
            {
                CSVRecord record = records.get(i);

                int key = Integer.parseInt(record.get(0));
                String name = record.get(1);
                String capacity = record.get(2);
                String restrict = record.get(3);
                double longitude = Double.parseDouble(record.get(4));
                double latitude = Double.parseDouble(record.get(5));
                String address = record.get(6);
                String notes = record.get(7);
                String phone = record.get(8);

                /* Add the data into the list */
                Shelter shelter = new Shelter(key, name, capacity, restrict,
                        longitude, latitude, address, notes, phone);

                shelters.add(shelter);
            }

        }
        catch (IOException e)
        {
            Log.e("Exception", e.getMessage());
        }
    }

    public List<Shelter> getShelters()
    {
        return shelters;
    }
}
