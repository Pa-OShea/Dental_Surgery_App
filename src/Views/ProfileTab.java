package Views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by Patrick on 23-Mar-17.
 */
public class ProfileTab extends Tab {
    private Button save;
    private TextField txtFirstName, txtLastName, txtPassword, txtContactNo;
    private Label lblsaved;

    public ProfileTab(){
        setText("Profile");

        Pane pane = new Pane();
        GridPane Grid = new GridPane();
        ColumnConstraints columnConstraints = new ColumnConstraints();
        ColumnConstraints columnConstraints0 = new ColumnConstraints();
        RowConstraints rowConstraints = new RowConstraints();
        RowConstraints rowConstraints0 = new RowConstraints();
        RowConstraints rowConstraints1 = new RowConstraints();
        RowConstraints rowConstraints2 = new RowConstraints();
        RowConstraints rowConstraints3 = new RowConstraints();
        RowConstraints rowConstraints4 = new RowConstraints();

        Label lblTitle = new Label("PROFILE");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Label lblFirstName = new Label("FIRST NAME");
        lblFirstName.setFont(Font.font("Arial",FontWeight.BOLD, 15));
        Label lblLastName = new Label("LAST NAME");
        lblLastName.setFont(Font.font("Arial",FontWeight.BOLD, 15));
        Label lblPassword = new Label("PASSWORD");
        lblPassword.setFont(Font.font("Arial",FontWeight.BOLD, 15));
        Label lblContactNo = new Label("CONTACT #");
        lblContactNo.setFont(Font.font("Arial",FontWeight.BOLD, 15));

        lblsaved = new Label();

        txtFirstName = new TextField();
        txtLastName = new TextField();
        txtPassword = new TextField();

        txtContactNo = new TextField();
        save = new Button("Save");
        save.setPrefSize(75, 25);


        Grid.setLayoutX(432.5);
        Grid.setLayoutY(100);
        Grid.setPrefHeight(350);
        Grid.setPrefWidth(385.0);
        Grid.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(167.0);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(116.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(227.0);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(227.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints2.setMinHeight(10.0);
        rowConstraints2.setPrefHeight(30.0);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints3.setMinHeight(10.0);
        rowConstraints3.setPrefHeight(30.0);
        rowConstraints3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints4.setMinHeight(10.0);
        rowConstraints4.setPrefHeight(30.0);
        rowConstraints4.setVgrow(javafx.scene.layout.Priority.SOMETIMES);


        GridPane.setColumnIndex(lblTitle, 0);
        GridPane.setColumnIndex(lblFirstName, 0);
        GridPane.setColumnIndex(lblLastName, 0);
        GridPane.setColumnIndex(lblPassword, 0);
        GridPane.setColumnIndex(lblContactNo, 0);
        GridPane.setColumnIndex(txtFirstName, 1);
        GridPane.setColumnIndex(txtLastName, 1);
        GridPane.setColumnIndex(txtPassword, 1);
        GridPane.setColumnIndex(txtContactNo, 1);
        GridPane.setColumnIndex(save, 0);
        GridPane.setColumnIndex(lblsaved, 1);
        GridPane.setRowIndex(lblTitle, 0);
        GridPane.setRowIndex(lblFirstName, 1);
        GridPane.setRowIndex(lblLastName, 2);
        GridPane.setRowIndex(lblPassword, 3);
        GridPane.setRowIndex(lblContactNo, 4);
        GridPane.setRowIndex(txtFirstName, 1);
        GridPane.setRowIndex(txtLastName, 2);
        GridPane.setRowIndex(txtPassword, 3);
        GridPane.setRowIndex(txtContactNo, 4);
        GridPane.setRowIndex(save, 5);
        GridPane.setRowIndex(lblsaved, 5);



        Grid.getColumnConstraints().add(columnConstraints);
        Grid.getColumnConstraints().add(columnConstraints0);
        Grid.getRowConstraints().add(rowConstraints);
        Grid.getRowConstraints().add(rowConstraints0);
        Grid.getRowConstraints().add(rowConstraints1);
        Grid.getRowConstraints().add(rowConstraints2);
        Grid.getRowConstraints().add(rowConstraints3);
        Grid.getRowConstraints().add(rowConstraints4);
        Grid.getChildren().add(lblTitle);
        Grid.getChildren().add(lblFirstName);
        Grid.getChildren().add(lblLastName);
        Grid.getChildren().add(lblPassword);
        Grid.getChildren().add(lblContactNo);
        Grid.getChildren().add(txtFirstName);
        Grid.getChildren().add(txtLastName);
        Grid.getChildren().add(txtPassword);
        Grid.getChildren().add(txtContactNo);
        Grid.getChildren().add(save);
        Grid.getChildren().addAll(lblsaved);
        Grid.setStyle("-fx-border-style: solid");
        Grid.setPadding(new Insets(10,10,10,10));
        pane.getChildren().add(Grid);
        setContent(pane);
    }

    public Button getSave() {
        return save;
    }

    public void setSave(Button save) {
        this.save = save;
    }

    public TextField getTxtFirstName() {
        return txtFirstName;
    }

    public void setTxtFirstName(TextField txtFirstName) {
        this.txtFirstName = txtFirstName;
    }

    public TextField getTxtLastName() {
        return txtLastName;
    }

    public void setTxtLastName(TextField txtLastName) {
        this.txtLastName = txtLastName;
    }

    public TextField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(TextField txtPassword) {
        this.txtPassword = txtPassword;
    }

    public TextField getTxtContactNo() {
        return txtContactNo;
    }

    public void setTxtContactNo(TextField txtContactNo) {
        this.txtContactNo = txtContactNo;
    }

    public Label getLblsaved() {
        return lblsaved;
    }

    public void setLblsaved(Label lblsaved) {
        this.lblsaved = lblsaved;
    }
}
