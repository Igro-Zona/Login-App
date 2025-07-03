package com.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class App extends Application {

    private static Scene scene;
    public static DatabaseController dbController;
    public static AlertFactory alertFactory;

    @Override
    public void start(Stage stage) {
        alertFactory = new AlertFactory();
        try {
            dbController = new DatabaseController();
            scene = new Scene(loadFXML("login"), 1120, 630);
            stage.initStyle(StageStyle.DECORATED);
            stage.setMinWidth(1120);
            stage.setMinHeight(630);
            stage.setTitle("Login App");
            stage.setScene(scene);
            stage.getIcons().add(
                    new Image(getClass().getResourceAsStream("/com/login/login.png")));
            stage.setOnCloseRequest((event) -> {
                dbController.close();
            });
            stage.show();
        } catch (IOException | SQLException e) {
            App.alertFactory.createFatalError();
        }

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void setRootWithUser(String fxml, String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.setUsername(username);
        scene.setRoot(root);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}