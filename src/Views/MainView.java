package Views;

import Models.Payment;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * Created by Patrick on 03-Mar-17.
 */
public class MainView extends BorderPane{
    private BorderPane borderPane;
    private MenuBar menuBar;
    private Menu menuFile, menuHelp;
    private MenuItem save, log_out, exit, about;
    private TabPane tabPane;
    private PatientTab patientTab;
    private ProcedureTab procedureTab;
    private InvoiceTab invoiceTab;
    private ReportTab reportTab;
    private ProfileTab profileTab;

    public MainView(){
        createAndConfigurePane();
        getChildren().add(getBorderPane());
    }

    public Parent asParent(){
        return getTabPane();
    }

    private void createAndConfigurePane(){
        borderPane = new BorderPane();
        menuBar = new MenuBar();
        menuFile = new Menu("File");
        menuHelp = new Menu("Help");

        save = new MenuItem("Save");
        log_out = new MenuItem("Log Out");
        exit = new MenuItem("Exit");
        about = new MenuItem("About");

        menuFile.getItems().addAll(save,log_out,exit);
        menuHelp.getItems().addAll(about);
        menuBar.getMenus().addAll(menuFile, menuHelp);

        //borderPane.setTop(menuBar);

        tabPane = new TabPane();
        patientTab = new PatientTab();
        procedureTab = new ProcedureTab();
        invoiceTab = new InvoiceTab();
        reportTab = new ReportTab();
        profileTab = new ProfileTab();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getTabs().add(patientTab);
        tabPane.getTabs().add(procedureTab);
        tabPane.getTabs().add(invoiceTab);
        tabPane.getTabs().add(reportTab);
        tabPane.getTabs().add(profileTab);

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(700.0);
        setPrefWidth(1250.0);

        setTop(menuBar);
        setCenter(tabPane);
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public PatientTab getPatientTab(){
        return patientTab;
    }

    public ProcedureTab getProcedureTab(){
        return procedureTab;
    }

    public InvoiceTab getInvoiceTab(){
        return invoiceTab;
    }

    public ReportTab getReportTab(){
        return reportTab;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public Menu getMenuFile() {
        return menuFile;
    }

    public void setMenuFile(Menu menuFile) {
        this.menuFile = menuFile;
    }

    public Menu getMenuHelp() {
        return menuHelp;
    }

    public void setMenuHelp(Menu menuHelp) {
        this.menuHelp = menuHelp;
    }

    public void setSave(MenuItem save) {
        this.save = save;
    }

    public MenuItem getLog_out() {
        return log_out;
    }

    public void setLog_out(MenuItem log_out) {
        this.log_out = log_out;
    }

    public MenuItem getExit() {
        return exit;
    }

    public void setExit(MenuItem exit) {
        this.exit = exit;
    }

    public MenuItem getAbout() {
        return about;
    }

    public void setAbout(MenuItem about) {
        this.about = about;
    }

    public MenuItem getSave() {
        return save;
    }

    public ProfileTab getProfileTab() {
        return profileTab;
    }

    public void setProfileTab(ProfileTab profileTab) {
        this.profileTab = profileTab;
    }
}
