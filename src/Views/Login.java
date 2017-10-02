package Views;

import Controllers.LoginController;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;

/**
 * Created by Patrick on 23-Mar-17.
 */
public class Login extends TabPane {
    private LoginTab loginTab;
    private CreateUserTab createUserTab;
    public Login(){
        this.loginTab = new LoginTab();
        this.createUserTab = new CreateUserTab();
        getTabs().addAll(loginTab, createUserTab);
        setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    }

    public LoginTab getLoginTab() {
        return loginTab;
    }

    public void setLoginTab(LoginTab loginTab) {
        this.loginTab = loginTab;
    }

    public CreateUserTab getCreateUserTab() {
        return createUserTab;
    }

    public void setCreateUserTab(CreateUserTab createUserTab) {
        this.createUserTab = createUserTab;
    }
}
