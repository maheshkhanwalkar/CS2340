package edu.gatech.buzzshelter.model.db;

import edu.gatech.buzzshelter.model.auth.Credential;
import edu.gatech.buzzshelter.model.user.Person;

/* Database connector */

public interface Database
{
    void put(Person person);
    Person get(Credential cred);
}
