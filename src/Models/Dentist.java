package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Dentist extends Person implements Serializable {
    private String password;
    private ArrayList<Patient> patientList;

    public Dentist(){
        setPatientList(new ArrayList<>());
    }

    public Dentist(String firstName, String lastName, String contactNo, String password) {
        super(firstName, lastName, contactNo);
        setPassword(password);
        setPatientList(new ArrayList<>());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(ArrayList<Patient> patientList) {
        this.patientList = patientList;
    }

    public void addPatient(Patient newPatient){
        patientList.add(newPatient);
    }

    public String toString(){
        return getFirstName();
    }


}
