package Controllers;

import Models.Invoice;
import Models.Patient;
import Models.Payment;
import Models.Procedure;

/**
 * Created by Patrick O'Shea on 25/03/2017.
 */
public class reportTable implements Comparable {
    private int patientNo, invoiceNo, procedureNo, paymentNo;
    private String patientFName, patientLName, invoiceDate, procedureName, paymentDate;
    private Double invoiceAmt, invoiceAmtOwed, paymentAmt;

    public reportTable(){

    }

    public int getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(int patientNo) {
        this.patientNo = patientNo;
    }

    public int getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public int getProcedureNo() {
        return procedureNo;
    }

    public void setProcedureNo(int procedureNo) {
        this.procedureNo = procedureNo;
    }

    public int getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(int paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getPatientFName() {
        return patientFName;
    }

    public void setPatientFName(String patientFName) {
        this.patientFName = patientFName;
    }

    public String getPatientLName() {
        return patientLName;
    }

    public void setPatientLName(String patientLName) {
        this.patientLName = patientLName;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public Double getInvoiceAmt() {
        return invoiceAmt;
    }

    public void setInvoiceAmt(Double invoiceAmt) {
        this.invoiceAmt = invoiceAmt;
    }

    public Double getPaymentAmt() {
        return paymentAmt;
    }

    public void setPaymentAmt(Double paymentAmt) {
        this.paymentAmt = paymentAmt;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getInvoiceAmtOwed() {
        return invoiceAmtOwed;
    }

    public void setInvoiceAmtOwed(Double invoiceAmtOwed) {
        this.invoiceAmtOwed = invoiceAmtOwed;
    }
}
