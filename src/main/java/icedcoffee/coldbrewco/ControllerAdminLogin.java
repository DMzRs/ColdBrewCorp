package icedcoffee.coldbrewco;

import Database.DatabaseLogin;
import ForEnkeepingLoginId.AdminId;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class ControllerAdminLogin {
    @FXML
    private TextField usernameAdmin;
    @FXML
    private PasswordField passwordAdmin;
    @FXML
    private Button adminLoginButton;
    @FXML
    private void initialize() {
        // Add key press listener to the admin login button
        adminLoginButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    onLoginAdminButtonClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //button to switch back to log in account from admin login page
    @FXML
    protected void onHiddenLoginButtonClick() throws IOException {
        switchtoLoginAccount();

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

    //to switch stage to log in account again
    private void switchtoLoginAccount() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppLogin.class.getResource("Login.fxml"));
        Scene loginAccount = new Scene(fxmlLoader.load(), 600, 400);

        Stage currentStage = (Stage) usernameAdmin.getScene().getWindow();
        currentStage.setScene(loginAccount);
        currentStage.setTitle("Login Page");
        currentStage.centerOnScreen();
        currentStage.setResizable(false);
        currentStage.show();

    }
}