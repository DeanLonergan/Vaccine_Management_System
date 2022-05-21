package com.vaccine_management_system;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class EditVaccinationCentreBoothController implements Initializable {
    @FXML private Button cancelButton;
    @FXML private TextField boothID;
    @FXML private ChoiceBox<String> boothFloor;
    @FXML private CheckBox boothAccessibility;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boothFloor.getItems().addAll("G","1","2","3","4","5","6","7","8","8","9","10");
    }

    @FXML
    protected void EditVaccinationCentreBoothDisplay() {
        Scene scene = Driver.editCentreBooth;
        Stage stage = new Stage();
        stage.setTitle("Edit Booth");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    protected void CloseEditVaccinationCentreBoothDisplay() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void EditBoothInVaccinationCentre() {
        String ID = boothID.getText();
        String floor = "Invalid";
        if(boothFloor.getSelectionModel().getSelectedItem() != null) floor = boothFloor.getSelectionModel().getSelectedItem();
        Boolean accessibility = boothAccessibility.isSelected();
        if (MainController.vcl.get(MainController.selectedIndex).getBooths().get(VaccinationCentreBoothsController.selectedBoothIndex).getID().equalsIgnoreCase(ID)) {
            MainController.vcl.get(MainController.selectedIndex).getBooths().set(VaccinationCentreBoothsController.selectedBoothIndex, new VaccinationBooth(ID, floor, accessibility));
            CloseEditVaccinationCentreBoothDisplay();
            return;
        }
        for (int i=0; i<MainController.vcl.get(MainController.selectedIndex).getBooths().size(); i++) {
            if (MainController.vcl.get(MainController.selectedIndex).getBooths().get(i).getID().equals(ID)) {
                CloseEditVaccinationCentreBoothDisplay();
                return;
            }
        }
        MainController.vcl.get(MainController.selectedIndex).getBooths().set(VaccinationCentreBoothsController.selectedBoothIndex, new VaccinationBooth(ID, floor, accessibility));
        CloseEditVaccinationCentreBoothDisplay();
    }
}