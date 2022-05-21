package com.vaccine_management_system;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddSmartAppointmentController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private DatePicker dateDP;
    @FXML
    private ChoiceBox<String> timeCB;
    @FXML
    private ChoiceBox<String> doseCB;
    @FXML
    private TextField typeTF;
    @FXML
    private TextField batchTF;
    @FXML
    private TextField nurseIDTF;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeCB.getItems().addAll("9:00", "9:15", "9:30", "9:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00");
        doseCB.getItems().addAll("1", "2", "3", "4", "5", "6");
        Appointment ap3 = new Appointment("WIT Arena", "B01", "11/12/2021", "11:15", "1", "Moderna", "HJD98I", "09153", "3417290JM", false);
        Appointment ap4 = new Appointment("City Hall Cork", "A01", "21/12/2021", "12:00", "1", "AstraZenica", "JP2D8Q", "53621", "1892754RM", false);
        MainController.vcl.get(1).getBooths().get(0).getPendingAppointments().add(ap3);
        MainController.vcl.get(0).getBooths().get(0).getPendingAppointments().add(ap4);
    }

    @FXML
    protected void SmartAddAppointmentDisplay() {
        Scene scene = Driver.smartAddAppointment;
        Stage stage = new Stage();
        stage.setTitle("Smart Book an appointment");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    protected void CloseAddPatientDisplay() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Method to create an appointment for a patient in the first available Vaccine Centre that has a booth available on the desired date and time.
     */
    @FXML
    protected void smartBookAppointment() {
        String date;
        if (dateDP.getValue() != null) date = dateDP.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        else return;
        String time;
        if (timeCB.getValue() != null) time = timeCB.getValue();
        else return;
        String dose;
        if (doseCB.getValue() != null) dose = doseCB.getValue();
        else return;
        String type = "Invalid";
        if (!typeTF.getText().equals("")) type = typeTF.getText();
        String batch = "Invalid";
        if (!batchTF.getText().equals("")) batch = batchTF.getText();
        String nurseID = "Invalid";
        if (!nurseIDTF.getText().equals("")) nurseID = nurseIDTF.getText();
        String location;
        String booth;
        String pps = MainController.selectedPatientPPS;
        boolean accessibility = MainController.selectedPatientAccessibility;
        int i = 0;
        while (i < MainController.vcl.size()) {                                                                                                     //Loop through each Vaccine Centre.
            for (int j = 0; j < MainController.vcl.get(i).getBooths().size(); j++) {                                                                //Loop through their respective Booths.
                location = MainController.vcl.get(i).getName();
                booth = MainController.vcl.get(i).getBooths().get(j).getID();
                if (!accessibility || MainController.vcl.get(i).getBooths().get(j).getAccessibility()) {                                            //If the patient doesn't need a wheelchair or the patient does need wheelchair and the Booth is accessible.
                    if (MainController.vcl.get(i).getBooths().get(j).getPendingAppointments().size() != 0) {                                        //If the Booth has pending appointments.
                        for (int k = 0; k < MainController.vcl.get(i).getBooths().get(j).getPendingAppointments().size(); k++) {                    //Loop through each appointment.
                            if (!(MainController.vcl.get(i).getBooths().get(j).getPendingAppointments().get(k).getDate().equals(date)               //If the Booth is available on the desired date and time.
                                    && MainController.vcl.get(i).getBooths().get(j).getPendingAppointments().get(k).getTime().equals(time))) {
                                MainController.vcl.get(i).getBooths().get(j).getPendingAppointments().add(new Appointment(location, booth, date, time, dose, type, batch, nurseID, pps, false));    //Book the appointment.
                                CloseAddPatientDisplay();
                                return;
                            }
                        }
                    }  else {                                                                                                                        //If the Booth does not have pending appointments.
                            MainController.vcl.get(i).getBooths().get(j).getPendingAppointments().add(new Appointment(location, booth, date, time, dose, type, batch, nurseID, pps, false));         //Book the appointment.
                            CloseAddPatientDisplay();
                            return;
                    }
                }
            }
            i++;
        }
    }
}