package Dental_Surgery_App;

import Controllers.*;
import Models.*;

import Views.Login;
import Views.MainView;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;


public class Main extends Application{
    private Scene loginScene, mainScene;
    private Login login;
    private MainView mainView;
    private LoginController loginController;
    private MainController mainController;
    private int dentist_no;
    ArrayList<Dentist> dentistList;

    @Override
    public void start(Stage primaryStage) throws Exception{
        DBConnection dbConnection = new DBConnection();
        dbConnection.createConnection();
        dentistList = new ArrayList<>();

        try{
            login = new Login();
            loginScene = new Scene(login);
            loginController = new LoginController(login);
            mainView = new MainView();
            mainScene = new Scene(mainView);


            //HANDLES THE SIGN IN BUTTON
            login.getLoginTab().getButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try{
                        for (Dentist aDentistList : dbConnection.getDentists()) {
                            Dentist d = (Dentist) login.getLoginTab().getUserCMB().getSelectionModel().getSelectedItem();
                            if (d.getId() == aDentistList.getId() && login.getLoginTab().getPwBox().getText().equals(aDentistList.getPassword())) {
                                dentist_no = aDentistList.getId();
                                mainController = new MainController(mainView, dentist_no);
                                PatientTabController patientTabController = new PatientTabController(mainView, dentist_no);
                                ProcedureTabController procedureTabController = new ProcedureTabController(mainView, dentist_no);
                                InvoiceTabController invoiceTabController = new InvoiceTabController(mainView, dentist_no);
                                primaryStage.setScene(mainScene);
                                primaryStage.centerOnScreen();
                            } else {
                                login.getLoginTab().getWarning().setText("Incorrect!");
                            }
                        }
                    }catch (NullPointerException ex){
                        ex.printStackTrace();
                        System.out.print("Something went wrong");

                    }
                }
            });


            // HANDLES THE CREATE USER BUTTON
            login.getCreateUserTab().getButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(loginController.isInputValid()){
                        Dentist newDentist = new Dentist();
                        newDentist.setFirstName(login.getCreateUserTab().getFirstNameField().getText());
                        newDentist.setLastName(login.getCreateUserTab().getLastNameField().getText());
                        newDentist.setPassword(login.getCreateUserTab().getPassword().getText());
                        newDentist.setContactNo(login.getCreateUserTab().getContactNo().getText());

                        if(dbConnection.getDentists() == null){
                            newDentist.setId(1);
                        }else {
                            newDentist.setId(dbConnection.highestDentistNo());
                        }
                        dbConnection.addDentist(newDentist);
                        dentist_no = newDentist.getId();
                        mainController = new MainController(mainView, dentist_no);
                        PatientTabController patientTabController = new PatientTabController(mainView, dentist_no);
                        ProcedureTabController procedureTabController = new ProcedureTabController(mainView, dentist_no);
                        InvoiceTabController invoiceTabController = new InvoiceTabController(mainView, dentist_no);
                        primaryStage.setScene(mainScene);
                        primaryStage.centerOnScreen();
                    }
                }
            });


            //HANDLES THE LOG-OUT BUTTON
            mainView.getLog_out().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    primaryStage.setScene(loginScene);
                }
            });



            primaryStage.setTitle("Dental Surgery");
            primaryStage.setScene(loginScene);
            primaryStage.resizableProperty().setValue(Boolean.FALSE);
            primaryStage.show();

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        dbConnection.createConnection();
        dbConnection.checkTables();

        launch(args);
       
    }
}