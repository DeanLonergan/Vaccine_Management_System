package com.vaccine_management_system;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class VaccinationCentreBoothsController implements Initializable{
    public static AddVaccinationCentreBoothController abcController = new AddVaccinationCentreBoothController();
    public static EditVaccinationCentreBoothController ebcController = new EditVaccinationCentreBoothController();
    public static int selectedBoothIndex;
    @FXML private TableView<VaccinationBooth> tableView;
    @FXML private TableColumn<VaccinationBooth, String> idColumn;
    @FXML private TableColumn<VaccinationBooth, Integer> floorColumn;
    @FXML private TableColumn<VaccinationBooth, Boolean> accessColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        floorColumn.setCellValueFactory(new PropertyValueFactory<>("floor"));
        accessColumn.setCellValueFactory(new PropertyValueFactory<>("accessibility"));
        MainController.vcl.get(0).addBooth("A01" , "G", true);
        MainController.vcl.get(1).addBooth("B01" , "1", true);
        MainController.vcl.get(2).addBooth("C01" , "2", false);
        MainController.vcl.get(3).addBooth("D01" , "3", false);
        updateTable();
    }

    @FXML
    protected void VaccinationCentreBoothsDisplay() {
        Scene scene = Driver.centreBooths;
        Stage stage = new Stage();
        stage.setTitle("Vaccination Centre Booths");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void displayAddVaccinationCentreBooth() { abcController.AddVaccinationCentreBoothDisplay(); }

    public void displayEditVaccinationCentreBooth() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            selectedBoothIndex = MainController.vcl.get(MainController.selectedIndex).getBooths().indexOf(tableView.getSelectionModel().getSelectedItem());
            ebcController.EditVaccinationCentreBoothDisplay();
        }
    }

    public void updateTable() {
        tableView.getItems().clear();
        int i=0;
        while (i<MainController.vcl.get(MainController.selectedIndex).getBooths().size()) {
            if(!tableView.getItems().contains(MainController.vcl.get(MainController.selectedIndex).getBooths().get(i))) tableView.getItems().add(MainController.vcl.get(MainController.selectedIndex).getBooths().get(i));
            i++;
        }
        tableView.refresh();
    }

    //Only working with custom data.
    public void removeSelected() {
        String date;
        String time;
        String dose;
        String type;
        String batch;
        String nurseID;
        String pps;
        boolean accessibility = false;
        if (MainController.vcl.get(MainController.selectedIndex).getBooths().get(selectedBoothIndex).getPendingAppointments().size()!=0) {
            int i=0;
            while (i < MainController.vcl.get(MainController.selectedIndex).getBooths().get(selectedBoothIndex).getPendingAppointments().size()) {
                date = MainController.vcl.get(MainController.selectedIndex).getBooths().get(selectedBoothIndex).getPendingAppointments().get(i).getDate();
                time = MainController.vcl.get(MainController.selectedIndex).getBooths().get(selectedBoothIndex).getPendingAppointments().get(i).getTime();
                dose = MainController.vcl.get(MainController.selectedIndex).getBooths().get(selectedBoothIndex).getPendingAppointments().get(i).getDose();
                type = MainController.vcl.get(MainController.selectedIndex).getBooths().get(selectedBoothIndex).getPendingAppointments().get(i).getType();
                batch = MainController.vcl.get(MainController.selectedIndex).getBooths().get(selectedBoothIndex).getPendingAppointments().get(i).getBatch();
                nurseID = MainController.vcl.get(MainController.selectedIndex).getBooths().get(selectedBoothIndex).getPendingAppointments().get(i).getNurseID();
                pps = MainController.vcl.get(MainController.selectedIndex).getBooths().get(selectedBoothIndex).getPendingAppointments().get(i).getPatientPPS();
                for (int y = 0; y < MainController.patients.size(); y++) {
                    if (MainController.vcl.get(MainController.selectedIndex).getBooths().get(selectedBoothIndex).getPendingAppointments().get(i).getPatientPPS().equals(MainController.patients.get(y).getPpsNumber())) {
                        accessibility = MainController.patients.get(y).isWheelchair();
                    }
                }
                int k = 0;
                while (k < MainController.vcl.size()) {
                        for (int g = 0; g < MainController.vcl.get(k).getBooths().size(); g++) {
                            if (!MainController.vcl.get(k).getBooths().get(g).equals(MainController.vcl.get(MainController.selectedIndex).getBooths().get(selectedBoothIndex))) {
                                String location = MainController.vcl.get(k).getName();
                                String booth = MainController.vcl.get(k).getBooths().get(g).getID();
                                if (!accessibility || MainController.vcl.get(k).getBooths().get(g).getAccessibility()) {
                                    if (MainController.vcl.get(k).getBooths().get(g).getPendingAppointments().size() != 0) {
                                        for (int h = 0; h < MainController.vcl.get(k).getBooths().get(g).getPendingAppointments().size(); h++) {
                                            if (MainController.vcl.get(k).getBooths().get(g).getPendingAppointments().get(h).getPatientPPS().equals(pps)) {
                                                if (!(MainController.vcl.get(k).getBooths().get(g).getPendingAppointments().get(h).getDate().equals(date)
                                                        && MainController.vcl.get(k).getBooths().get(g).getPendingAppointments().get(h).getTime().equals(time))) {
                                                    MainController.vcl.get(k).getBooths().get(g).getPendingAppointments().add(new Appointment(location, booth, date, time, dose, type, batch, nurseID, pps, false));
                                                    break;
                                                }
                                            }
                                        }
                                    } else {
                                        MainController.vcl.get(k).getBooths().get(g).getPendingAppointments().add(new Appointment(location, booth, date, time, dose, type, batch, nurseID, pps, false));
                                        break;
                                    }
                                }
                            }
                        }
                    k++;
                }
                i++;
            }
        }
        MainController.vcl.get(MainController.selectedIndex).getBooths().remove(tableView.getSelectionModel().getSelectedItem());
        tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());
    }
}
