package de.berlin.htw.webtech.demo.m2;

public class Person {


    private long id;
    private String firstName;
    private String lastName;
    private boolean vaccinated;

    public Person(long id, String firstName, String lastName, boolean vaccinated) {

        this.id =id;
        this.firstName =firstName;
        this.lastName =lastName;
        this.vaccinated=vaccinated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    }
