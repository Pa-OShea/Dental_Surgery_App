package Views;

import Models.Patient;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * Created by Patrick on 03-Mar-17.
 */
public class PatientTab extends Tab {
    private SplitPane split;
    private AnchorPane left, right;
    private TableView<Patient> patientTable;
    private Button addPatient, editPatient, removePatient;
    private TextField txtFirstName, txtLastName, txtAddress, txtContactNo;
    private ComboBox cmbListPatients;
    private TableColumn<Patient, Integer> ID;
    private TableColumn<Patient, String> fName;
    private TableColumn<Patient, String> lName;
    private TableColumn<Patient, String> address;
    private TableColumn<Patient, String> contactNo;

    public PatientTab(){
        setText("Patient");
        split = new SplitPane();
        split.setDividerPosition(0, 0.3357);
        split.setLayoutX(41.0);
        split.setLayoutY(76.0);
        split.setPrefHeight(346.0);
        split.setPrefWidth(600.0);

        AnchorPane.setBottomAnchor(split, 0.0);
        AnchorPane.setLeftAnchor(split, 0.0);
        AnchorPane.setRightAnchor(split, 0.0);
        AnchorPane.setTopAnchor(split, 0.0);

        SplitPane.setResizableWithParent(getLeft(), true);
        split.getItems().addAll(getLeft(), getRight());

        setContent(split);
    }

    /*
    left hand side of the window
     */
    private AnchorPane getLeft(){
        left = new AnchorPane();

        cmbListPatients = new ComboBox();
        cmbListPatients.setPromptText("Patients");
        cmbListPatients.setLayoutX(12);
        cmbListPatients.setLayoutY(20);
        cmbListPatients.setPrefWidth(150);

        /*
        LABELS
         */

        Label lblFirstName = new Label("First Name:");

        Label lblLastName = new Label("Last Name:");

        Label lblAddress = new Label("Address");

        Label lblContactNo = new Label("Contact No");

        /*
        TEXTFIELDS
         */

        txtFirstName = new TextField();
        txtFirstName.setPrefWidth(210);

        txtLastName = new TextField();
        txtLastName.setPrefWidth(210);

        txtAddress = new TextField();
        txtAddress.setPrefWidth(210);

        txtContactNo = new TextField();
        txtContactNo.setPrefWidth(210);

        GridPane Grid = new GridPane();
        ColumnConstraints columnConstraints = new ColumnConstraints();
        ColumnConstraints columnConstraints0 = new ColumnConstraints();
        RowConstraints rowConstraints = new RowConstraints();
        RowConstraints rowConstraints0 = new RowConstraints();
        RowConstraints rowConstraints1 = new RowConstraints();
        RowConstraints rowConstraints2 = new RowConstraints();


        /*
        BUTTONS
         */

        addPatient = new Button("Add");
        addPatient.setLayoutX(14);
        addPatient.setLayoutY(605);
        addPatient.setPrefSize(75, 25);

        editPatient = new Button("Update");
        editPatient.setDisable(true);
        editPatient.setLayoutX(165);
        editPatient.setLayoutY(605);
        editPatient.setPrefSize(75, 25);

        removePatient = new Button("Remove");
        removePatient.setDisable(true);
        removePatient.setLayoutX(323);
        removePatient.setLayoutY(605);
        removePatient.setPrefSize(65, 25);

        /*
        GRID
         */

        Grid.setLayoutX(14.0);
        Grid.setLayoutY(73.0);
        Grid.setPrefHeight(228.0);
        Grid.setPrefWidth(385.0);

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


        GridPane.setColumnIndex(txtFirstName, 1);
        GridPane.setColumnIndex(txtLastName, 1);
        GridPane.setRowIndex(txtLastName, 1);
        GridPane.setColumnIndex(txtAddress, 1);
        GridPane.setRowIndex(txtAddress, 2);
        GridPane.setColumnIndex(txtContactNo, 1);
        GridPane.setRowIndex(txtContactNo, 3);
        GridPane.setRowIndex(lblFirstName, 0);
        GridPane.setRowIndex(lblLastName, 1);
        GridPane.setRowIndex(lblAddress, 2);
        GridPane.setRowIndex(lblContactNo, 3);

        Grid.getColumnConstraints().add(columnConstraints);
        Grid.getColumnConstraints().add(columnConstraints0);
        Grid.getRowConstraints().add(rowConstraints);
        Grid.getRowConstraints().add(rowConstraints0);
        Grid.getRowConstraints().add(rowConstraints1);
        Grid.getRowConstraints().add(rowConstraints2);
        Grid.getChildren().add(lblFirstName);
        Grid.getChildren().add(txtFirstName);
        Grid.getChildren().add(txtLastName);
        Grid.getChildren().add(txtAddress);
        Grid.getChildren().add(txtContactNo);
        Grid.getChildren().add(lblLastName);
        Grid.getChildren().add(lblAddress);
        Grid.getChildren().add(lblContactNo);

        left.getChildren().addAll(cmbListPatients, Grid,
                addPatient, editPatient, removePatient);
        return left;
    }

    /*
    right hand side of the window
     */
    private AnchorPane getRight(){
        right = new AnchorPane();

        patientTable = new TableView<>();
        patientTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        ID = new TableColumn<>("ID");
        fName = new TableColumn<>("First Name");
        lName = new TableColumn<>("Last Name");
        address = new TableColumn<>("Address");
        contactNo = new TableColumn<>("Contact No");

        //text alignment
        ID.setStyle("-fx-alignment: CENTER;");
        fName.setStyle("-fx-alignment: CENTER;");
        lName.setStyle("-fx-alignment: CENTER;");
        address.setStyle("-fx-alignment: CENTER;");
        contactNo.setStyle("-fx-alignment: CENTER;");

        ID.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("id"));
        fName.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
        lName.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastName"));
        address.setCellValueFactory(new PropertyValueFactory<Patient, String>("address"));
        contactNo.setCellValueFactory(new PropertyValueFactory<Patient, String>("contactNo"));

        patientTable.getColumns().addAll(ID, fName, lName, address, contactNo);

        right.getChildren().addAll(patientTable);
        AnchorPane.setBottomAnchor(patientTable, 0.0);
        AnchorPane.setLeftAnchor(patientTable, 0.0);
        AnchorPane.setTopAnchor(patientTable, 0.0);
        AnchorPane.setRightAnchor(patientTable, 0.0);
        return right;
    }

    public TableView<Patient> getPatientTable(){
        return patientTable;
    }

    public Button getAddPatient() {
        return addPatient;
    }

    public void setAddPatient(Button addPatient) {
        this.addPatient = addPatient;
    }

    public Button getEditPatient() {
        return editPatient;
    }

    public void setEditPatient(Button editPatient) {
        this.editPatient = editPatient;
    }

    public Button getRemovePatient() {
        return removePatient;
    }

    public void setRemovePatient(Button removePatient) {
        this.removePatient = removePatient;
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

    public TextField getTxtAddress() {
        return txtAddress;
    }

    public void setTxtAddress(TextField txtAddress) {
        this.txtAddress = txtAddress;
    }

    public TextField getTxtContactNo() {
        return txtContactNo;
    }

    public void setTxtContactNo(TextField txtContactNo) {
        this.txtContactNo = txtContactNo;
    }

    public ComboBox getCmbListPatients() {
        return cmbListPatients;
    }

    public void setCmbListPatients(ComboBox cmbListPatients) {
        this.cmbListPatients = cmbListPatients;
    }

}
