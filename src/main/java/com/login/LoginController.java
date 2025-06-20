package com.login;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private SplitPane splitPane;

    @FXML
    private Label loginMessage;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button closeButton;

    @FXML
    public void initialize() {
        SplitPane.Divider divider = splitPane.getDividers().get(0);
        divider.positionProperty().addListener((obs, oldVal, newVal) -> {
            divider.setPosition(0.5);
        });
    }

    @FXML
    public void loginButtonOnAction(ActionEvent e) throws IOException {
        if (usernameField.getText().isBlank() == false &&
                passwordField.getText().isBlank() == false) {
            if (usernameField.getText().equals("Timur") && passwordField.getText().equals("12345")) {
                loginMessage.setText("Succesfull login");
            } else {
                loginMessage.setText("Invalid username or password");
            }
        }
    }

    @FXML
    public void closeButtonOnAction(ActionEvent e) throws IOException {
        System.exit(0);
    }

}
