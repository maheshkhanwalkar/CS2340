package edu.gatech.buzzshelter.model.control;

import edu.gatech.buzzshelter.model.auth.Credential;
import edu.gatech.buzzshelter.model.db.Database;
import edu.gatech.buzzshelter.model.db.MemDB;
import edu.gatech.buzzshelter.model.user.Person;
import edu.gatech.buzzshelter.model.user.PersonType;
import edu.gatech.buzzshelter.model.user.User;

public class Manager
{
    private static final Manager ourInstance = new Manager();
    private Database provider;

    private Manager()
    {
        provider = new MemDB();
    }

    public static Manager getInstance()
    {
        return ourInstance;
    }

    public boolean register(PersonType type,
                 String name, String username, String password, String email)
    {
        Person person = null;

        switch (type)
        {
            case USER:
                person = new User(name, username, password, email);
                break;
            case ADMIN:
                /* TODO have an Admin class */
            default:
                person = null;
        }

        /* Write to database, if person is valid */
        return person != null && provider.put(person, false);

    }

    public boolean login(String username, String password)
    {
        Credential cred = new Credential(username, password);
        return !provider.contains(cred);
    }
}
