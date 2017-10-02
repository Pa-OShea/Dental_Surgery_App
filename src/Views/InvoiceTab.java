package Views;

import Models.Invoice;
import Models.Payment;
import Models.Procedure;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * Created by Patrick on 03-Mar-17.
 */
public class InvoiceTab extends Tab {
    private SplitPane split;
    private AnchorPane left, right;
    private Label lblfirstName, lblLastName, lblAddress, lblContactNo, invoiceAmt, invoiceAmtOwed, invoiceDate, invoiceNo;
    private Button makePayment, addProcedure;
    private ComboBox cmbListPatients, cmbListProcedures;
    private TextField paymentField;
    private TableView<Procedure> procedureTable;
    private TableView<Payment> paymentTable;
    private TableColumn<Procedure, Integer> procID;
    private TableColumn<Procedure, String> procedure;
    private TableColumn<Procedure, Double> amt;

    private TableColumn<Payment, Integer> paymentID;
    private TableColumn<Payment, Double> paymentAmt;
    private TableColumn<Payment, String> date;

    public InvoiceTab(){
        setText("Invoice");
        split = new SplitPane();
        split.setDividerPosition(0, 0.2500);

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

        cmbListProcedures = new ComboBox();
        cmbListProcedures.setDisable(true);
        cmbListProcedures.setPromptText("Procedures");
        cmbListProcedures.setLayoutX(12);
        cmbListProcedures.setLayoutY(460);
        cmbListProcedures.setPrefWidth(150);

        /*
        LABELS
         */

        Label label = new Label("First Name:");
        Label label1 = new Label("Last Name:");
        Label label2 = new Label("Address:");
        Label label3 = new Label("Contact No:");
        Label label7 = new Label("Invoice No:");
        Label label4 = new Label("Invoice Date:");
        Label label5 = new Label("Total:");
        Label label6 = new Label("Amount Owed:");

        lblfirstName = new Label("-");
        lblLastName = new Label("-");
        lblAddress = new Label("-");
        lblContactNo = new Label("-");
        invoiceNo = new Label("-");
        invoiceAmt = new Label("0.00");
        invoiceDate = new Label("-/-/-");
        invoiceAmtOwed = new Label("0.00");

        label6.setLayoutX(14);
        label6.setLayoutY(550);

        invoiceAmtOwed.setLayoutX(120);
        invoiceAmtOwed.setLayoutY(550);

        paymentField = new TextField();

        paymentField.setLayoutX(14);
        paymentField.setLayoutY(570);


        /*
        BUTTONS
         */

        makePayment = new Button("Make Payment");
        makePayment.setDisable(true);
        makePayment.setLayoutX(14);
        makePayment.setLayoutY(605);
        makePayment.setPrefSize(150, 25);

        addProcedure = new Button("Add Procedure");
        addProcedure.setDisable(true);
        addProcedure.setLayoutX(14);
        addProcedure.setLayoutY(500);
        addProcedure.setPrefSize(150, 25);

        /*
        GRID
         */
        GridPane gridPane = new GridPane();
        ColumnConstraints columnConstraints = new ColumnConstraints();
        ColumnConstraints columnConstraints0 = new ColumnConstraints();
        RowConstraints rowConstraints = new RowConstraints();
        RowConstraints rowConstraints0 = new RowConstraints();
        RowConstraints rowConstraints1 = new RowConstraints();
        RowConstraints rowConstraints2 = new RowConstraints();
        RowConstraints rowConstraints3 = new RowConstraints();
        RowConstraints rowConstraints4 = new RowConstraints();
        RowConstraints rowConstraints5 = new RowConstraints();

        gridPane.setLayoutX(14.0);
        gridPane.setLayoutY(71.0);
        gridPane.setPrefHeight(197.0);
        gridPane.setPrefWidth(236.0);
        gridPane.setStyle("-fx-border-style: solid;");

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(100.0);

        rowConstraints.setMaxHeight(49.0);
        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(49.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMaxHeight(49.0);
        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(49.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMaxHeight(49.0);
        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(49.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints2.setMaxHeight(49.0);
        rowConstraints2.setMinHeight(10.0);
        rowConstraints2.setPrefHeight(49.0);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints3.setMaxHeight(49.0);
        rowConstraints3.setMinHeight(10.0);
        rowConstraints3.setPrefHeight(49.0);
        rowConstraints3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints4.setMaxHeight(49.0);
        rowConstraints4.setMinHeight(10.0);
        rowConstraints4.setPrefHeight(49.0);
        rowConstraints4.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints5.setMaxHeight(49.0);
        rowConstraints5.setMinHeight(10.0);
        rowConstraints5.setPrefHeight(49.0);
        rowConstraints5.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        GridPane.setRowIndex(label, 0);
        GridPane.setRowIndex(label1, 1);
        GridPane.setRowIndex(label2, 2);
        GridPane.setRowIndex(label3, 3);
        GridPane.setRowIndex(label4, 4);
        GridPane.setRowIndex(label5, 5);
        GridPane.setRowIndex(label7, 6);
        GridPane.setRowIndex(lblfirstName, 0);
        GridPane.setRowIndex(lblLastName, 1);
        GridPane.setRowIndex(lblAddress, 2);
        GridPane.setRowIndex(lblContactNo, 3);
        GridPane.setRowIndex(invoiceDate, 4);
        GridPane.setRowIndex(invoiceAmt, 5);
        GridPane.setRowIndex(invoiceNo, 6);
        GridPane.setColumnIndex(lblfirstName, 1);
        GridPane.setColumnIndex(lblLastName, 1);
        GridPane.setColumnIndex(lblAddress, 1);
        GridPane.setColumnIndex(lblContactNo, 1);
        GridPane.setColumnIndex(invoiceDate, 1);
        GridPane.setColumnIndex(invoiceAmt, 1);
        GridPane.setColumnIndex(invoiceNo, 1);
        gridPane.setPadding(new Insets(10.0));

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getRowConstraints().add(rowConstraints2);
        gridPane.getRowConstraints().add(rowConstraints3);
        gridPane.getRowConstraints().add(rowConstraints4);
        gridPane.getRowConstraints().add(rowConstraints5);
        gridPane.getChildren().add(label);
        gridPane.getChildren().add(label1);
        gridPane.getChildren().add(label2);
        gridPane.getChildren().add(label3);
        gridPane.getChildren().add(label4);
        gridPane.getChildren().add(label5);
        gridPane.getChildren().add(label7);
        gridPane.getChildren().add(lblfirstName);
        gridPane.getChildren().add(lblLastName);
        gridPane.getChildren().add(lblAddress);
        gridPane.getChildren().add(lblContactNo);
        gridPane.getChildren().add(invoiceDate);
        gridPane.getChildren().add(invoiceAmt);
        gridPane.getChildren().add(invoiceNo);

        left.getChildren().addAll(cmbListPatients, gridPane, label6, invoiceAmtOwed, paymentField,
                makePayment, cmbListProcedures, addProcedure);
        return left;
    }

    /*
    right hand side of the window
     */
    private AnchorPane getRight(){
        right = new AnchorPane();
        SplitPane splitPane = new SplitPane();

        procedureTable = new TableView<>();
        procedureTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn pheading = new TableColumn("Procedures");
        procID = new TableColumn<>("#");
        procedure = new TableColumn<>("Procedure");
        amt = new TableColumn<>("Price");


        procID.setStyle("-fx-alignment: CENTER;");
        procedure.setStyle("-fx-alignment: CENTER;");
        amt.setStyle("-fx-alignment: CENTER;");


        procID.setCellValueFactory(new PropertyValueFactory<Procedure, Integer>("procNo"));
        procedure.setCellValueFactory(new PropertyValueFactory<Procedure, String>("procName"));
        amt.setCellValueFactory(new PropertyValueFactory<Procedure, Double>("procCost"));

        pheading.getColumns().addAll(procID, procedure, amt);
        procedureTable.getColumns().addAll(pheading);

        paymentTable = new TableView<>();
        paymentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn payHeading = new TableColumn("Payments");
        paymentID = new TableColumn<>("#");
        paymentAmt = new TableColumn<>("Amount");
        date = new TableColumn<>("Date");

        paymentID.setStyle("-fx-alignment: CENTER;");
        paymentAmt.setStyle("-fx-alignment: CENTER;");
        date.setStyle("-fx-alignment: CENTER;");

        paymentID.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("paymentNo"));
        paymentAmt.setCellValueFactory(new PropertyValueFactory<Payment, Double>("paymentAmt"));
        date.setCellValueFactory(new PropertyValueFactory<Payment, String>("date"));


        payHeading.getColumns().addAll(paymentID, paymentAmt, date);
        paymentTable.getColumns().addAll(payHeading);

        splitPane.getItems().addAll(procedureTable, paymentTable);

        right.getChildren().addAll(splitPane);
        AnchorPane.setBottomAnchor(splitPane, 0.0);
        AnchorPane.setLeftAnchor(splitPane, 0.0);
        AnchorPane.setTopAnchor(splitPane, 0.0);
        AnchorPane.setRightAnchor(splitPane, 0.0);
        return right;
    }

    public TableView getProcedureTable(){
        return procedureTable;
    }

    public Button getMakePayment() {
        return makePayment;
    }

    public void setMakePayment(Button makePayment) {
        this.makePayment = makePayment;
    }

    public ComboBox getCmbListPatients() {
        return cmbListPatients;
    }

    public void setCmbListPatients(ComboBox cmbListPatients) {
        this.cmbListPatients = cmbListPatients;
    }

    public void setProcedureTable(TableView<Procedure> procedureTable) {
        this.procedureTable = procedureTable;
    }

    public TableColumn<Procedure, Integer> getProcID() {
        return procID;
    }

    public void setProcID(TableColumn<Procedure, Integer> procID) {
        this.procID = procID;
    }

    public TableColumn<Procedure, Double> getAmt() {
        return amt;
    }

    public void setAmt(TableColumn<Procedure, Double> amt) {
        this.amt = amt;
    }

    public TableColumn<Payment, String> getDate() {
        return date;
    }

    public void setDate(TableColumn<Payment, String> date) {
        this.date = date;
    }

    public TableColumn<Procedure, String> getProcedures() {
        return procedure;
    }

    public void setProcedures(TableColumn<Procedure, String> procedure) {
        this.procedure = procedure;
    }


    public Label getLblfirstName() {
        return lblfirstName;
    }

    public void setLblfirstName(Label lblfirstName) {
        this.lblfirstName = lblfirstName;
    }

    public Label getLblLastName() {
        return lblLastName;
    }

    public void setLblLastName(Label lblLastName) {
        this.lblLastName = lblLastName;
    }

    public Label getLblAddress() {
        return lblAddress;
    }

    public void setLblAddress(Label lblAddress) {
        this.lblAddress = lblAddress;
    }

    public Label getLblContactNo() {
        return lblContactNo;
    }

    public void setLblContactNo(Label lblContactNo) {
        this.lblContactNo = lblContactNo;
    }

    public Label getInvoiceAmt() {
        return invoiceAmt;
    }

    public void setInvoiceAmt(Label invoiceAmt) {
        this.invoiceAmt = invoiceAmt;
    }

    public Label getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Label invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Button getAddProcedure() {
        return addProcedure;
    }

    public void setAddProcedure(Button addProcedure) {
        this.addProcedure = addProcedure;
    }

    public ComboBox getCmbListProcedures() {
        return cmbListProcedures;
    }

    public void setCmbListProcedures(ComboBox cmbListProcedures) {
        this.cmbListProcedures = cmbListProcedures;
    }

    public Label getInvoiceAmtOwed() {
        return invoiceAmtOwed;
    }

    public void setInvoiceAmtOwed(Label invoiceAmtOwed) {
        this.invoiceAmtOwed = invoiceAmtOwed;
    }

    public TextField getPaymentField() {
        return paymentField;
    }

    public void setPaymentField(TextField paymentField) {
        this.paymentField = paymentField;
    }

    public TableView getPaymentTable() {
        return paymentTable;
    }

    public void setPaymentTable(TableView<Payment> paymentTable) {
        this.paymentTable = paymentTable;
    }

    public TableColumn<Payment, Integer> getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(TableColumn<Payment, Integer> paymentID) {
        this.paymentID = paymentID;
    }

    public TableColumn<Payment, Double> getPaymentAmt() {
        return paymentAmt;
    }

    public void setPaymentAmt(TableColumn<Payment, Double> paymentAmt) {
        this.paymentAmt = paymentAmt;
    }

    public Label getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Label invoiceNo) {
        this.invoiceNo = invoiceNo;
    }
}
