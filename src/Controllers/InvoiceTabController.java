package Controllers;

import Models.*;
import Views.InvoiceTab;
import Views.MainView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Patrick O'Shea on 13/03/2017.
 */
public class InvoiceTabController extends MainController{
    private InvoiceTab view;
    private ObservableList<Procedure> procedureList;
    private ObservableList<Payment> paymentList;
    private Invoice currentInvoice;
    private Patient selectedPatient;
    private DBConnection dbConnection;

    public InvoiceTabController(MainView view, int dentist){
        super(view, dentist);
        dbConnection = new DBConnection();
        dbConnection.createConnection();
        this.view = view.getInvoiceTab();
        procedureList = FXCollections.observableArrayList();
        paymentList = FXCollections.observableArrayList();
        setComboBox();
        eventListners();
    }

    public ObservableList<Procedure> getProcedureList(){
        dbConnection.createConnection();
        try{
            if(procedureList != null){
                procedureList.clear();
            }else{
                procedureList = FXCollections.observableArrayList();
            }
            if(!currentInvoice.getIn_procList().isEmpty()){
                procedureList.addAll(currentInvoice.getIn_procList());
            }else{
                return null;
            }

        }catch (NullPointerException ex){
            ex.printStackTrace();
        }


        return procedureList;

    }

    public ObservableList<Payment> getPaymentList(){
        dbConnection.createConnection();
        try {
            if(paymentList != null){
                paymentList.clear();
            }else{
                paymentList = FXCollections.observableArrayList();
            }
            if(!currentInvoice.getIn_paymentList().isEmpty()){
                paymentList.addAll(currentInvoice.getIn_paymentList());
            }
        }catch (NullPointerException ex){ex.printStackTrace();}
        return paymentList;
    }


    public void patientComboboxChange(){
        dbConnection.createConnection();
        refresh();

        try{
            try{
                selectedPatient = (Patient) view.getCmbListPatients().getSelectionModel().getSelectedItem();
            }catch (ClassCastException e){
                refresh();
            }
            Patient patient = dbConnection.getPatient(selectedPatient.getId());

            if (selectedPatient.getId() == patient.getId()) {
                view.getMakePayment().setDisable(false);
                view.getCmbListProcedures().setDisable(false);
                //picking the newest invoice
                currentInvoice = dbConnection.getCurrentInvoice(selectedPatient.getId());

                updateLabels();
                //fill table

            }
        }catch (NullPointerException ex){}
    }

    public void addProcedure(){
        dbConnection.createConnection();
        currentInvoice = dbConnection.getCurrentInvoice(selectedPatient.getId());

        Procedure procedure = (Procedure) view.getCmbListProcedures().getSelectionModel().getSelectedItem();

        if(currentInvoice == null || currentInvoice.isPaid()){
            currentInvoice = new Invoice();
            currentInvoice.addProcedure(procedure);
            if(dbConnection.getAllInvoices().isEmpty()){
                currentInvoice.setInvoiceNo(1);
            }else {
                currentInvoice.setInvoiceNo(dbConnection.getAllInvoices().size()+1);
            }
            Date todaysDate = new Date();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            currentInvoice.setInvoiceDate(df.format(todaysDate));
            dbConnection.addInvoice(selectedPatient, currentInvoice);
        }else{
            currentInvoice.addProcedure(procedure);
            dbConnection.editInvoice(currentInvoice);
        }

        dbConnection.addProcedureToInvoice(procedure, currentInvoice);

        updateLabels();
    }

    public void addPayment(){
        dbConnection.createConnection();
        currentInvoice = dbConnection.getCurrentInvoice(selectedPatient.getId());
        Payment payment = new Payment();
        try{
            if(dbConnection.getPayments(currentInvoice.getInvoiceNo()).isEmpty()){
                payment.setPaymentNo(1);
            }else{
                payment.setPaymentNo(currentInvoice.getIn_paymentList().size()+1);
            }
        }catch (NullPointerException ex){}

        double amt = Double.parseDouble(view.getPaymentField().getText());
        Date date = new Date();
        payment.setPaymentAmt(amt);
        Date todaysDate = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        payment.setDate(df.format(todaysDate));

        currentInvoice.addPayment(payment);

        dbConnection.addPayment(payment, currentInvoice);
        dbConnection.editInvoice(currentInvoice);

        refresh();
        updateLabels();
    }

    public void eventListners(){
        setComboBox();
        view.getCmbListPatients().setOnAction(e -> patientComboboxChange());
        view.getCmbListProcedures().setOnAction(e -> view.getAddProcedure().setDisable(false));
        view.getAddProcedure().setOnAction(e -> addProcedure());
        view.getMakePayment().setOnAction(e -> addPayment());
        refresh();
    }

    public void clearLabels(){
        view.getLblfirstName().setText("-");
        view.getLblLastName().setText("-");
        view.getLblAddress().setText("-");
        view.getLblContactNo().setText("-");
        view.getInvoiceDate().setText("-/-/-");
        view.getInvoiceAmt().setText("0.00");
        view.getInvoiceAmtOwed().setText("0.00");
        view.getInvoiceNo().setText("-");
        view.getPaymentField().clear();
    }

    public void refresh(){
        clearLabels();
    }

    public void updateLabels(){
        //filling the labels with the selected patient
        view.getLblfirstName().setText(selectedPatient.getFirstName());
        view.getLblLastName().setText(selectedPatient.getLastName());
        view.getLblAddress().setText(selectedPatient.getAddress());
        view.getLblContactNo().setText(selectedPatient.getContactNo());


        if(currentInvoice != null){
            if(currentInvoice.isPaid()){
                currentInvoice = new Invoice();
            }
            view.getInvoiceNo().setText(String.valueOf(currentInvoice.getInvoiceNo()));
            view.getInvoiceDate().setText(currentInvoice.getInvoiceDate());
            view.getInvoiceAmt().setText(String.valueOf(currentInvoice.getInvoiceAmt()));
            view.getInvoiceAmtOwed().setText(String.valueOf(currentInvoice.getInvoiceAmtOwed()));
            fillTable();
        }
    }

    public void fillTable(){
        dbConnection.createConnection();
        try{
            view.getProcedureTable().setItems(getProcedureList());
            view.getPaymentTable().setItems(getPaymentList());
        }catch (NullPointerException ex){

        }

    }

    public void clearTable(){
        view.getProcedureTable().getItems().clear();
        view.getPaymentTable().getItems().clear();
    }


}
