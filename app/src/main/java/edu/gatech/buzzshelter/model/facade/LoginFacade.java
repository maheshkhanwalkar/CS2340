package edu.gatech.buzzshelter.model.facade;

import edu.gatech.buzzshelter.model.control.UserManager;
import edu.gatech.buzzshelter.model.user.PersonType;

public class LoginFacade
{
    private static LoginFacade ourInstance = new LoginFacade();
    private UserManager manager = new UserManager();

    private LoginFacade() {}
    public static LoginFacade getInstance() { return ourInstance; }

    public boolean register(PersonType type, String name,
            String username, String password, String email)
    {
        return manager.register(type, name, username, password, email);
    }

    public boolean login(String username, String password)
    {
        return manager.login(username, password);
    }
}
