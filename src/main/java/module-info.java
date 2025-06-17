module com.login {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.login to javafx.fxml;

    exports com.login;
}
