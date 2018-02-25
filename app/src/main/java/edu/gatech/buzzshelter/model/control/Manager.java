package edu.gatech.buzzshelter.model.control;

import android.util.Log;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        shelters = new LinkedList<>();
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

    public boolean parseShelter(InputStream stream)
    {
        try
        {
            CSVParser parser = new CSVParser(new InputStreamReader(stream), CSVFormat.EXCEL);
            List<CSVRecord> records = parser.getRecords();

            for(CSVRecord record : records)
                Log.d("Test: ", record.toString());
        }
        catch (IOException e)
        {
            Log.e("Exception: ", e.getMessage());
            return false;
        }

        return true;
    }
}
