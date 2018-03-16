package edu.gatech.buzzshelter.model.control;

import edu.gatech.buzzshelter.model.db.Database;
import edu.gatech.buzzshelter.model.db.MemDB;
import edu.gatech.buzzshelter.model.user.Admin;
import edu.gatech.buzzshelter.model.user.Person;
import edu.gatech.buzzshelter.model.user.PersonType;
import edu.gatech.buzzshelter.model.user.User;

public class UserManager
{
    private Database<String, Person> provider;
    public UserManager()
    {
        provider = new MemDB<>();
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
        return provider.put(person.getName(), person, false);
    }

    public boolean login(String username, String password)
    {
        Person uMatch = provider.get(username);
        return uMatch != null && uMatch.checkPass(password);
    }

}
