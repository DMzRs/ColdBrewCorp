package icedcoffee.coldbrewco;
import Database.DatabaseLogin;
import ForEnkeepingLoginId.AdminId;
import ForEnkeepingLoginId.LoginId;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class ControllerLogin {

    @FXML
    private TextField usernameAdmin;
    @FXML
    private PasswordField passwordAdmin;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField newUserField;
    @FXML
    private PasswordField newPassField;
    @FXML
    private PasswordField passwordField;

    //for normal user login
    @FXML
    protected void onLoginButtonClick() throws IOException {
        DatabaseLogin login = new DatabaseLogin();
        String username = usernameField.getText();
        String password = passwordField.getText();
        int currentLoginID = login.ReturnLoginEmployee(username,password);
        if(currentLoginID > 0){
            LoginId.setLoginId(currentLoginID);
            JOptionPane.showMessageDialog(null,"Login Successful");
            switchtoMain();
        } else {
            JOptionPane.showMessageDialog(null,"Login Failed");
        }
    }

    //for admin login
    @FXML
    protected void onLoginAdminButtonClick() throws IOException {
        DatabaseLogin login = new DatabaseLogin();
        String username = usernameAdmin.getText();
        String password = passwordAdmin.getText();
        int currentAdminId = login.ReturnLoginAdmin(username,password);
        if(currentAdminId > 0){
            AdminId.setAdminId(currentAdminId);
            JOptionPane.showMessageDialog(null,"Admin Login Successful");
            switchtoAdminMainPage();
        } else {
            JOptionPane.showMessageDialog(null,"Admin Login Failed");
        }
    }


    //to switch to admin login Page
    @FXML
    protected void onHiddenAdminLoginButtonClick() throws IOException {
        switchtoAdminLogin();
    }


    //button to switch back to log in account from admin login page
    @FXML
    protected void onHiddenLoginButtonClick() throws IOException {
        switchtoLoginAccount(usernameAdmin);

    }


    //to switch stage to admin login
    private void switchtoAdminLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppLogin.class.getResource("adminLogin.fxml"));
        Scene adminLogin = new Scene(fxmlLoader.load(), 650, 400);

        Stage currentStage = (Stage) usernameField.getScene().getWindow();
        currentStage.setScene(adminLogin);
        currentStage.setTitle("Admin Login Page");
        currentStage.setResizable(false);
        currentStage.centerOnScreen();
        currentStage.show();
    }



    //to switch stage to log in account again
    private void switchtoLoginAccount(TextField textField) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppLogin.class.getResource("Login.fxml"));
        Scene loginAccount = new Scene(fxmlLoader.load(), 600, 400);

        Stage currentStage = (Stage) textField.getScene().getWindow();
        currentStage.setScene(loginAccount);
        currentStage.setTitle("Login Page");
        currentStage.centerOnScreen();
        currentStage.setResizable(false);
        currentStage.show();

    }

    //to switch to main page
    private void switchtoMain() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppLogin.class.getResource("MainPage.fxml"));
        Scene mainAccount = new Scene(fxmlLoader.load(), 900, 700);

        Stage currentStage = (Stage) usernameField.getScene().getWindow();
        currentStage.setScene(mainAccount);
        currentStage.setTitle("Main Page");
        currentStage.centerOnScreen();
        currentStage.setResizable(false);
        currentStage.show();
    }

    private void switchtoAdminMainPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppLogin.class.getResource("AdminMainPage.fxml"));
        Scene adminPage = new Scene(fxmlLoader.load(), 900, 700);

        Stage currentStage = (Stage) usernameAdmin.getScene().getWindow();
        currentStage.setScene(adminPage);
        currentStage.setTitle("Admin Main Page");
        currentStage.centerOnScreen();
        currentStage.setResizable(false);
        currentStage.show();
    }
}
