package edu.gatech.buzzshelter.model.auth;

public class Credential
{
    private String username;
    private String password;

    public Credential(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }


    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Credential))
            return false;

        Credential other = (Credential)obj;

        return username.equals(other.getUsername())
                && password.equals(other.getPassword());
    }

    @Override
    public int hashCode()
    {
        /* Could be better */
        return username.hashCode() + password.hashCode();
    }
}
