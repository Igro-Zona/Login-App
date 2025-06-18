package com.login;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Label message;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField usernameInput;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    public void initialize() {
        message.setText("Enter your informaion:");
    }

    public void button2OnAction(ActionEvent e) throws IOException {
        Stage stage = (Stage) button2.getScene().getWindow();
        stage.close();
    }

    public void button1OnAction(ActionEvent e) throws IOException {
        if (passwordInput.getText().isBlank() == false && usernameInput.getText().isBlank() == false) {
            message.setText("You tried to login");
        } else {
            message.setText("Invalid username or password");
        }
    }

}
