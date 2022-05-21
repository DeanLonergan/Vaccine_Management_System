package com.vaccine_management_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Driver extends Application {
    public static VBox mainVB, addCentreVB, editCentreVB, centreBoothsVB, addCentreBoothVB, editCentreBoothVB, addPatientVB, editPatientVB, smartAddAppointmentVB, customAddAppointmentVB = new VBox();
    public static Scene mainMenu, addCentre, editCentre, centreBooths, addCentreBooth, editCentreBooth, addPatient, editPatient, smartAddAppointment, customAddAppointment;

    @Override
    public void start(Stage stage) throws IOException {
        mainVB=FXMLLoader.load(Objects.requireNonNull(Driver.class.getResource("main-menu-view.fxml")));
        addCentreVB=FXMLLoader.load(Objects.requireNonNull(Driver.class.getResource("add-vaccination-centre.fxml")));
        editCentreVB=FXMLLoader.load(Objects.requireNonNull(Driver.class.getResource("edit-vaccination-centre.fxml")));
        centreBoothsVB=FXMLLoader.load(Objects.requireNonNull(Driver.class.getResource("vaccination-centre-booths.fxml")));
        addCentreBoothVB=FXMLLoader.load(Objects.requireNonNull(Driver.class.getResource("add-vaccination-centre-booth.fxml")));
        editCentreBoothVB=FXMLLoader.load(Objects.requireNonNull(Driver.class.getResource("edit-vaccination-centre-booth.fxml")));
        addPatientVB=FXMLLoader.load(Objects.requireNonNull(Driver.class.getResource("add-patient.fxml")));
        editPatientVB=FXMLLoader.load(Objects.requireNonNull(Driver.class.getResource("edit-patient.fxml")));
        smartAddAppointmentVB=FXMLLoader.load(Objects.requireNonNull(Driver.class.getResource("smart-add-appointment.fxml")));
        customAddAppointmentVB=FXMLLoader.load(Objects.requireNonNull(Driver.class.getResource("custom-add-appointment.fxml")));

        mainMenu = new Scene(mainVB, 800, 500);
        addCentre = new Scene(addCentreVB, 400, 300);
        editCentre = new Scene(editCentreVB, 400, 300);
        centreBooths = new Scene(centreBoothsVB, 300, 250);
        addCentreBooth = new Scene(addCentreBoothVB, 250, 250);
        editCentreBooth = new Scene(editCentreBoothVB, 250, 250);
        addPatient = new Scene(addPatientVB, 400, 400);
        editPatient = new Scene(editPatientVB, 400, 400);
        smartAddAppointment = new Scene(smartAddAppointmentVB, 250, 400);
        customAddAppointment = new Scene(customAddAppointmentVB, 250, 600);

        stage.setTitle("Vaccine Management System");
        stage.setScene(mainMenu);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}