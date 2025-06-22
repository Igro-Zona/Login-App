package com.login;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MainController {
    @FXML
    private BorderPane mainPane;

    @FXML
    private Button modeButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label mainMessage;

    private String username;

    @FXML
    public void exitOnAction(ActionEvent e) throws IOException {
        App.setRoot("login");
    }

    public void setUsername(String username) {
        this.username = username;
        mainMessage.setText("Welcome " + username + "!");
    }

}
