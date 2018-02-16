package edu.gatech.buzzshelter.model.db;

import edu.gatech.buzzshelter.model.auth.Credential;
import edu.gatech.buzzshelter.model.user.Person;

/* Database connector */

public interface Database
{
    boolean put(Person person, boolean overwrite);
    Person get(Credential cred);

    boolean contains(Credential cred);
}
