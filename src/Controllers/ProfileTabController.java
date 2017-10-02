package Controllers;

import Models.Dentist;
import Views.ProfileTab;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Created by Patrick on 23-Mar-17.
 */
public class ProfileTabController {
    private int dentist;
    private ProfileTab view;
    private DBConnection dbConnection;

    public ProfileTabController(ProfileTab view, int dentist){
        dbConnection = new DBConnection();
        setView(view);
        setDentist(dentist);
        setTextFields();

        eventListeners();
    }

    public void setTextFields(){
        dbConnection.createConnection();
        Dentist dentist = dbConnection.getDentist(getDentist());
        view.getTxtFirstName().setText(dentist.getFirstName());
        view.getTxtLastName().setText(dentist.getLastName());
        view.getTxtPassword().setText(dentist.getPassword());
        view.getTxtContactNo().setText(dentist.getContactNo());
    }

    public void saveProfile(){
        if(isInputValid()){
            dbConnection.createConnection();
            Dentist dentist = dbConnection.getDentist(getDentist());
            dentist.setFirstName(view.getTxtFirstName().getText());
            dentist.setLastName(view.getTxtLastName().getText());
            dentist.setPassword(view.getTxtPassword().getText());
            dentist.setContactNo(view.getTxtContactNo().getText());
            dbConnection.editDentist(dentist);
            view.getLblsaved().setText("Profile Save!");
        }
    }

    public void eventListeners(){
        view.getSave().setOnAction(e -> saveProfile());
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (view.getTxtFirstName().getText() == null || view.getTxtFirstName().getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (view.getTxtLastName().getText() == null || view.getTxtLastName().getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (view.getTxtPassword().getText() == null || view.getTxtPassword().getText().length() == 0) {
            errorMessage += "No valid Password!\n";
        }
        if (view.getTxtContactNo().getText() == null || view.getTxtContactNo().getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
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

    public int getDentist() {
        return dentist;
    }

    public void setDentist(int dentist) {
        this.dentist = dentist;
    }

    public ProfileTab getView() {
        return view;
    }

    public void setView(ProfileTab view) {
        this.view = view;
    }
}
