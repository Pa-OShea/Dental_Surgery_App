package Models;

import java.io.Serializable;

public class Person implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String contactNo;

    public Person(){}

    public Person(String firstName, String lastName, String contactNo){
        setFirstName(firstName);
        setLastName(lastName);
        setContactNo(contactNo);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
