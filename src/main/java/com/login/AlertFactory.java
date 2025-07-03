package com.login;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertFactory {
    public void createDBAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Database Error");
        alert.setHeaderText("An error occurred");
        alert.setContentText("Please ensure that settings.db file is presented near Login-App.exe file");
        alert.showAndWait();
    }
}
