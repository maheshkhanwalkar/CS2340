package edu.gatech.buzzshelter.model.db;

import java.util.HashMap;

import edu.gatech.buzzshelter.model.auth.Credential;
import edu.gatech.buzzshelter.model.user.Person;

public class MemDB implements Database
{
    private HashMap<Credential, Person> db = new HashMap<>();

    @Override
    public void put(Person person)
    {
        Credential cred = person.getCred();
        db.put(cred, person);
    }

    @Override
    public Person get(Credential cred)
    {
        return db.get(cred);
    }
}
