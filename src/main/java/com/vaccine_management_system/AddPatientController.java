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

public class AddPatientController {
    @FXML private Button cancelButton;
    @FXML private TextField nameTF;
    @FXML private DatePicker dobDP;
    @FXML private TextField ppsNumberTF;
    @FXML private TextField addressTF;
    @FXML private TextField phoneTF;
    @FXML private CheckBox wheelchairBool;

    @FXML
    protected void AddPatientDisplay() {
        Scene scene = Driver.addPatient;
        Stage stage = new Stage();
        stage.setTitle("Add Patient");
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
    protected void AddPatientToList() {
        String name = nameTF.getText();
        String dob = "Invalid";
        if(dobDP.getValue() != null) dob = dobDP.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String ppsNumber = ppsNumberTF.getText();
        String address = addressTF.getText();
        String phone = phoneTF.getText();
        boolean wheelchair = wheelchairBool.isSelected();
        for(int i = 0; i<MainController.patients.size(); i++) {
            if(MainController.patients.get(i).getPpsNumber().equalsIgnoreCase(ppsNumber)) {
                CloseAddPatientDisplay();
                return;
            }
        }
        MainController.patients.add(new Patient(name, dob, ppsNumber, address, phone, wheelchair));
        CloseAddPatientDisplay();
    }
}
