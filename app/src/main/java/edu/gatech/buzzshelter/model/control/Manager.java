package edu.gatech.buzzshelter.model.control;

import android.util.Log;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import edu.gatech.buzzshelter.model.auth.Credential;
import edu.gatech.buzzshelter.model.db.Database;
import edu.gatech.buzzshelter.model.db.MemDB;
import edu.gatech.buzzshelter.model.user.Admin;
import edu.gatech.buzzshelter.model.user.Person;
import edu.gatech.buzzshelter.model.user.PersonType;
import edu.gatech.buzzshelter.model.user.Shelter;
import edu.gatech.buzzshelter.model.user.User;

public class Manager
{
    private static final Manager ourInstance = new Manager();
    private Database<Credential, Person> provider;
    private List<Shelter> shelters;

    private Manager()
    {
        provider = new MemDB<>();
    }

    public static Manager getInstance()
    {
        return ourInstance;
    }

    public boolean register(PersonType type,
                 String name, String username, String password, String email)
    {
        Person person;

        switch (type)
        {
            case USER:
                person = new User(name, username, password, email);
                break;
            case ADMIN:
                person = new Admin(name, username, password, email);
                break;
            /* Never called */
            default:
                person = null;
        }

        /* Write to database */
        return provider.put(person.getCred(), person, false);
    }

    public boolean login(String username, String password)
    {
        Credential cred = new Credential(username, password);
        return provider.contains(cred);
    }

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
