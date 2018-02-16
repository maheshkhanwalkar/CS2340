package edu.gatech.buzzshelter.model.control;

import edu.gatech.buzzshelter.model.auth.Credential;
import edu.gatech.buzzshelter.model.db.Database;
import edu.gatech.buzzshelter.model.db.MemDB;
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

    public boolean register(String name, String username, String password, String email)
    {
        User user = new User(name, username, password, email);
        return provider.put(user, false);
    }

    public boolean login(String username, String password)
    {
        Credential cred = new Credential(username, password);
        return !provider.contains(cred);
    }
}
