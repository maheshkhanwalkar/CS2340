package edu.gatech.buzzshelter.model.user;

import edu.gatech.buzzshelter.model.auth.Credential;

public class Admin extends Person
{
    public Admin(String name, String username, String password, String email)
    {
        /* TODO initialize other fields? */
        super(name, username, password, email);
    }
}
