package com.vaccine_management_system;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddVaccinationCentreController {
    @FXML private Button cancelButton;
    @FXML private TextField nameVC;
    @FXML private TextField addressVC;
    @FXML private TextField eircodeVC;
    @FXML private TextField spacesVC;

    @FXML
    protected void AddVaccinationCentreDisplay() {
        Scene scene = Driver.addCentre;
        Stage stage = new Stage();
        stage.setTitle("Add Vaccination Centre");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    protected void CloseAddVaccinationCentreDisplay() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void AddVaccinationCentreToList() {
        String name = nameVC.getText();
        String address = addressVC.getText();
        String eircode = eircodeVC.getText();
        String spaces = spacesVC.getText();
        for (int i = 0; i < MainController.vcl.size(); i++) {
            if(MainController.vcl.get(i).getEircode().equalsIgnoreCase(eircode)) {          //If the new vaccine centre does not have an original eircode.
                CloseAddVaccinationCentreDisplay();                                         //Close the window.
                return;                                                                     //Return without changing anything.
            }
        }
        MainController.vcl.add(new VaccinationCentre(name, address, eircode, spaces));      //Otherwise, add the new vaccine centre.
        CloseAddVaccinationCentreDisplay();
    }
}
