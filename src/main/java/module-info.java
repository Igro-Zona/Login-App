module com.login {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.login to javafx.fxml;
    exports com.login;
}
