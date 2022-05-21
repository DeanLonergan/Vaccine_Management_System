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

public class AddVaccinationCentreBoothController implements Initializable {
    @FXML private Button cancelButton;
    @FXML private TextField boothID;
    @FXML private ChoiceBox<String> boothFloor;
    @FXML private CheckBox boothAccessibility;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boothFloor.getItems().addAll("G","1","2","3","4","5","6","7","8","8","9","10");
    }

    @FXML
    protected void AddVaccinationCentreBoothDisplay() {
        Scene scene = Driver.addCentreBooth;
        Stage stage = new Stage();
        stage.setTitle("Add Booth");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    protected void CloseAddVaccinationCentreBoothDisplay() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void AddBoothToVaccinationCentre() {
        String ID = boothID.getText();
        String floor = "Invalid";
        if(boothFloor.getSelectionModel().getSelectedItem() != null) floor = boothFloor.getSelectionModel().getSelectedItem();
        Boolean accessibility = boothAccessibility.isSelected();
        for (int i=0; i<MainController.vcl.get(MainController.selectedIndex).getBooths().size(); i++) {
            if(MainController.vcl.get(MainController.selectedIndex).getBooths().get(i).getID().equalsIgnoreCase(ID)) {
                CloseAddVaccinationCentreBoothDisplay();
                return;
            }
        }
        MainController.vcl.get(MainController.selectedIndex).getBooths().add(new VaccinationBooth(ID, floor, accessibility));
        CloseAddVaccinationCentreBoothDisplay();
    }
}
