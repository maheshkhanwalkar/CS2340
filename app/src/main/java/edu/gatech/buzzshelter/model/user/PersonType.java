package edu.gatech.buzzshelter.model.user;

/* Types of users */
public enum PersonType
{
    USER("User"),
    ADMIN("Admin");

    private String name;

    PersonType(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
