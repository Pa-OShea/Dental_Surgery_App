package Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by Patrick on 23-Mar-17.
 */
public class LoginTab extends Tab {
    private GridPane view;
    private Text sceneTitle;
    private Label warning, userName, pw;
    private ComboBox userCMB;
    private PasswordField pwBox;
    private Button btn;
    private HBox hbBtn;


    public LoginTab(){
        createAndConfigurePane();
        createAndLayoutControls();
    }

    public Parent asParent(){
        return view;
    }

    private void createAndConfigurePane(){
        setText("Login");
        view = new GridPane();

        view.setAlignment(Pos.CENTER);
        view.setHgap(10);
        view.setVgap(10);
        view.setPadding(new Insets(25,25,25,25));

        setContent(view);
    }

    private void createAndLayoutControls(){
        sceneTitle = new Text("Welcome");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        view.add(sceneTitle, 0, 0, 2, 1);

        warning = new Label("");
        view.add(warning, 0,4);

        userName = new Label("User Name:");
        view.add(userName, 0, 1);

        userCMB = new ComboBox();
        userCMB.setMinWidth(180);
        view.add(userCMB, 1, 1);

        pw = new Label("Password:");
        view.add(pw, 0, 2);

        pwBox = new PasswordField();
        view.add(pwBox, 1, 2);

        btn = new Button("Sign in");
        btn.setDisable(true);
        hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        view.add(hbBtn, 1, 4);
    }

//    public Stage show(){
//        Stage stage = new Stage();
//        Scene scene = new Scene(asParent());
//        return scene;
//    }

    public Button getButton(){
        return btn;
    }

    public ComboBox getUserCMB(){
        return userCMB;
    }

    public PasswordField getPwBox(){
        return pwBox;
    }

    public Label getWarning() {
        return warning;
    }

    public void setWarning(Label warning) {
        this.warning = warning;
    }
}
