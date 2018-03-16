package edu.gatech.buzzshelter.model.control;

import edu.gatech.buzzshelter.model.db.Database;
import edu.gatech.buzzshelter.model.db.MemDB;
import edu.gatech.buzzshelter.model.user.Person;
import edu.gatech.buzzshelter.model.user.PersonType;

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
        Person person = new Person(name, username, password, email, type);

        /* Write to database */
        return provider.put(person.getName(), person, false);
    }

    public boolean login(String username, String password)
    {
        Person uMatch = provider.get(username);
        return uMatch != null && uMatch.checkPass(password);
    }

}
