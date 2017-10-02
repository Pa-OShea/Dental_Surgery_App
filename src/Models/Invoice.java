package Models;

import java.io.Serializable;
import java.text.*;
import java.util.ArrayList;
import java.util.Date;

public class Invoice implements Serializable{
    private int invoiceNo;
    private double invoiceAmt;
    private double invoiceAmtOwed;
    private String invoiceDate;
    private boolean isPaid;
    private ArrayList<Procedure> in_procList;
    private ArrayList<Payment> in_paymentList;

    public Invoice(){
        setPaid(false);
        setIn_procList(new ArrayList<Procedure>());
        setIn_paymentList(new ArrayList<Payment>());
    }

    //GETTERS AND SETTERS

    public int getInvoiceNo() {
        return invoiceNo;
    }

    public double getInvoiceAmt() {
        return invoiceAmt;
    }

    public void setInvoiceAmt(double invoiceAmt) {
        this.invoiceAmt += invoiceAmt;
    }

//    //public Date getInvoiceDate(){
//        return this.invoiceDate;
//    }

    public String getInvoiceDate() {
        return this.invoiceDate;
        /*String stringDate;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        stringDate = dateFormat.format(invoiceDate);
        return stringDate;*/
    }

    public void setInvoiceDate(String date) {
        this.invoiceDate = date;
    }

    public ArrayList<Procedure> getIn_procList() {
        return in_procList;
    }

    public void setIn_procList(ArrayList<Procedure> in_procList) {
        this.in_procList = in_procList;
    }

    public ArrayList<Payment> getIn_paymentList() {
        return in_paymentList;
    }

    public void setIn_paymentList(ArrayList<Payment> in_paymentList) {
        this.in_paymentList = in_paymentList;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        this.isPaid = paid;
    }

    //END GETTERS AND SETTERS

    public void addPayment(Payment newPayment){
        in_paymentList.add(newPayment);
        this.invoiceAmtOwed -= newPayment.getPaymentAmt();
        if(this.invoiceAmtOwed <= 0) {
            this.invoiceAmtOwed = 0;
            setPaid(true);
        }
    }

    public void addProcedure(Procedure newProcedure){
        in_procList.add(newProcedure);
        setInvoiceAmt(newProcedure.getProcCost());
        setInvoiceAmtOwed(newProcedure.getProcCost());
    }

    public String toString (){
        return "Invoice Number: " + getInvoiceNo() + " AmtOwed = " + getInvoiceAmtOwed() + " Amt = " + getInvoiceAmt() + " date = " + getInvoiceDate();
    }

    public double getInvoiceAmtOwed() {
        return invoiceAmtOwed;
    }

    public void setInvoiceAmtOwed(double invoiceAmtOwed) {
        this.invoiceAmtOwed += invoiceAmtOwed;
    }

    public void setInvoiceNo(int invoiceNo) {
        this.invoiceNo = invoiceNo;
    }
}
