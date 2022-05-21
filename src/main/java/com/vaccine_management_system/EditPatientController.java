package com.vaccine_management_system;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;

public class EditPatientController {
    @FXML private Button cancelButton;
    @FXML private TextField nameTF;
    @FXML private DatePicker dobDP;
    @FXML private TextField ppsNumberTF;
    @FXML private TextField addressTF;
    @FXML private TextField phoneTF;
    @FXML private CheckBox wheelchairBool;

    @FXML
    protected void EditPatientDisplay() {
        Scene scene = Driver.editPatient;
        Stage stage = new Stage();
        stage.setTitle("Edit Patient");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    protected void CloseEditPatientDisplay() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void EditPatientInList() {
        String dob = "Invalid";
        if(dobDP.getValue() != null) dob = dobDP.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String name = nameTF.getText();
        String ppsNumber = ppsNumberTF.getText();
        String address = addressTF.getText();
        String phone = phoneTF.getText();
        boolean wheelchair = wheelchairBool.isSelected();
        if (MainController.patients.get(MainController.selectedIndex).getPpsNumber().equalsIgnoreCase(ppsNumber)) {
            MainController.patients.set(MainController.selectedIndex, new Patient(name, dob, ppsNumber, address, phone, wheelchair));
            CloseEditPatientDisplay();
            return;
        }
        for (int i = 0; i<MainController.patients.size(); i++) {
            if (MainController.patients.get(i).getPpsNumber().equalsIgnoreCase(ppsNumber)) {
                CloseEditPatientDisplay();
                return;
            }
        }
        MainController.patients.set(MainController.selectedIndex, new Patient(name, dob, ppsNumber, address, phone, wheelchair));
        CloseEditPatientDisplay();
    }
}
