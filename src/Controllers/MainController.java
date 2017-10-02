package Controllers;

import Models.Dentist;
import Models.Patient;
import Models.Procedure;
import Views.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import sun.java2d.cmm.Profile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Patrick O'Shea on 13/03/2017.
 */
public class MainController {
    private MainView mainView;
    private PatientTab patientTab;
    private ProcedureTab procedureTab;
    private InvoiceTab invoiceTab;
    private ReportTab reportTab;
    private ProfileTab profileTab;

    private PatientTabController patientTabController;
    private ProcedureTabController procedureTabController;
    private InvoiceTabController invoiceTabController;
    private ReportTabController reportTabController;
    private ProfileTabController profileTabController;

    private int dentist;
    private DBConnection dbConnection;

    public MainController(MainView mainView, int dentist){
        this.mainView = mainView;
        this.dentist = dentist;
        dbConnection = new DBConnection();
        dbConnection.createConnection();

        //USE A INT FOR CHECK FOR CURRENT DENTIST
        createControllers();
        setComboBox();

    }

    public void createControllers(){
        profileTabController = new ProfileTabController(this.mainView.getProfileTab(), this.dentist);
        reportTabController = new ReportTabController(this.mainView.getReportTab(), this.dentist);
    }

    public void setComboBox(){
        dbConnection.createConnection();

        try{
            mainView.getPatientTab().getCmbListPatients().getItems().removeAll(mainView.getPatientTab().getCmbListPatients().getItems());
            mainView.getPatientTab().getCmbListPatients().setPromptText("Patients");
            mainView.getPatientTab().getCmbListPatients().getItems().add("+NEW");

            if(!dbConnection.getPatients(dentist).isEmpty()){
                mainView.getPatientTab().getCmbListPatients().getItems().addAll(dbConnection.getPatients(dentist));

            }
        }catch (NullPointerException ex){

        }

        mainView.getProcedureTab().getCmbListProcedure().getItems().clear();
        mainView.getProcedureTab().getCmbListProcedure().setPromptText("Procedures");
        mainView.getProcedureTab().getCmbListProcedure().getItems().add("+NEW");

        if(!dbConnection.getProcedures().isEmpty()){
            mainView.getProcedureTab().getCmbListProcedure().getItems().addAll(dbConnection.getProcedures());
        }

        mainView.getInvoiceTab().getCmbListPatients().getItems().clear();
        mainView.getInvoiceTab().getCmbListPatients().setPromptText("Patients");
        try{
            if(dbConnection.getPatients(dentist).isEmpty()){
                mainView.getInvoiceTab().getCmbListPatients().getItems().add("-");
            }else{
                mainView.getInvoiceTab().getCmbListPatients().getItems().addAll(dbConnection.getPatients(dentist));
            }
        }catch (NullPointerException ex){

        }

        mainView.getInvoiceTab().getCmbListProcedures().getItems().clear();
        mainView.getInvoiceTab().getCmbListProcedures().setPromptText("Procedures");
        try {
            if(!dbConnection.getProcedures().isEmpty()){
                mainView.getInvoiceTab().getCmbListProcedures().getItems().addAll(dbConnection.getProcedures());
            }else{
                mainView.getInvoiceTab().getCmbListProcedures().getItems().add("-");
            }
        }catch (NullPointerException ex){

        }
    }

    public PatientTab getPatientTab() {
        return patientTab;
    }

    public void setPatientTab(PatientTab patientTab) {
        this.patientTab = patientTab;
    }

    public ProcedureTab getProcedureTab() {
        return procedureTab;
    }

    public void setProcedureTab(ProcedureTab procedureTab) {
        this.procedureTab = procedureTab;
    }

    public InvoiceTab getInvoiceTab() {
        return invoiceTab;
    }

    public void setInvoiceTab(InvoiceTab invoiceTab) {
        this.invoiceTab = invoiceTab;
    }

    public ReportTab getReportTab() {
        return reportTab;
    }

    public void setReportTab(ReportTab reportTab) {
        this.reportTab = reportTab;
    }

    public PatientTabController getPatientTabController() {
        return patientTabController;
    }

    public void setPatientTabController(PatientTabController patientTabController) {
        this.patientTabController = patientTabController;
    }

    public ProcedureTabController getProcedureTabController() {
        return procedureTabController;
    }

    public void setProcedureTabController(ProcedureTabController procedureTabController) {
        this.procedureTabController = procedureTabController;
    }

    public InvoiceTabController getInvoiceTabController() {
        return invoiceTabController;
    }

    public void setInvoiceTabController(InvoiceTabController invoiceTabController) {
        this.invoiceTabController = invoiceTabController;
    }

    public ReportTabController getReportTabController() {
        return reportTabController;
    }

    public void setReportTabController(ReportTabController reportTabController) {
        this.reportTabController = reportTabController;
    }

    public ProfileTab getProfileTab() {
        return profileTab;
    }

    public void setProfileTab(ProfileTab profileTab) {
        this.profileTab = profileTab;
    }

    public ProfileTabController getProfileTabController() {
        return profileTabController;
    }

    public void setProfileTabController(ProfileTabController profileTabController) {
        this.profileTabController = profileTabController;
    }

    public int getDentist() {
        return dentist;
    }

    public void setDentist(int dentist) {
        this.dentist = dentist;
    }
}
