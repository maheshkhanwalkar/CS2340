package edu.gatech.buzzshelter.model.db;

import java.util.HashMap;

import edu.gatech.buzzshelter.model.auth.Credential;
import edu.gatech.buzzshelter.model.user.Person;

public class MemDB implements Database
{
    private HashMap<Credential, Person> db = new HashMap<>();

    @Override
    public boolean put(Person person, boolean overwrite)
    {
        Credential cred = person.getCred();

        /* Already exists (and write disabled) */
        if(db.containsKey(cred) && !overwrite)
            return false;

        db.put(cred, person);
        return true;
    }

    @Override
    public Person get(Credential cred)
    {
        return db.get(cred);
    }

    @Override
    public boolean contains(Credential cred)
    {
        return db.containsKey(cred);
    }
}
