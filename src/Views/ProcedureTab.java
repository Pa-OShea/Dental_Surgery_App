package Views;

import Models.Procedure;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * Created by Patrick on 03-Mar-17.
 */
public class ProcedureTab extends Tab {
    private SplitPane split;
    private AnchorPane left, right;
    private TableView<Procedure> procedureTable;
    private ComboBox cmbListProcedure;
    private TextField txtProcName, txtCost;
    private Button addProcedure, editProcedure, removeProcedure;
    private TableColumn<Procedure, Integer> ID;
    private TableColumn<Procedure, String> name;
    private TableColumn<Procedure, Double> cost;

    public ProcedureTab(){
        setText("Procedure");
        split = new SplitPane();
        split.setDividerPosition(0, 0.3357);

        split.getItems().addAll(getLeft(), getRight());

        setContent(split);
    }

    /*
    left hand side of the window
     */
    private AnchorPane getLeft(){
        left = new AnchorPane();


        cmbListProcedure = new ComboBox();
        cmbListProcedure.setPromptText("Procedures");
        cmbListProcedure.setLayoutX(12);
        cmbListProcedure.setLayoutY(20);
        cmbListProcedure.setPrefWidth(150);

        /*
        LABELS
         */

        Label lblName = new Label("Name:");
        lblName.setLayoutX(14);
        lblName.setLayoutY(95);

        Label lblCost = new Label("Cost:");
        lblCost.setLayoutX(14);
        lblCost.setLayoutY(150);

        /*
        TEXTFIELDS
         */

        txtProcName = new TextField();
        txtProcName.setLayoutX(175);
        txtProcName.setLayoutY(95);
        txtProcName.setPrefWidth(210);

        txtCost = new TextField();
        txtCost.setLayoutX(175);
        txtCost.setLayoutY(150);
        txtCost.setPrefWidth(210);


        /*
        BUTTONS
         */

        addProcedure = new Button("Add");
        addProcedure.setLayoutX(14);
        addProcedure.setLayoutY(605);
        addProcedure.setPrefSize(75, 25);

        editProcedure = new Button("Update");
        editProcedure.setDisable(true);
        editProcedure.setLayoutX(165);
        editProcedure.setLayoutY(605);
        editProcedure.setPrefSize(75, 25);

        removeProcedure = new Button("Remove");
        removeProcedure.setDisable(true);
        removeProcedure.setLayoutX(316);
        removeProcedure.setLayoutY(605);
        removeProcedure.setPrefSize(75, 25);

        left.getChildren().addAll(cmbListProcedure, lblName, lblCost,
                txtProcName, txtCost,
                addProcedure, editProcedure, removeProcedure);
        return left;
    }

    /*
    right hand side of the window
     */
    private AnchorPane getRight(){
        right = new AnchorPane();

        procedureTable = new TableView();
        procedureTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        ID = new TableColumn<>("ID");
        name = new TableColumn<>("Name");
        cost = new TableColumn<>("Cost");

        ID.setStyle("-fx-alignment: CENTER;");
        name.setStyle("-fx-alignment: CENTER;");
        cost.setStyle("-fx-alignment: CENTER;");

        ID.setCellValueFactory(new PropertyValueFactory<Procedure, Integer>("procNo"));
        name.setCellValueFactory(new PropertyValueFactory<Procedure, String>("procName"));
        cost.setCellValueFactory(new PropertyValueFactory<Procedure, Double>("procCost"));

        procedureTable.getColumns().addAll(ID, name, cost);

        right.getChildren().addAll(procedureTable);
        AnchorPane.setBottomAnchor(procedureTable, 0.0);
        AnchorPane.setLeftAnchor(procedureTable, 0.0);
        AnchorPane.setTopAnchor(procedureTable, 0.0);
        AnchorPane.setRightAnchor(procedureTable, 0.0);
        return right;
    }

    public TableView<Procedure> getProcedureTable(){
        return procedureTable;
    }

    public ComboBox getCmbListProcedure() {
        return cmbListProcedure;
    }

    public TextField getTxtProcName() {
        return txtProcName;
    }

    public TextField getTxtCost() {
        return txtCost;
    }

    public Button getAddProcedure() {
        return addProcedure;
    }

    public Button getEditProcedure() {
        return editProcedure;
    }

    public Button getRemoveProcedure() {
        return removeProcedure;
    }

    public TableColumn<Procedure, Integer> getID() {
        return ID;
    }

    public TableColumn<Procedure, String> getName() {
        return name;
    }

    public TableColumn<Procedure, Double> getCost() {
        return cost;
    }
}
