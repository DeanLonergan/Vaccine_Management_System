package com.vaccine_management_system;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditVaccinationCentreController {
    @FXML private Button cancelButton;
    @FXML private TextField nameEVC;
    @FXML private TextField addressEVC;
    @FXML private TextField eircodeEVC;
    @FXML private TextField spacesEVC;

    @FXML
    protected void EditVaccinationCentreDisplay() {
        Scene scene = Driver.editCentre;
        Stage stage = new Stage();
        stage.setTitle("Edit Vaccination Centre");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    protected void CloseEditVaccinationCentreDisplay() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void EditVaccinationCentre() {
        String name = nameEVC.getText();
        String address = addressEVC.getText();
        String eircode = eircodeEVC.getText();
        String spaces = spacesEVC.getText();
        if (MainController.vcl.get(MainController.selectedIndex).getEircode().equalsIgnoreCase(eircode)) {                  //If the edited vaccine centre still has the same eircode.
            MainController.vcl.set(MainController.selectedIndex, new VaccinationCentre(name, address, eircode, spaces));    //Update the vaccine centre with the new information.
            CloseEditVaccinationCentreDisplay();
            return;
        }
        for (int i = 0; i < MainController.vcl.size(); i++) {
            if (MainController.vcl.get(i).getEircode().equalsIgnoreCase(eircode)) {                                         //If the edited vaccine centre attempts to use an eircode already in use (other than its own).
                CloseEditVaccinationCentreDisplay();                                                                                              //Close the window.
                return;                                                                                                     //Return without changing anything.
            }
        }
        MainController.vcl.set(MainController.selectedIndex, new VaccinationCentre(name, address, eircode, spaces));        //Otherwise, update the vaccine centre with the new information (including eircode).
        CloseEditVaccinationCentreDisplay();
    }
}