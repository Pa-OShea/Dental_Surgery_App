package Models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment {
    private int paymentNo;
    private double paymentAmt;
    private String date;

    public Payment(){
    }

    public Payment(double paymentAmt){
        setPaymentAmt(paymentAmt);
    }

    public int getPaymentNo() {
        return paymentNo;
    }

    public double getPaymentAmt() {
        return paymentAmt;
    }

    public void setPaymentAmt(double paymentAmt) {
        this.paymentAmt = paymentAmt;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString(){
        return "Payment No: " + getPaymentNo() + "\nAmount: " + getPaymentAmt() + "\nDate: " + getDate();
    }

    public void setPaymentNo(int paymentNo) {
        this.paymentNo = paymentNo;
    }
}
