package edu.gatech.buzzshelter;

/**
 * Created by daneolog on 2/8/18.
 */

public class Person {
    String name;
    String username;
    String password;
    boolean locked;
    String email;
    int phone;

    public Person(String name, String username, String password, String email, int phone) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.locked = false;
        this.email = email;
        this.phone = phone;
    }
}
