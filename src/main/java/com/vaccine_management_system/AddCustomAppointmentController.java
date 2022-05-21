package com.vaccine_management_system;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddCustomAppointmentController implements Initializable {

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
    @FXML
    private TableView<VaccinationCentre> vaccinationCentreTableView;
    @FXML
    private TableColumn<VaccinationCentre, String> nameColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeCB.getItems().addAll("9:00", "9:15", "9:30", "9:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00");
        doseCB.getItems().addAll("1", "2", "3", "4", "5", "6");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        updateTableView();
    }

    @FXML
    protected void CustomAddAppointmentDisplay() {
        Scene scene = Driver.customAddAppointment;
        Stage stage = new Stage();
        stage.setTitle("Book an appointment");
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

    @FXML
    protected void updateTableView() {
        vaccinationCentreTableView.getItems().clear();
        int i=0;
        while (i<MainController.vcl.size()) {
            if(!vaccinationCentreTableView.getItems().contains(MainController.vcl.get(i))) vaccinationCentreTableView.getItems().add(MainController.vcl.get(i));
            i++;
        }
        vaccinationCentreTableView.refresh();
    }

    /**
     * Method to create an appointment for a patient in the first available Vaccine Centre that has a booth available on the desired date and time.
     */
    @FXML
    protected void customBookAppointment() {
        if (vaccinationCentreTableView.getSelectionModel().getSelectedItem() != null) {
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
            String pps = MainController.selectedPatientPPS;
            String location = vaccinationCentreTableView.getSelectionModel().getSelectedItem().getName();
            boolean accessibility = MainController.selectedPatientAccessibility;
            int i = 0;
            while (i < vaccinationCentreTableView.getSelectionModel().getSelectedItem().getBooths().size()) {
                if (!accessibility || vaccinationCentreTableView.getSelectionModel().getSelectedItem().getBooths().get(i).getAccessibility()) {
                    String booth = vaccinationCentreTableView.getSelectionModel().getSelectedItem().getBooths().get(i).getID();
                    if (vaccinationCentreTableView.getSelectionModel().getSelectedItem().getBooths().get(i).getPendingAppointments().size() != 0) {
                        for (int j = 0; j < vaccinationCentreTableView.getSelectionModel().getSelectedItem().getBooths().get(i).getPendingAppointments().size(); j++) {
                            if (!(vaccinationCentreTableView.getSelectionModel().getSelectedItem().getBooths().get(i).getPendingAppointments().get(j).getDate().equals(date)
                                    && vaccinationCentreTableView.getSelectionModel().getSelectedItem().getBooths().get(i).getPendingAppointments().get(j).getTime().equals(time))) {
                                vaccinationCentreTableView.getSelectionModel().getSelectedItem().getBooths().get(i).getPendingAppointments().add(new Appointment(location, booth, date, time, dose, type, batch, nurseID, pps, false));
                                CloseAddPatientDisplay();
                                return;
                            }
                        }
                    } else {
                        vaccinationCentreTableView.getSelectionModel().getSelectedItem().getBooths().get(i).getPendingAppointments().add(new Appointment(location, booth, date, time, dose, type, batch, nurseID, pps, false));
                        CloseAddPatientDisplay();
                        return;
                    }
                }
                i++;
            }
        }
    }
}