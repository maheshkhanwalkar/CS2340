package edu.gatech.buzzshelter.model.user;

import edu.gatech.buzzshelter.model.auth.Credential;

public class Person
{
    private String name;
    private Credential cred;

    private boolean locked;
    private String email;

    /* There are probably other fields here */
    /* TODO check notecard/spec for this */

    public Person(String name, String username, String password, String email)
    {
        this(name, new Credential(username, password), email);
    }

    public Person(String name, Credential cred, String email)
    {
        this.name = name;
        this.cred = cred;
        this.email = email;
    }

    /* Getters and Setters */

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public Credential getCred()
    {
        return cred;
    }
    public void setCred(Credential cred)
    {
        this.cred = cred;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public boolean isLocked()
    {
        return locked;
    }
    public void lock()
    {
        locked = true;
    }
}
