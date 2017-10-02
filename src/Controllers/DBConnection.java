package Controllers;

import Models.*;
import org.apache.derby.jdbc.EmbeddedDriver;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Patrick on 23-Apr-17.
 */
public class DBConnection {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:DB;";
    private static DBConnection self = new DBConnection();
    private String statement;
    private Connection conn;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ArrayList<Dentist> dentists;
    private ArrayList<Procedure> procedures;
    
    public DBConnection(){
    }

    public void createConnection(){
        try {
            Driver derbyEmbeddedDriver = new EmbeddedDriver();
            DriverManager.registerDriver(derbyEmbeddedDriver);
            conn = DriverManager.getConnection(JDBC_URL + "create=true");
        }
        catch (Exception except) {
            System.out.println("in connection" + except);
            except.printStackTrace();
        }
    }

    public void shutdown() {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException ex) {
            if (((ex.getErrorCode() == 50000) && ("XJ015".equals(ex.getSQLState())))) {
                System.out.println("Derby shut down normally");
            } else {
                System.err.println("Derby did not shut down normally");
                System.err.println(ex.getMessage());
         }
      }
    }

    public static DBConnection getInstance(){
        return self;
    }

    //DENTIST TABLE SQL
    public ArrayList<Dentist> getDentists(){
        dentists = new ArrayList<>();

        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.DENTIST");

            while(rs.next()){
                int ID = rs.getInt("dentist_no");
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");
                String contactNo = rs.getString("contact_no");
                String password = rs.getString("password");
                Dentist dentist = new Dentist(fName, lName, contactNo, password);
                dentist.setId(ID);

                if(getPatients(dentist.getId()) != null){
                    dentist.setPatientList(getPatients(dentist.getId()));
                }

                dentists.add(dentist);
            }
            stmt.close();
            return dentists;
        }catch (Exception ex){
            System.err.print(ex);
            ex.printStackTrace();
        }

        return null;
    }

    public Dentist getDentist(int dentistNo){
        Dentist dentist = new Dentist();
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.DENTIST WHERE DENTIST_NO = " + dentistNo);

            if(rs.next()){
                int ID = rs.getInt("dentist_no");
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");
                String contactNo = rs.getString("contact_no");
                String password = rs.getString("password");
                dentist = new Dentist(fName, lName, contactNo, password);
                dentist.setId(ID);


            }
            stmt.close();
            return dentist;
        }catch (Exception ex){
            System.err.print(ex);
            ex.printStackTrace();
        }

        return null;
    }

    public void addDentist(Dentist dentist){
        try{
            int ID = dentist.getId();
            String name = dentist.getFirstName();
            String lName = dentist.getLastName();
            String contactNo = dentist.getContactNo();
            String password = dentist.getPassword();

            stmt = conn.createStatement();

            pstmt = conn.prepareStatement("INSERT INTO DENTIST VALUES (?,?,?,?,?)");
            pstmt.setInt(1, ID);
            pstmt.setString(2,name);
            pstmt.setString(3, lName);
            pstmt.setString(4, contactNo);
            pstmt.setString(5, password);
            pstmt.execute();

            stmt.close();
            System.out.println("Dentist Added");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void editDentist(Dentist dentist){
        try {
            stmt = conn.createStatement();
            stmt.execute("UPDATE Dentist SET fName = '" + dentist.getFirstName() + "'" +
                    ", lName = '" + dentist.getLastName() + "'" +
                    ", contact_no = '" + dentist.getContactNo() + "'" +
                    ", password = '" + dentist.getPassword() + "'" +
                    "WHERE dentist_no = " + dentist.getId());
            stmt.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public int highestDentistNo(){
        int num = 1;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(DENTIST_NO) AS Highest_ID FROM APP.DENTIST");
            if (rs.next()) {
                num = rs.getInt("Highest_ID") + 1;
            }
            return num;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
    //END OF DENTIST TABLE SQL

    //PATIENT TABLE SQL
    public void addPatient(Patient patient, int dentist){

        try {
            int id = highestPatientNo();
            String fName = patient.getFirstName();
            String lName = patient.getLastName();
            String contactNo = patient.getContactNo();
            String address = patient.getAddress();

            stmt = conn.createStatement();
            pstmt = conn.prepareStatement("INSERT INTO APP.PATIENT VALUES (?,?,?,?,?,?)");
            pstmt.setInt(1, id);
            pstmt.setString(2, fName);
            pstmt.setString(3, lName);
            pstmt.setString(4, contactNo);
            pstmt.setString(5, address);
            pstmt.setInt(6, dentist);
            pstmt.execute();
            stmt.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void editPatient(Patient patient){
        try {
            int ID = patient.getId();
            String fName = patient.getFirstName();
            String lName = patient.getLastName();
            String address = patient.getAddress();
            String contactNo = patient.getContactNo();
            stmt = conn.createStatement();
            stmt.execute("UPDATE APP.PATIENT SET PATIENT_NO = " + ID +
                    ", FNAME = '" + fName + "'" +
                    ", LNAME = '" + lName + "'" +
                    ", CONTACT_NO = '" + contactNo + "'" +
                    ", ADDRESS = '" + address + "'" +
                    "WHERE PATIENT_NO = " + ID);
            stmt.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void deletePatient(Patient patient){
        try {
            for(int i = 0; i<patient.getP_invoiceList().size(); i++){
                deletePayment(patient.getP_invoiceList().get(i).getInvoiceNo());
                deleteProcedureFormInvoice(patient.getP_invoiceList().get(i).getInvoiceNo());
            }

            deleteInvoice(patient.getId());
            stmt = conn.createStatement();
            stmt.execute("DELETE FROM Patient WHERE patient_no = " + patient.getId());
            stmt.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public ArrayList<Patient> getPatients(int dentist){
        ArrayList<Patient> patients = new ArrayList<>();

        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.PATIENT WHERE DENTIST_NO = " + dentist);

            while(rs.next()){
                int PatientNo = rs.getInt("patient_no");
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");
                String contactNo = rs.getString("contact_no");
                String address = rs.getString("address");
                Patient patient = new Patient(fName, lName, contactNo, address);
                patient.setId(PatientNo);

                if(getInvoices(patient.getId()) != null){
                    patient.setP_invoiceList(getInvoices(patient.getId()));
                }
                patients.add(patient);
            }
            stmt.close();
            return patients;
        }catch (Exception ex){
            System.err.print(ex);
            ex.printStackTrace();
        }

        return null;
    }

    public Patient getPatient(int patient_no){
        Patient patient = new Patient();
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.PATIENT WHERE PATIENT_NO = " + patient_no);

            if(rs.next()){
                int PatientNo = rs.getInt("patient_no");
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");
                String contactNo = rs.getString("contact_no");
                String address = rs.getString("address");
                patient = new Patient(fName, lName, contactNo, address);
                patient.setId(PatientNo);

                if(getInvoices(patient.getId()) != null){
                    patient.setP_invoiceList(getInvoices(patient.getId()));
                }
            }
            stmt.close();
            return patient;
        }catch (Exception ex){
            System.err.print(ex);
            ex.printStackTrace();
        }

        return null;

    }

    public int highestPatientNo(){
        int num = 1;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(PATIENT_NO) AS Highest_ID FROM APP.PATIENT");
            if (rs.next()) {
                num = rs.getInt("Highest_ID") + 1;
            }
            return num;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
    //END PATIENT TABLE SQL

    //PROCEDURE TABLE SQL
    public ArrayList<Procedure> getProcedures(){
        procedures = new ArrayList<>();

        try{
            stmt = conn.createStatement();
            ResultSet procs = stmt.executeQuery("SELECT * FROM APP.PROCEDURE_TABLE");

            while(procs.next()){
                int procNo = procs.getInt("procedure_no");
                String procName = procs.getString("procName");
                Double procCost = procs.getDouble("procCost");
                Procedure procedure = new Procedure(procName, procCost);
                procedure.setProcNo(procNo);
                procedures.add(procedure);
            }
            stmt.close();
            return procedures;
        }catch (Exception ex){
            System.err.print(ex);
            ex.printStackTrace();
        }

        return null;
    }

    public Procedure getProcedure(int procNo){
        Procedure procedure = new Procedure();

        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.PROCEDURE_TABLE WHERE PROCEDURE_NO = " + procNo);

            if (rs.next()){
                int id = rs.getInt("procedure_no");
                String name = rs.getString("procName");
                Double cost = rs.getDouble("procCost");
                procedure.setProcNo(id);
                procedure.setProcName(name);
                procedure.setProcCost(cost);
                return procedure;
            }
        }catch (SQLException e){

        }
        return null;
    }

    public void addProcedure(Procedure procedure){
        try {
            stmt = conn.createStatement();

            int ID = procedure.getProcNo();
            String name = procedure.getProcName();
            Double cost = procedure.getProcCost();

            pstmt = conn.prepareStatement("INSERT INTO APP.PROCEDURE_TABLE VALUES (?,?,?)");
            pstmt.setInt(1,ID);
            pstmt.setString(2, name);
            pstmt.setDouble(3, cost);
            pstmt.execute();

            stmt.close();
            System.out.println("Procedure added");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void editProcedure(Procedure procedure){
        try {
            stmt = conn.createStatement();
            stmt.execute("UPDATE APP.PROCEDURE_TABLE SET PROCNAME = '" + procedure.getProcName() + "',"+
                            "PROCCOST = " + procedure.getProcCost() + " WHERE PROCEDURE_NO = " + procedure.getProcNo());
            stmt.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void deleteProcedure(int ProcNo){
        try {
            stmt = conn.createStatement();
            stmt.execute("DELETE FROM APP.PROCEDURE_TABLE WHERE PROCEDURE_NO =" + ProcNo);
            stmt.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    //END PROCEDURE TABLE SQL

    //INVOICE TABLE SQL
    public ArrayList<Invoice> getAllInvoices(){
        ArrayList<Invoice> invoices = new ArrayList<>();
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.INVOICE");

            while(rs.next()){
                int invoice_no = rs.getInt("invoice_no");
                double amount = rs.getFloat("amount");
                double amountOwed = rs.getFloat("amountOwed");
                String date = rs.getString("invoice_date");
                int paid = rs.getInt("is_paid");
                boolean isPaid = false;
                if(paid == 1){
                    isPaid = true;
                }
                Invoice invoice = new Invoice();
                invoice.setInvoiceNo(invoice_no);
                invoice.setInvoiceAmt(amount);
                invoice.setInvoiceAmtOwed(amountOwed);
                invoice.setInvoiceDate(date);
                invoice.setPaid(isPaid);

                if(getPayments(invoice_no) != null){
                    invoice.setIn_paymentList(getPayments(invoice_no));
                }

                if(getProcedureFromInvoice(invoice_no) != null){
                    invoice.setIn_procList(getProcedureFromInvoice(invoice_no));
                }

                invoices.add(invoice);
            }
            stmt.close();
            return invoices;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Invoice> getInvoices(int patient_no){
        ArrayList<Invoice> invoices = new ArrayList<>();
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.INVOICE WHERE PATIENT_NO = " + patient_no);

            while(rs.next()){
                int invoice_no = rs.getInt("invoice_no");
                double amount = rs.getFloat("amount");
                double amountOwed = rs.getFloat("amountOwed");
                String date = rs.getString("invoice_date");
                int paid = rs.getInt("is_paid");
                boolean isPaid = false;
                if(paid == 1){
                    isPaid = true;
                }
                Invoice invoice = new Invoice();
                invoice.setInvoiceNo(invoice_no);
                invoice.setInvoiceAmt(amount);
                invoice.setInvoiceAmtOwed(amountOwed);
                invoice.setInvoiceDate(date);
                invoice.setPaid(isPaid);

                if(getPayments(invoice_no) != null){
                    invoice.setIn_paymentList(getPayments(invoice_no));
                }

                if(getProcedureFromInvoice(invoice_no) != null){
                    invoice.setIn_procList(getProcedureFromInvoice(invoice_no));
                }

                invoices.add(invoice);
            }
            stmt.close();
            return invoices;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Invoice getCurrentInvoice(int patient_no){
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.INVOICE WHERE PATIENT_NO = " + patient_no
                    + " AND INVOICE_NO = (SELECT MAX(INVOICE_NO) FROM APP.INVOICE WHERE PATIENT_NO = "+ patient_no+")" );

            if(rs.next()){
                Invoice invoice = new Invoice();
                int invoice_no = rs.getInt("invoice_no");
                double amount = rs.getFloat("amount");
                double amountOwed = rs.getFloat("amountOwed");
                String date = rs.getString("invoice_date");
                int isPaid = rs.getInt("is_paid");
                invoice.setInvoiceNo(invoice_no);
                invoice.setInvoiceAmt(amount);
                invoice.setInvoiceAmtOwed(amountOwed);
                invoice.setInvoiceDate(date);

                boolean paid = false;
                if(isPaid == 1){
                    paid = true;
                }
                invoice.setPaid(paid);

                if(getProcedureFromInvoice(invoice_no) != null){
                    invoice.setIn_procList(getProcedureFromInvoice(invoice_no));
                }
                if(getPayments(invoice_no) != null){
                    invoice.setIn_paymentList(getPayments(invoice_no));
                }
                return invoice;
            }
            stmt.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void addInvoice(Patient patient, Invoice invoice){
        try{
            int id = getNextInvoiceNo()+1;
            double amt = invoice.getInvoiceAmt();
            double amtOwed = invoice.getInvoiceAmtOwed();
            String date = invoice.getInvoiceDate();
            boolean ispaid = invoice.isPaid();
            int paid = 0; // 1 for true and 0 for false
            if(ispaid){
                paid = 1;
            }
            int pID = patient.getId();

            stmt = conn.createStatement();
            pstmt = conn.prepareStatement("INSERT INTO APP.INVOICE VALUES (?,?,?,?,?,?)");
            pstmt.setInt(1, id);
            pstmt.setDouble(2, amt);
            pstmt.setDouble(3, amtOwed);
            pstmt.setString(4, date);
            pstmt.setInt(5, paid);
            pstmt.setInt(6, pID);
            pstmt.execute();
            stmt.close();
            System.out.println("Invoice has been added");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void editInvoice(Invoice invoice){
        boolean ispaid = invoice.isPaid();
        int paid = 0; // 1 for true and 0 for false
        if(ispaid){
            paid = 1;
        }
        try {
            stmt = conn.createStatement();
            stmt.execute("UPDATE APP.INVOICE SET APP.INVOICE.AMOUNT = " + invoice.getInvoiceAmt() + ","+
                    "APP.INVOICE.AMOUNTOWED = " + invoice.getInvoiceAmtOwed() +
                    ", APP.INVOICE.IS_PAID = " + paid +
                    " WHERE APP.INVOICE.INVOICE_NO = " + invoice.getInvoiceNo());
            stmt.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void deleteInvoice(int patient_no){
        try {
            stmt = conn.createStatement();
            stmt.execute("DELETE FROM APP.INVOICE WHERE PATIENT_NO IN (SELECT PATIENT_NO FROM INVOICE WHERE PATIENT_NO = + " + patient_no + ")");
            stmt.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public int getNextInvoiceNo(){
        int num = 0;

        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(INVOICE_NO) AS Highest_id FROM APP.INVOICE");

            while(rs.next()){
                num = rs.getInt("Highest_id");
            }
            stmt.close();
            return num;
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return num;
    }
    //END INVOICE TABLE SQL

    //PAYMENT TABLE SQL
    public ArrayList<Payment> getPayments(int invoiceNo){
        ArrayList<Payment> payments = new ArrayList<>();

        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.PAYMENT WHERE invoice_no = " + invoiceNo);

            while(rs.next()){
                int payment_no = rs.getInt("payment_no");
                double amount = rs.getFloat("amount");
                String date = rs.getString("payment_date");
                Payment payment = new Payment();
                payment.setPaymentNo(payment_no);
                payment.setPaymentAmt(amount);
                payment.setDate(date);
                payments.add(payment);
            }
            stmt.close();
            return payments;
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }
    public void addPayment(Payment payment, Invoice invoice){
        int id = getNextPaymentNo()+1;
        double amt = payment.getPaymentAmt();
        String date = payment.getDate();
        int IId = invoice.getInvoiceNo();
        try {
            stmt = conn.createStatement();
            pstmt = conn.prepareStatement("INSERT INTO APP.PAYMENT VALUES (?,?,?,?)");
            pstmt.setInt(1, id);
            pstmt.setDouble(2, amt);
            pstmt.setString(3, date);
            pstmt.setInt(4, IId);
            pstmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePayment(int invoiceNo){
        try {
            stmt = conn.createStatement();
            stmt.execute("DELETE FROM APP.PAYMENT WHERE INVOICE_NO IN (SELECT INVOICE_NO FROM INVOICE WHERE INVOICE.INVOICE_NO = " + invoiceNo +")");
            stmt.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public int getNextPaymentNo(){
        int num = 0;

        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT max(PAYMENT_NO) AS highest_id FROM APP.PAYMENT");

            while(rs.next()){
                num = rs.getInt("highest_id");
            }
            stmt.close();
            return num;
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return num;
    }
    //END PAYMENT TABLE SQL

    //INVOICE_PROCEDURE TABLE SQL
    public ArrayList<Procedure> getProcedureFromInvoice(int invoiceNo){
        ArrayList<Procedure> procedures = new ArrayList<>();

        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PROCEDURE_NO FROM APP.INVOICE_PROCEDURE WHERE invoice_no = " + invoiceNo);

            while(rs.next()){
                int procedureNo = rs.getInt("procedure_no");
                procedures.add(getProcedure(procedureNo));
            }
            stmt.close();
            return procedures;
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    public void addProcedureToInvoice(Procedure procedure, Invoice invoice){
        int Pid = procedure.getProcNo();
        int InvoiceId = invoice.getInvoiceNo();
        int nexttablekey = getHighestTableNo()+1;
        try {
            stmt = conn.createStatement();
            pstmt = conn.prepareStatement("INSERT INTO APP.INVOICE_PROCEDURE VALUES (?,?,?)");
            pstmt.setInt(1, nexttablekey);
            pstmt.setInt(2, InvoiceId);
            pstmt.setInt(3, Pid);
            pstmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteProcedureFormInvoice(int invoiceNo){
        try {
            stmt = conn.createStatement();
            stmt.execute("DELETE FROM APP.INVOICE_PROCEDURE WHERE INVOICE_NO IN (SELECT INVOICE_NO FROM INVOICE_PROCEDURE WHERE INVOICE_NO = "+ invoiceNo+")");
            stmt.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public int getHighestTableNo(){
        int num = 0;

        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(TABLEKEY) AS tablekey FROM APP.INVOICE_PROCEDURE");

            while(rs.next()){
                num = rs.getInt("tablekey");
            }
            stmt.close();
            return num;
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return num;
    }
    //END INVOICE_PROCEDURE TABLE SQL

    public void createTables(String table){
        switch (table) {
            case "Dentist":
                createDentistTable();
                break;
            case "Patient":
                createPatientTable();
                break;
            case "Procedure_table":
                createProcedureTable();
                break;
            case "Invoice":
                createInvoiceTable();
                break;
            case "Payment":
                createPaymentTable();
                break;
            case "Invoice_Procedure":
                createInvoiceProcedures();
                break;

            default:
                break;
        }
    }

    public void checkTables() {
        try {
            String[] tables = {"Dentist", "Patient", "Procedure_table", "Invoice", "Payment", "Invoice_Procedure"};
            DatabaseMetaData metadata = conn.getMetaData();

            for (String table : tables) {
                ResultSet rs = metadata.getTables(null, null, table.toUpperCase(), null);
                if (!rs.next()) {
                    createTables(table);
                }else{
                    System.out.println(table + " already exits");
                }
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void createDentistTable(){

        statement = "CREATE TABLE Dentist (" +
                         "dentist_no INT NOT NULL," +
                         "fName VARCHAR(20) NOT NULL," +
                         "lName VARCHAR(20) NOT NULL," +
                         "contact_no VARCHAR(10) NOT NULL," +
                         "password VARCHAR(30) NOT NULL," +
                         "PRIMARY KEY (dentist_no))";

        try{
            stmt = conn.createStatement();
            stmt.execute(statement);
            stmt.close();
            System.out.println("Dentist Table Created");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void createPatientTable(){
        statement = "CREATE TABLE Patient (" +
                "patient_no INT NOT NULL," +
                "fName VARCHAR(20) NOT NULL," +
                "lName VARCHAR(20) NOT NULL," +
                "contact_no VARCHAR(10) NOT NULL," +
                "address VARCHAR(40) NOT NULL," +
                "dentist_no INT NOT NULL," +
                "CONSTRAINT dentist_fk FOREIGN KEY (dentist_no) REFERENCES Dentist(dentist_no)," +
                "PRIMARY KEY (patient_no))";

        try{
            stmt = conn.createStatement();
            stmt.execute(statement);
            stmt.close();
            System.out.println("Patient Table Created");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void createProcedureTable(){
        statement = "CREATE TABLE Procedure_table ("  +
                "procedure_no INT NOT NULL," +
                "procName VARCHAR(20) NOT NULL," +
                "procCost FLOAT(2) NOT NULL," +
                "PRIMARY KEY (procedure_no))";

        try{
            stmt = conn.createStatement();
            stmt.execute(statement);
            stmt.close();
            System.out.println("Procedure_table Table Creating");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void createPaymentTable() {

        statement = "CREATE TABLE PAYMENT ("  +
                "payment_no INT NOT NULL," +
                "amount FLOAT(2) NOT NULL," +
                "payment_date VARCHAR(10)," +
                "invoice_no INT NOT NULL," +
                "PRIMARY KEY (payment_no),"+
                "CONSTRAINT payment_invoice_fk FOREIGN KEY (invoice_no) REFERENCES Invoice(invoice_no))";

        try{
            stmt = conn.createStatement();
            stmt.execute(statement);
            stmt.close();
            System.out.println("Payment Table Creating");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void createInvoiceTable() {
        statement = "CREATE TABLE Invoice ("  +
                "invoice_no INT NOT NULL," +
                "amount FLOAT(2)," +
                "amountOwed FLOAT(2)," +
                "invoice_date VARCHAR(10) NOT NULL," +
                "is_paid INT NOT NULL," +
                "patient_no INT NOT NULL," +
                "PRIMARY KEY (invoice_no)," +
                "CONSTRAINT patient_fk FOREIGN KEY (patient_no) REFERENCES Patient(patient_no))";

        try{
            stmt = conn.createStatement();
            stmt.execute(statement);
            //stmt.execute(statement);
            stmt.close();
            System.out.println("Invoice Table Created");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private void createInvoiceProcedures(){
        statement = "CREATE TABLE Invoice_Procedure (" +
                "tablekey INT," +
                "invoice_no INT NOT NULL," +
                "procedure_no INT NOT NULL," +
                "PRIMARY KEY (tablekey)," +
                "CONSTRAINT invoice_fk FOREIGN KEY (invoice_no) REFERENCES Invoice(invoice_no)," +
                "CONSTRAINT procedure_fk FOREIGN KEY (procedure_no) REFERENCES Procedure_table(procedure_no)" +
                ")";

        try{
            stmt = conn.createStatement();
            stmt.execute(statement);
            stmt.close();
            System.out.println("Invoice_Procedure Table Created");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    

    
}
