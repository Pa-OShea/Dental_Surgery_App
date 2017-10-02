package Controllers;

import Models.Dentist;
import Models.Invoice;
import Models.Patient;
import Models.Procedure;
import Views.MainView;
import Views.PatientTab;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Patrick on 03-Mar-17.
 */
public class PatientTabController extends MainController {
    private PatientTab view;
    private ObservableList<Patient> observableList;
    private int dentist_no;
    private DBConnection dbConnection;

    public PatientTabController(MainView view, int dentist_no){
        super(view, dentist_no);
        dbConnection = new DBConnection();
        dbConnection.createConnection();
        this.view = view.getPatientTab();
        this.dentist_no = dentist_no;
        observableList = FXCollections.observableArrayList();



        setDentist(dentist_no);
        loadTable();
        setComboBox();
        eventListeners();
    }

    public ObservableList<Patient> getObservableList(){
        dbConnection.createConnection();
        try{
            if(observableList != null){
                observableList.clear();
            }else {
                observableList = FXCollections.observableArrayList();
            }
            if(!dbConnection.getPatients(getDentist()).isEmpty()){
                observableList.addAll(dbConnection.getPatients(dentist_no));
            }else {
                observableList = null;
            }
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }

        return observableList;

    }

    public void addPatient(){
        dbConnection.createConnection();
        if(isInputValid()){
            try{

                Patient newPatient = new Patient();
                try{
                    if(dbConnection.getPatients(getDentist()).isEmpty()){
                        newPatient.setId(1);
                    }else{
                        newPatient.setId(dbConnection.getPatients(getDentist()).size()+1);
                    }
                }catch (NullPointerException ex){
                    //System.exit(1);
                }

                newPatient.setFirstName(view.getTxtFirstName().getText());
                newPatient.setLastName(view.getTxtLastName().getText());
                newPatient.setAddress(view.getTxtAddress().getText());
                newPatient.setContactNo(view.getTxtContactNo().getText());
                newPatient.setId(dbConnection.highestPatientNo());



                dbConnection.addPatient(newPatient, dentist_no);
                //dbConnection.addInvoice(newPatient, invoice);
                System.out.print("Patient Added");

                clearTextfields();
                refresh();

            }catch (Exception e){
                e.printStackTrace();
                System.out.print("error");
            }
        }
    }

    public void editPatient(){
        dbConnection.createConnection();
        Patient patient = (Patient) view.getCmbListPatients().getSelectionModel().getSelectedItem();

        patient.setFirstName(view.getTxtFirstName().getText());
        patient.setLastName(view.getTxtLastName().getText());
        patient.setContactNo(view.getTxtContactNo().getText());
        patient.setAddress(view.getTxtAddress().getText());

        dbConnection.editPatient(patient);
        clearTextfields();
        refresh();
    }

    public void removePatient(){
        dbConnection.createConnection();
        Patient patient = (Patient) view.getCmbListPatients().getSelectionModel().getSelectedItem();
        dbConnection.deletePatient(patient);
        clearTextfields();
        refresh();
    }


    public void comboBoxChange(){
        dbConnection.createConnection();
        //if the combobox is set to new
        try{
            if(view.getCmbListPatients().getSelectionModel().getSelectedIndex() == 0){
                clearTextfields();
            } else { // or a patient has been choosen
                for (Patient patient : dbConnection.getPatients(getDentist())) {
                    Patient comparing = (Patient) view.getCmbListPatients().getSelectionModel().getSelectedItem();
                    if (comparing.getId() == patient.getId()) {
                        view.getEditPatient().setDisable(false);
                        view.getRemovePatient().setDisable(false);
                        view.getAddPatient().setDisable(true);
                        view.getTxtFirstName().setText(patient.getFirstName());
                        view.getTxtLastName().setText(patient.getLastName());
                        view.getTxtContactNo().setText(patient.getContactNo());
                        view.getTxtAddress().setText(patient.getAddress());
                    }
                }
            }
        }catch(NullPointerException ex){

        }
    }

    public void loadTable(){
        try{
            view.getPatientTable().setItems(getObservableList());
        }catch (NullPointerException ex){}
    }

    public void clearTextfields(){
        view.getAddPatient().setDisable(false);
        view.getEditPatient().setDisable(true);
        view.getRemovePatient().setDisable(true);
        view.getTxtFirstName().clear();
        view.getTxtLastName().clear();
        view.getTxtAddress().clear();
        view.getTxtContactNo().clear();
    }

    public void eventListeners(){// collection of all the event
        refresh();
        view.getAddPatient().setOnAction(e -> addPatient());
        view.getEditPatient().setOnAction(e -> editPatient());
        view.getRemovePatient().setOnAction(e -> removePatient());
        view.getCmbListPatients().valueProperty().addListener(e -> comboBoxChange());
    }

    public void refresh(){
        loadTable();
        setComboBox();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (view.getTxtFirstName().getText() == null || view.getTxtFirstName().getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (view.getTxtLastName().getText() == null || view.getTxtLastName().getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (view.getTxtAddress().getText() == null || view.getTxtAddress().getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }

        if (view.getTxtContactNo().getText() == null || view.getTxtContactNo().getText().length() == 0) {
            errorMessage += "No valid contact number!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(view.getTxtContactNo().getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid contact number(must be an integer)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
