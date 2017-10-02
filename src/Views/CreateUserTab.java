package Views;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * Created by Patrick on 23-Mar-17.
 */
public class CreateUserTab extends Tab{
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField password;
    private TextField contactNo;
    private Button button;


    public CreateUserTab() {
        setText("Create User");
        AnchorPane anchorPane = new AnchorPane();
        GridPane gridPane;
        ColumnConstraints columnConstraints;
        ColumnConstraints columnConstraints0;
        RowConstraints rowConstraints;
        RowConstraints rowConstraints0;
        RowConstraints rowConstraints1;
        RowConstraints rowConstraints2;
        RowConstraints rowConstraints3;
        RowConstraints rowConstraints4;
        Label label;
        Label label0;
        Label label1;
        Label label2;

        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        rowConstraints3 = new RowConstraints();
        rowConstraints4 = new RowConstraints();
        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        firstNameField = new TextField();
        lastNameField = new TextField();
        password = new TextField();
        contactNo = new TextField();
        ButtonBar buttonBar = new ButtonBar();
        button = new Button();

        AnchorPane.setLeftAnchor(gridPane, 10.0);
        AnchorPane.setRightAnchor(gridPane, 10.0);
        AnchorPane.setTopAnchor(gridPane, 10.0);
        gridPane.setLayoutX(30.0);
        gridPane.setLayoutY(28.0);
        gridPane.setPrefHeight(254.0);
        gridPane.setPrefWidth(404.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(218.0);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(111.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(316.0);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(261.0);

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

        label.setText("First Name");

        GridPane.setRowIndex(label0, 1);
        label0.setText("Last Name");

        GridPane.setRowIndex(label1, 2);
        label1.setText("Password");

        GridPane.setRowIndex(label2, 3);
        label2.setText("Contact No");

        GridPane.setColumnIndex(firstNameField, 1);

        GridPane.setColumnIndex(lastNameField, 1);
        GridPane.setRowIndex(lastNameField, 1);

        GridPane.setColumnIndex(password, 1);
        GridPane.setRowIndex(password, 2);

        GridPane.setColumnIndex(contactNo, 1);
        GridPane.setRowIndex(contactNo, 3);

        AnchorPane.setBottomAnchor(buttonBar, 10.0);
        AnchorPane.setRightAnchor(buttonBar, 10.0);
        buttonBar.setLayoutX(176.0);
        buttonBar.setLayoutY(233.0);

        button.setDefaultButton(true);
        button.setMnemonicParsing(false);
        button.setText("Save");


        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getRowConstraints().add(rowConstraints2);
        gridPane.getRowConstraints().add(rowConstraints3);
        gridPane.getRowConstraints().add(rowConstraints4);
        gridPane.getChildren().add(label);
        gridPane.getChildren().add(label0);
        gridPane.getChildren().add(label1);
        gridPane.getChildren().add(label2);
        gridPane.getChildren().add(firstNameField);
        gridPane.getChildren().add(lastNameField);
        gridPane.getChildren().add(password);
        gridPane.getChildren().add(contactNo);
        anchorPane.getChildren().add(gridPane);
        buttonBar.getButtons().add(button);
        anchorPane.getChildren().add(buttonBar);

        setContent(anchorPane);

    }

    public TextField getFirstNameField() {
        return firstNameField;
    }

    public TextField getLastNameField() {
        return lastNameField;
    }

    public TextField getPassword() {
        return password;
    }

    public TextField getContactNo() {
        return contactNo;
    }

    public Button getButton() {
        return button;
    }
}
