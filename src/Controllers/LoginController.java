package Controllers;

import Models.Dentist;
import Views.CreateUserTab;
import Views.Login;
import Views.LoginTab;
import Views.MainView;
import javafx.scene.control.Alert;

import java.util.ArrayList;

/**
 * Created by Patrick O'Shea on 20/02/2017.
 */
public class LoginController {
    private ArrayList<Dentist> dentistModel;
    private Login login;
    private LoginTab loginTab;
    private CreateUserTab createUserTab;
    private Dentist current;
    private DBConnection dbConnection;


    public LoginController(Login login){
        dbConnection = new DBConnection();
        dbConnection.createConnection();
        this.dentistModel = dbConnection.getDentists();
        this.login = login;
        this.loginTab = login.getLoginTab();
        this.createUserTab = login.getCreateUserTab();

        fillcomboBox();
        eventListners();
    }

    public void eventListners(){
        ///loginTab.getButton().setOnAction(e -> signIn());
        //loginTab.getUserCMB().setOnAction(e -> fillcomboBox());
        loginTab.getUserCMB().valueProperty().addListener(e -> dentistCmbChanged());
    }

    public void fillcomboBox(){
        try{
            loginTab.getUserCMB().getItems().clear();
            loginTab.getUserCMB().getItems().addAll(dentistModel);
        }catch(NullPointerException ex){}
    }

    public void dentistCmbChanged(){
        for (Dentist aDentistModel : dentistModel) {
            if(loginTab.getUserCMB().getSelectionModel().getSelectedItem() == aDentistModel){
                loginTab.getPwBox().setText(aDentistModel.getPassword());
                loginTab.getButton().setDisable(false);
            }
        }
    }

    public boolean isInputValid() {
        String errorMessage = "";

        if (createUserTab.getFirstNameField().getText() == null || createUserTab.getFirstNameField().getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (createUserTab.getLastNameField().getText() == null || createUserTab.getFirstNameField().getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (createUserTab.getPassword().getText() == null || createUserTab.getPassword().getText().length() == 0) {
            errorMessage += "No valid Password!\n";
        }
        if (createUserTab.getContactNo().getText() == null || createUserTab.getContactNo().getText().length() == 0) {
            errorMessage += "No valid Contact Numner!\n";
        }else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(createUserTab.getContactNo().getText());
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

    public Dentist getCurrent() {
        return current;
    }

    public void setCurrent(Dentist current) {
        this.current = current;
    }
}

