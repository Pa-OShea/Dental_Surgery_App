package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Patient extends Person implements Serializable {
    private String address;
//    private static int nextPatientID = 1;
//    private int patientID;
    private ArrayList<Invoice> p_invoiceList;

    public Patient(){
        setP_invoiceList(new ArrayList<>());
    }

    public Patient(String firstName, String lastName, String contactNo, String address) {
        super(firstName, lastName, contactNo);
        setAddress(address);
        setP_invoiceList(new ArrayList<>());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Invoice> getP_invoiceList() {
        return p_invoiceList;
    }

    public void setP_invoiceList(ArrayList<Invoice> p_invoiceList) {
        this.p_invoiceList = p_invoiceList;
    }

    public void addInvoice(Invoice newInvoice){
        p_invoiceList.add(newInvoice);
    }

    public String toString(){
    	return getFirstName() + " " + getLastName() + " - ID: " + getId();
                /*+ "\n\nInvoice:\n" + getP_invoiceList()*/
    }

}
