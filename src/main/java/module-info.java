module com.vaccine_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;

    opens com.vaccine_management_system to javafx.fxml, xstream;
    exports com.vaccine_management_system;
}