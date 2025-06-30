package com.login;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
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
        App.dbController.createTable();
        SplitPane.Divider divider = splitPane.getDividers().get(0);
        divider.positionProperty().addListener((obs, oldVal, newVal) -> {
            divider.setPosition(0.5);
        });
    }

    @FXML
    public void loginButtonOnAction(ActionEvent e) throws IOException {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        if (!username.isBlank() && !password.isBlank()) {
            if (App.dbController.userExists(username)) {
                if (password.equals(App.dbController.getUserPassword(username))) {
                    succesfullLogin(username);
                } else {
                    loginMessage.setText("Invalid password");
                }
            } else {
                App.dbController.addUser(username, password);
                succesfullLogin(username);
            }

        }
    }

    private void succesfullLogin(String username) {
        loginMessage.setText("Succesfull login");
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            try {
                App.setRootWithUser("main", username);
            } catch (IOException ex) {
            }
        });
        pause.play();
    }

    @FXML
    public void closeButtonOnAction(ActionEvent e) throws IOException {
        App.dbController.close();
        System.exit(0);
    }

}
