package Controllers;

import Models.Dentist;
import Models.Procedure;
import Views.MainView;
import Views.ProcedureTab;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Patrick on 03-Mar-17.
 */
public class ProcedureTabController extends MainController{
    private ArrayList<Procedure> procedures;
    private DBConnection dbConnection;
    private ProcedureTab view;
    private ObservableList<Procedure> observableList;

    public ProcedureTabController(MainView view, int dentist){
        super(view, dentist);
        dbConnection = new DBConnection();
        dbConnection.createConnection();
        this.procedures = dbConnection.getProcedures();
        this.view = view.getProcedureTab();
        observableList = FXCollections.observableArrayList();

        eventListeners();
        setComboBox();

       // dbConnection.shutdown();
    }

    public ObservableList<Procedure> fillTable(){
        observableList.clear();
        dbConnection.createConnection();
        if(observableList != null){
            observableList.clear();
        }else {
            observableList = FXCollections.observableArrayList();
        }
        if(!dbConnection.getProcedures().isEmpty()){
            for (Procedure procedure : dbConnection.getProcedures()) {
                observableList.add(procedure);
            }
        }
        return observableList;
    }

    public void addProcedure(){
        dbConnection.createConnection();
        if(isInputValid()){
            try{
                Procedure newProcedure = new Procedure();
                try{
                    if(dbConnection.getProcedures().isEmpty()){
                        newProcedure.setProcNo(1);
                    }else{
                        newProcedure.setProcNo(dbConnection.getProcedures().size()+1);
                    }
                }catch (NullPointerException ex){

                }
                newProcedure.setProcName(view.getTxtProcName().getText());
                newProcedure.setProcCost(Double.parseDouble(view.getTxtCost().getText()));
                dbConnection.addProcedure(newProcedure);
                clearTextfields();
                refresh();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
       // dbConnection.shutdown();
    }

    public void editProcedure(){
        Procedure procedure = (Procedure) view.getCmbListProcedure().getSelectionModel().getSelectedItem();
        procedure.setProcName(view.getTxtProcName().getText());
        procedure.setProcCost(Double.parseDouble(view.getTxtCost().getText()));
        dbConnection.editProcedure(procedure);
        clearTextfields();
        refresh();
    }

    public void removeProcedure(){
        dbConnection.createConnection();
        Procedure procedure = (Procedure) view.getCmbListProcedure().getSelectionModel().getSelectedItem();
        int ProcNo = procedure.getProcNo();
        dbConnection.deleteProcedure(ProcNo);

        clearTextfields();
        refresh();
    }

    public void comboBoxChange(){
        dbConnection.createConnection();
        //if the combobox is set to new
        try{

            if(view.getCmbListProcedure().getSelectionModel().getSelectedIndex() == 0){
                clearTextfields();
            } else { // or a patient has been choosen
                for (Procedure procedure : dbConnection.getProcedures()) {
                    Procedure compare = (Procedure) view.getCmbListProcedure().getSelectionModel().getSelectedItem();
                    if (procedure.getProcNo() == compare.getProcNo()) {
                        view.getEditProcedure().setDisable(false);
                        view.getRemoveProcedure().setDisable(false);
                        view.getAddProcedure().setDisable(true);
                        view.getTxtProcName().setText(procedure.getProcName());
                        view.getTxtCost().setText(String.valueOf(procedure.getProcCost()));
                    }
                }
            }
        }catch (NullPointerException ex){}
    }

    public void loadTable(){
        try{
            view.getProcedureTable().setItems(fillTable());
        }catch (NullPointerException ex){}
    }

    public void clearTextfields(){
        view.getAddProcedure().setDisable(false);
        view.getEditProcedure().setDisable(true);
        view.getRemoveProcedure().setDisable(true);
        view.getTxtProcName().clear();
        view.getTxtCost().clear();
    }

    public void eventListeners(){
        view.getProcedureTable().setItems(fillTable());
        view.getCmbListProcedure().setOnAction(e -> comboBoxChange());
        view.getAddProcedure().setOnAction(e -> addProcedure());
        view.getRemoveProcedure().setOnAction(e -> removeProcedure());
        view.getEditProcedure().setOnAction(e -> editProcedure());
        refresh();
    }

    public void refresh(){
        setComboBox();
        loadTable();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (view.getTxtProcName().getText() == null || view.getTxtProcName().getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }

        if (view.getTxtCost().getText() == null || view.getTxtCost().getText().length() == 0) {
            errorMessage += "No valid cost!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(view.getTxtCost().getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid cost(must be an integer)!\n";
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
