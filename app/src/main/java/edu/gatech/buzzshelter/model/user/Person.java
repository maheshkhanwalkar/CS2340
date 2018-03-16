package edu.gatech.buzzshelter.model.user;

public class Person
{
    private String name;
    private String username, password;

    private boolean locked;
    private String email;

    public Person(String name, String username, String password, String email)
    {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    /* Check if password matches */
    public boolean checkPass(String password) { return password.equals(this.password); }

    /* Getters and Setters */
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getUsername()
    {
        return username;
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
