package Views;

import Controllers.reportTable;
import Models.Invoice;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Patrick O'Shea on 08/03/2017.
 */
public class ReportTab extends Tab {
    TableView tableView;
    ComboBox comboBox;
    public ReportTab(){
        setText("Reports");

        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.setDividerPosition(0, 0.1500);


        comboBox = new ComboBox();
        comboBox.setPromptText("Select Type");
        comboBox.getItems().add("All invoices");
        comboBox.getItems().add("Unpaid Invoices");

        tableView = new TableView();

        TableColumn patient = new TableColumn("Patient");
        TableColumn<reportTable, Integer> patientNo = new TableColumn<>("#");
        TableColumn<reportTable, String> patientFName = new TableColumn<>("First name");
        TableColumn<reportTable, String> patientLName = new TableColumn<>("Last name");

        patientNo.setCellValueFactory(new PropertyValueFactory<reportTable, Integer>("patientNo"));
        patientFName.setCellValueFactory(new PropertyValueFactory<reportTable, String>("patientFName"));
        patientLName.setCellValueFactory(new PropertyValueFactory<reportTable, String>("patientLName"));
        patient.getColumns().addAll(patientNo,patientFName,patientLName);

        TableColumn invoice = new TableColumn("Invoice");
        TableColumn<reportTable, Integer> invoiceNo = new TableColumn<>("#");
        TableColumn<reportTable, String> invoiceDate = new TableColumn<>("Date");
        TableColumn<reportTable, Double> invoiceAmt = new TableColumn<>("Total");
        TableColumn<reportTable, Double> invoiceAmtOwed = new TableColumn<>("Amount Owed");

        invoiceNo.setCellValueFactory(new PropertyValueFactory<reportTable, Integer>("invoiceNo"));
        invoiceDate.setCellValueFactory(new PropertyValueFactory<reportTable, String>("invoiceDate"));
        invoiceAmt.setCellValueFactory(new PropertyValueFactory<reportTable, Double>("invoiceAmt"));
        invoiceAmtOwed.setCellValueFactory(new PropertyValueFactory<reportTable, Double>("invoiceAmtOwed"));
        invoice.getColumns().addAll(invoiceNo,invoiceDate, invoiceAmt, invoiceAmtOwed);

        TableColumn procedure = new TableColumn("Procedure");
        TableColumn<reportTable, Integer> procedureNo = new TableColumn<>("#");
        TableColumn<reportTable, String> procedureName = new TableColumn<>("Name");

        procedureNo.setCellValueFactory(new PropertyValueFactory<reportTable, Integer>("procedureNo"));
        procedureName.setCellValueFactory(new PropertyValueFactory<reportTable, String>("procedureName"));
        procedure.getColumns().addAll(procedureNo,procedureName);

        TableColumn payment = new TableColumn("Payment");
        TableColumn<reportTable, Integer> paymentNo = new TableColumn<>("#");
        TableColumn<reportTable, Integer> paymentAmt = new TableColumn<>("Amount");
        TableColumn<reportTable, String> paymentDate = new TableColumn<>("Date");

        paymentNo.setCellValueFactory(new PropertyValueFactory<reportTable, Integer>("paymentNo"));
        paymentAmt.setCellValueFactory(new PropertyValueFactory<reportTable, Integer>("paymentAmt"));
        paymentDate.setCellValueFactory(new PropertyValueFactory<reportTable, String>("paymentDate"));
        payment.getColumns().addAll(paymentNo,paymentAmt, paymentDate);



        tableView.getColumns().addAll(patient,invoice, procedure, payment);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        splitPane.getItems().addAll(comboBox,tableView);
        setContent(splitPane);
    }

    public TableView getTableView() {
        return tableView;
    }

    public void setTableView(TableView tableView) {
        this.tableView = tableView;
    }

    public ComboBox getComboBox() {
        return comboBox;
    }

    public void setComboBox(ComboBox comboBox) {
        this.comboBox = comboBox;
    }



}
