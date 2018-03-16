package edu.gatech.buzzshelter.model.user;

public class Person
{
    private String name;
    private String username, password;

    private boolean locked;
    private String email;

    private PersonType type;

    public Person(String name, String username, String password, String email,
        PersonType type)
    {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.type = type;
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

    public PersonType getType()
    {
        return type;
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
