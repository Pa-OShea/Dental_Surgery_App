package Controllers;

import Models.Dentist;
import Models.Patient;
import Models.Payment;
import Models.Procedure;
import Views.ReportTab;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by Patrick O'Shea on 13/03/2017.
 */
public class ReportTabController {
    private int dentist;
    private ReportTab view;
    private ArrayList<Patient> patients;
    private ObservableList<reportTable> reportObservableList;
    private DBConnection dbConnection;

    public ReportTabController(ReportTab view, int dentist){
        dbConnection = new DBConnection();
        dbConnection.createConnection();
        setView(view);
        setDentist(dentist);
        setPatients(dbConnection.getPatients(dentist));

        reportObservableList = FXCollections.observableArrayList();
        eventlistener();

    }

    private void eventlistener() {
        view.getComboBox().valueProperty().addListener(e -> comboboxChange());
    }

    public void fillTable(){
        try{
            view.getTableView().setItems(getDetails());
        }catch(NullPointerException ex){}
    }

    public void comboboxChange(){
        dbConnection.createConnection();
        setPatients(dbConnection.getPatients(dentist));
        fillTable();
    }

    public ObservableList<reportTable> getDetails(){
        reportObservableList.clear();
        dbConnection.createConnection();
        setPatients(dbConnection.getPatients(getDentist()));

        try{
            if(reportObservableList != null){
                reportObservableList.clear();
            }else{
                reportObservableList = FXCollections.observableArrayList();
            }

            reportTable newReport = new reportTable();

            for (Patient patient : getPatients()) {

                for (int j = 0; j < patient.getP_invoiceList().size(); j++) {

                    newReport.setInvoiceAmtOwed(patient.getP_invoiceList().get(j).getInvoiceAmtOwed());
                    int payments = patient.getP_invoiceList().get(j).getIn_paymentList().size();
                    int procedures = patient.getP_invoiceList().get(j).getIn_procList().size();
                    int size = 0;
                    if(payments > procedures){
                        size = payments;
                    }else{
                        size = procedures;
                    }

                    Payment payment = null;
                    Procedure procedure = null;

                    if (view.getComboBox().getSelectionModel().getSelectedIndex() == 0) {
                        for (int x = 0; x < size; x++) {
                            newReport = new reportTable();
                            newReport.setPatientNo(patient.getId());
                            newReport.setPatientFName(patient.getFirstName());
                            newReport.setPatientLName(patient.getLastName());
                            newReport.setInvoiceNo(patient.getP_invoiceList().get(j).getInvoiceNo());
                            newReport.setInvoiceDate(String.valueOf(patient.getP_invoiceList().get(j).getInvoiceDate()));
                            newReport.setInvoiceAmt(patient.getP_invoiceList().get(j).getInvoiceAmt());
                            newReport.setInvoiceAmtOwed(patient.getP_invoiceList().get(j).getInvoiceAmtOwed());

                            try{
                                payment = patient.getP_invoiceList().get(j).getIn_paymentList().get(x);
                            }catch (IndexOutOfBoundsException e){}

                            try {
                                procedure = patient.getP_invoiceList().get(j).getIn_procList().get(x);
                            }catch (IndexOutOfBoundsException e){}

                            if(procedure != null){
                                newReport.setProcedureNo(procedure.getProcNo());
                                newReport.setProcedureName(procedure.getProcName());
                            }
                            if(payment != null){
                                newReport.setPaymentNo(payment.getPaymentNo());
                                newReport.setPaymentAmt(payment.getPaymentAmt());
                                newReport.setPaymentDate(payment.getDate());
                            }
                            reportObservableList.add(newReport);
                        }
                    } else if (view.getComboBox().getSelectionModel().getSelectedIndex() == 1 && !patient.getP_invoiceList().get(j).isPaid()){
                        for (int x = 0; x < size; x++) {
                            newReport = new reportTable();
                            newReport.setPatientNo(patient.getId());
                            newReport.setPatientFName(patient.getFirstName());
                            newReport.setPatientLName(patient.getLastName());
                            newReport.setInvoiceNo(patient.getP_invoiceList().get(j).getInvoiceNo());
                            newReport.setInvoiceDate(String.valueOf(patient.getP_invoiceList().get(j).getInvoiceDate()));
                            newReport.setInvoiceAmt(patient.getP_invoiceList().get(j).getInvoiceAmt());
                            newReport.setInvoiceAmtOwed(patient.getP_invoiceList().get(j).getInvoiceAmtOwed());
                            try{
                                payment = patient.getP_invoiceList().get(j).getIn_paymentList().get(x);
                            }catch (IndexOutOfBoundsException e){}

                            try {
                                procedure = patient.getP_invoiceList().get(j).getIn_procList().get(x);
                            }catch (IndexOutOfBoundsException e){}

                            if(procedure != null){
                                newReport.setProcedureNo(procedure.getProcNo());
                                newReport.setProcedureName(procedure.getProcName());
                            }
                            if(payment != null){
                                newReport.setPaymentNo(payment.getPaymentNo());
                                newReport.setPaymentAmt(payment.getPaymentAmt());
                                newReport.setPaymentDate(payment.getDate());
                            }
                            try {
                                procedure = patient.getP_invoiceList().get(j).getIn_procList().get(x);
                            }catch (IndexOutOfBoundsException e){}
                            reportObservableList.add(newReport);
                        }
                    }
                }
            }


        }catch (NullPointerException ex){
            ex.printStackTrace();
        }



        return reportObservableList;
    }



    public int getDentist() {
        return dentist;
    }

    public void setDentist(int dentist) {
        this.dentist = dentist;
    }

    public ReportTab getView() {
        return view;
    }

    public void setView(ReportTab view) {
        this.view = view;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }
}
