package com.vaccine_management_system;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public static AddVaccinationCentreController avcController = new AddVaccinationCentreController();
    public static EditVaccinationCentreController edtController = new EditVaccinationCentreController();
    public static VaccinationCentreBoothsController vcbController = new VaccinationCentreBoothsController();
    public static AddPatientController apController = new AddPatientController();
    public static EditPatientController epController = new EditPatientController();
    public static AddSmartAppointmentController asaController = new AddSmartAppointmentController();
    public static AddCustomAppointmentController acaController = new AddCustomAppointmentController();
    public static CustomList<VaccinationCentre> vcl = new CustomList<>();
    public static CustomList<Patient> patients = new CustomList<>();
    public static CustomList<Appointment> completeAppointments = new CustomList<>();
    public static int selectedIndex;
    public static String selectedPatientPPS;
    public static boolean selectedPatientAccessibility;

    @FXML
    private TableView<VaccinationCentre> vaccinationCentreTableView;
    @FXML
    private TableColumn<VaccinationCentre, String> nameColumn;
    @FXML
    private TableColumn<VaccinationCentre, String> addressColumn;
    @FXML
    private TableColumn<VaccinationCentre, String> eircodeColumn;
    @FXML
    private TableColumn<VaccinationCentre, Integer> parkingColumn;

    @FXML
    private TableView<Patient> patientTableView;
    @FXML
    private TableColumn<Patient, String> patientNameColumn;
    @FXML
    private TableColumn<Patient, String> patientDOBColumn;
    @FXML
    private TableColumn<Patient, String> patientPPSColumn;
    @FXML
    private TableColumn<Patient, String> patientAddressColumn;
    @FXML
    private TableColumn<Patient, String> patientPhoneColumn;
    @FXML
    private TableColumn<Patient, Boolean> patientWheelchairColumn;

    @FXML
    private TableView<Appointment> appointmentTableView;
    @FXML
    private TableColumn<Appointment, String> dateColumn;
    @FXML
    private TableColumn<Appointment, String> timeColumn;
    @FXML
    private TableColumn<Appointment, String> doseColumn;
    @FXML
    private TableColumn<Appointment, String> typeColumn;
    @FXML
    private TableColumn<Appointment, String> batchColumn;
    @FXML
    private TableColumn<Appointment, String> nurseIDColumn;
    @FXML
    private TableColumn<Appointment, String> appointmentPatientPPSColumn;
    @FXML
    private TableColumn<Appointment, Boolean> completeColumn;
    @FXML
    private Label patient;
    @FXML
    private Label location;
    @FXML
    private Label time;

    @FXML
    private TableView<Patient> patientSearchTableView;
    @FXML
    private TableColumn<Patient, String> patientNameSearchColumn;
    @FXML
    private TableColumn<Patient, String> patientPPSSearchColumn;
    @FXML
    private TableColumn<Patient, String> patientAddressSearchColumn;
    @FXML
    private TableColumn<Patient, String> patientPhoneSearchColumn;
    @FXML
    private TableColumn<Patient, Boolean> patientWheelchairSearchColumn;
    @FXML
    private TextField searchByNameTF;
    @FXML
    private TextField searchByPPSTF;
    @FXML
    private ChoiceBox<String> searchByDoseCB;

    @FXML
    private TableView<Appointment> appointmentSearchTableView;
    @FXML
    private TableColumn<Appointment, String> dateSearchColumn;
    @FXML
    private TableColumn<Appointment, String> timeSearchColumn;
    @FXML
    private TableColumn<Appointment, String> doseSearchColumn;
    @FXML
    private TableColumn<Appointment, String> typeSearchColumn;
    @FXML
    private TableColumn<Appointment, String> batchSearchColumn;
    @FXML
    private TableColumn<Appointment, String> appointmentPatientPPSSearchColumn;
    @FXML
    private TableColumn<Appointment, Boolean> completeSearchColumn;
    @FXML
    private DatePicker searchByDateDP;
    @FXML
    private TextField searchByTypeTF;
    @FXML
    private TextField searchByCentreNameTF;

    @FXML
    private Label saveLoadClear;

    VaccinationCentre vc1 = new VaccinationCentre("Clonmel Park Hotel", "Poppyfields Retail Park, Cahir Road, Clonmel, Tipperary", "E91 XN07", "150");
    VaccinationCentre vc2 = new VaccinationCentre("Clonakilty GAA Club", "Aghamilla, Clonakilty, Cork", "P85 WN84", "80");
    VaccinationCentre vc3 = new VaccinationCentre("WIT Arena", "WIT Sports Campus, Carriganore, Waterford", "X91 XD96", "250");
    VaccinationCentre vc4 = new VaccinationCentre("City Hall Cork", "City Hall, Anglesea Street, Cork City, Cork", "T12 T997", "50");

    Patient p1 = new Patient("John Smith", "27/09/1996", "8372618JS", "210 Ashville Road, Ballygreen, Waterford", "0828271678", true);
    Patient p2 = new Patient("Fran Walsh", "12/05/1989", "6371298FW", "15 Barberry, Flint Road, Cork", "0834781976", false);
    Patient p3 = new Patient("James McNulty", "24/02/1980", "3417290JM", "1020 Druid Hill Ave, Dublin", "0878662514", false);
    Patient p4 = new Patient("Rebecca Murray", "18/11/1999", "1892754RM", "14 Hazel hatch, Kildare", "0853782916", true);

    Appointment ap1 = new Appointment("Clonmel Park Hotel", "D01", "09/10/2021", "9:00", "2", "Pfizer", "F47TYS", "23874", "8372618JS", true);
    Appointment ap2 = new Appointment("Clonakilty GAA Club", "C01", "21/11/2021", "10:15", "1", "Pfizer", "HJS672", "94724", "6371298FW", true);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vcl.add(vc1);
        vcl.add(vc2);
        vcl.add(vc3);
        vcl.add(vc4);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        eircodeColumn.setCellValueFactory(new PropertyValueFactory<>("eircode"));
        parkingColumn.setCellValueFactory(new PropertyValueFactory<>("parkingSpaces"));

        patients.add(p1);
        patients.add(p2);
        patients.add(p3);
        patients.add(p4);
        patientNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        patientDOBColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));
        patientPPSColumn.setCellValueFactory(new PropertyValueFactory<>("ppsNumber"));
        patientAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        patientPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        patientWheelchairColumn.setCellValueFactory(new PropertyValueFactory<>("wheelchair"));

        completeAppointments.add(ap1);
        completeAppointments.add(ap2);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        doseColumn.setCellValueFactory(new PropertyValueFactory<>("dose"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        batchColumn.setCellValueFactory(new PropertyValueFactory<>("batch"));
        nurseIDColumn.setCellValueFactory(new PropertyValueFactory<>("nurseID"));
        appointmentPatientPPSColumn.setCellValueFactory(new PropertyValueFactory<>("patientPPS"));
        completeColumn.setCellValueFactory(new PropertyValueFactory<>("complete"));
        patient.setText("Patient:");
        location.setText("Location:");
        time.setText("Date:");

        patientNameSearchColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        patientPPSSearchColumn.setCellValueFactory(new PropertyValueFactory<>("ppsNumber"));
        patientAddressSearchColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        patientPhoneSearchColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        patientWheelchairSearchColumn.setCellValueFactory(new PropertyValueFactory<>("wheelchair"));
        searchByDoseCB.getItems().addAll("0", "1", "2", "3", "4", "5", "6");

        dateSearchColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeSearchColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        doseSearchColumn.setCellValueFactory(new PropertyValueFactory<>("dose"));
        typeSearchColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        batchSearchColumn.setCellValueFactory(new PropertyValueFactory<>("batch"));
        appointmentPatientPPSSearchColumn.setCellValueFactory(new PropertyValueFactory<>("patientPPS"));
        completeSearchColumn.setCellValueFactory(new PropertyValueFactory<>("complete"));

        updateVaccineCentreTable();
        updateAppointmentTableComplete();
        updatePatientTable();
    }

    public void displayAddVaccinationCentre() {
        avcController.AddVaccinationCentreDisplay();
    }

    public void displayEditVaccinationCentre() {
        if (vaccinationCentreTableView.getSelectionModel().getSelectedItem() != null) {
            selectedIndex = MainController.vcl.indexOf(vaccinationCentreTableView.getSelectionModel().getSelectedItem());
            edtController.EditVaccinationCentreDisplay();
        }
    }

    public void removeSelectedVaccineCentre() {
        vcl.remove(vaccinationCentreTableView.getSelectionModel().getSelectedItem());
        vaccinationCentreTableView.getItems().removeAll(vaccinationCentreTableView.getSelectionModel().getSelectedItem());
    }

    public void displayVaccinationCentreBooths() {
        if (vaccinationCentreTableView.getSelectionModel().getSelectedItem() != null) {
            selectedIndex = MainController.vcl.indexOf(vaccinationCentreTableView.getSelectionModel().getSelectedItem());
            vcbController.VaccinationCentreBoothsDisplay();
        }
    }

    public void displayAddPatient() {
        apController.AddPatientDisplay();
    }

    public void displayEditPatient() {
        if (patientTableView.getSelectionModel().getSelectedItem() != null) {
            selectedIndex = MainController.patients.indexOf(patientTableView.getSelectionModel().getSelectedItem());
            epController.EditPatientDisplay();
        }
    }

    public void removeSelectedPatient() {
        patients.remove(patientTableView.getSelectionModel().getSelectedItem());
        patientTableView.getItems().removeAll(patientTableView.getSelectionModel().getSelectedItem());
    }

    public void displaySmartAddAppointment() {
        if (patientTableView.getSelectionModel().getSelectedItem() != null) {
            selectedIndex = MainController.patients.indexOf(patientTableView.getSelectionModel().getSelectedItem());
            selectedPatientPPS = patientTableView.getSelectionModel().getSelectedItem().getPpsNumber();
            selectedPatientAccessibility = patientTableView.getSelectionModel().getSelectedItem().isWheelchair();
            asaController.SmartAddAppointmentDisplay();
        }
    }

    public void displayCustomAddAppointment() {
        if (patientTableView.getSelectionModel().getSelectedItem() != null) {
            selectedIndex = MainController.patients.indexOf(patientTableView.getSelectionModel().getSelectedItem());
            selectedPatientPPS = patientTableView.getSelectionModel().getSelectedItem().getPpsNumber();
            selectedPatientAccessibility = patientTableView.getSelectionModel().getSelectedItem().isWheelchair();
            acaController.CustomAddAppointmentDisplay();
        }
    }

    public void removeSelectedAppointment() {
        if (appointmentTableView.getSelectionModel().getSelectedItem() != null) {                                       //If an appointment has been selected.
            if (appointmentTableView.getSelectionModel().getSelectedItem().isComplete()) {                              //If the appointment has already been complete.
                completeAppointments.remove(appointmentTableView.getSelectionModel().getSelectedItem());                //remove the appointment from the list of complete appointments.
            } else {
                int i = 0;
                while (i < vcl.size()) {                                                                                //If the appointment is pending, loop through all Vaccine Centres and their respective booths.
                    for (int j = 0; j < vcl.get(i).getBooths().size(); j++) {
                        for (int k = 0; k < vcl.get(i).getBooths().get(j).getPendingAppointments().size(); k++) {
                            if (appointmentTableView.getSelectionModel().getSelectedItem().equals(vcl.get(i).getBooths().get(j).getPendingAppointments().get(k))) {     //Find the correct appointment.
                                vcl.get(i).getBooths().get(j).getPendingAppointments().remove(appointmentTableView.getSelectionModel().getSelectedItem());              //Remove it from the correct booths list of pending appointments.
                            }
                        }
                    }
                    i++;
                }
            }
            appointmentTableView.getItems().removeAll(appointmentTableView.getSelectionModel().getSelectedItem());      //Remove the selected appointment from the TableView.
        }
    }

    public void completeAppointment() {
        if (appointmentTableView.getSelectionModel().getSelectedItem() != null && !appointmentTableView.getSelectionModel().getSelectedItem().isComplete()) {
            Appointment appointment = appointmentTableView.getSelectionModel().getSelectedItem();
            removeSelectedAppointment();
            appointment.setComplete(true);
            MainController.completeAppointments.add(appointment);
        }
    }

    public void appointmentDetails() {
        if (appointmentTableView.getSelectionModel().getSelectedItem() != null) {
            int i = 0;
            while (i < patients.size()) {
                if (patients.get(i).getPpsNumber().equals(appointmentTableView.getSelectionModel().getSelectedItem().getPatientPPS())) {
                    patient.setText("Patient: " + patients.get(i).getName());
                    location.setText("Location: " + appointmentTableView.getSelectionModel().getSelectedItem().getLocation() + " in Booth: " + appointmentTableView.getSelectionModel().getSelectedItem().getBooth());
                    time.setText("Date: " + appointmentTableView.getSelectionModel().getSelectedItem().getDate() + " at " + appointmentTableView.getSelectionModel().getSelectedItem().getTime());
                }
                i++;
            }
        }
    }

    public void updateVaccineCentreTable() {
        vaccinationCentreTableView.getItems().clear();
        int i = 0;
        while (i < vcl.size()) {
            if (!vaccinationCentreTableView.getItems().contains(vcl.get(i)))
                vaccinationCentreTableView.getItems().add(vcl.get(i));
            i++;
        }
        vaccinationCentreTableView.refresh();
    }

    public void updateAppointmentTableAll() {
        appointmentTableView.getItems().clear();
        int i = 0;
        while (i < completeAppointments.size()) {
            if (!appointmentTableView.getItems().contains(completeAppointments.get(i)))
                appointmentTableView.getItems().add(completeAppointments.get(i));
            i++;
        }
        int j = 0;
        while (j < vcl.size()) {
            for (int k = 0; k < vcl.get(j).getBooths().size(); k++) {
                for (int g = 0; g < vcl.get(j).getBooths().get(k).getPendingAppointments().size(); g++) {
                    if (!appointmentTableView.getItems().contains(vcl.get(j).getBooths().get(k).getPendingAppointments().get(g))) {
                        appointmentTableView.getItems().add(vcl.get(j).getBooths().get(k).getPendingAppointments().get(g));
                    }
                }
            }
            j++;
        }
        appointmentTableView.refresh();
    }

    public void updateAppointmentTableComplete() {
        appointmentTableView.getItems().clear();
        int i = 0;
        while (i < completeAppointments.size()) {
            if (!appointmentTableView.getItems().contains(completeAppointments.get(i)))
                appointmentTableView.getItems().add(completeAppointments.get(i));
            i++;
        }
        appointmentTableView.refresh();
    }

    public void updateAppointmentTablePending() {
        appointmentTableView.getItems().clear();
        int i = 0;
        while (i < vcl.size()) {
            for (int k = 0; k < vcl.get(i).getBooths().size(); k++) {
                for (int g = 0; g < vcl.get(i).getBooths().get(k).getPendingAppointments().size(); g++) {
                    if (!appointmentTableView.getItems().contains(vcl.get(i).getBooths().get(k).getPendingAppointments().get(g))) {
                        appointmentTableView.getItems().add(vcl.get(i).getBooths().get(k).getPendingAppointments().get(g));
                    }
                }
            }
            i++;
        }
        appointmentTableView.refresh();
    }

    public void updatePatientTable() {
        patientTableView.getItems().clear();
        int i = 0;
        while (i < patients.size()) {
            if (!patientTableView.getItems().contains(patients.get(i)))
                patientTableView.getItems().add(patients.get(i));
            i++;
        }
        patientTableView.refresh();
    }

    public void searchPatients() {
        patientSearchTableView.getItems().clear();
        int i = 0;
        while (i < patients.size()) {                                                                                                               //Loop through all Patients.
            if (!searchByNameTF.getText().equals("")) {                                                                                             //If a name has been entered.
                if (patients.get(i).getName().contains(searchByNameTF.getText())) {                                                                 //If there is a Patient with that name (first or second).
                    if (!patientSearchTableView.getItems().contains(patients.get(i))) patientSearchTableView.getItems().add(patients.get(i));       //Display that Patient once on the table.
                }
            }
            if (!searchByPPSTF.getText().equals("")) {                                                                                              //If a PPS number has been entered.
                if (patients.get(i).getPpsNumber().equals(searchByPPSTF.getText())) {                                                               //If there is Patient with that PPS number.
                    if (!patientSearchTableView.getItems().contains(patients.get(i))) patientSearchTableView.getItems().add(patients.get(i));       //Display that Patient once on the table.
                }
            }
            if (searchByDoseCB.getSelectionModel().getSelectedItem() != null) {                                                                     //If a dose number has been selected.
                int j=0;
                while (j < completeAppointments.size()) {                                                                                           //Loop through all Completed Appointments.
                    if (completeAppointments.get(j).getDose().equals(searchByDoseCB.getSelectionModel().getSelectedItem())) {                       //If there is an Appointment with that dose number.
                        if (patients.get(i).getPpsNumber().equals(completeAppointments.get(j).getPatientPPS())
                                && !patientSearchTableView.getItems().contains(patients.get(i))) {
                            patientSearchTableView.getItems().add(patients.get(i));                                                                 //Display the Patient that relates to that Appointment once on the table.
                        }
                    }
                    j++;
                }
                /* This is unnecessary as it checks pending appointments (working).
                int k=0;
                while (k < vcl.size()) {
                    for (int p=0; p < vcl.get(k).getBooths().size(); p++) {
                        for (int q=0; q < vcl.get(k).getBooths().get(p).getPendingAppointments().size(); q++) {
                            if (vcl.get(k).getBooths().get(p).getPendingAppointments().get(q).getDose().equals(searchByDoseCB.getSelectionModel().getSelectedItem())) {
                                if (patients.get(i).getPpsNumber().equals(vcl.get(k).getBooths().get(p).getPendingAppointments().get(q).getPatientPPS())
                                        && !patientSearchTableView.getItems().contains(patients.get(i))) {
                                    patientSearchTableView.getItems().add(patients.get(i));
                                }
                            }
                        }
                    }
                    k++;
                }
                */
            }
            i++;
        }
    }

    public void searchAppointments() {
        appointmentSearchTableView.getItems().clear();
        int i = 0;
        while (i < completeAppointments.size()) {                                                                                                   //Loop through all complete Appointments.
            if (searchByDateDP.getValue() != null) {                                                                                                //If a date has been entered.
                if (completeAppointments.get(i).getDate().equals(searchByDateDP.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))) {    //If there is a Complete Appointment with that date.
                    if (!appointmentSearchTableView.getItems().contains(completeAppointments.get(i))) {
                        appointmentSearchTableView.getItems().add(completeAppointments.get(i));                                                     //Display that Appointment once in the table.
                    }
                }
            }
            if (!searchByTypeTF.getText().equals("")) {                                                                                             //If a type has been entered.
                if (completeAppointments.get(i).getType().equals(searchByTypeTF.getText())) {                                                       //If there is a Complete Appointment with that type.
                    if (!appointmentSearchTableView.getItems().contains(completeAppointments.get(i))) {
                        appointmentSearchTableView.getItems().add(completeAppointments.get(i));                                                     //Display that Appointment once in the table.
                    }
                }
            }
            if (!searchByCentreNameTF.getText().equals("")) {                                                                                       //If a Vaccine Centre name has been entered.
                int n=0;
                while (n < vcl.size()) {                                                                                                            //Loop through each Vaccine Centre.
                    if (vcl.get(n).getName().contains(searchByCentreNameTF.getText())) {                                                            //If there is a Vaccine Centre with that name.
                        if (completeAppointments.get(i).getLocation().equals(vcl.get(n).getName())) {                                               //If they have an associated complete Appointment.
                            if (!appointmentSearchTableView.getItems().contains(completeAppointments.get(i))) {
                                appointmentSearchTableView.getItems().add(completeAppointments.get(i));                                             //Display that Appointment once in the table.
                            }
                        }
                    }
                    n++;
                }
            }
            /* Unused code to search for Complete Appointments by Patients name (working).
            if (!searchByPatientNameTF.getText().equals("")) {                                                                                      //If a Patients name has been entered.
                int n=0;
                while (n < patients.size()) {                                                                                                       //Loop through all Patients.
                    if (patients.get(n).getName().contains(searchByPatientNameTF.getText())) {                                                      //If there is a Patient with that name (first or second).
                        if (completeAppointments.get(i).getPatientPPS().equals(patients.get(n).getPpsNumber())) {                                   //If they have an associated completed Appointment.
                            if (!appointmentSearchTableView.getItems().contains(completeAppointments.get(i))) {
                                appointmentSearchTableView.getItems().add(completeAppointments.get(i));                                             //Display that Appointment once in the table.
                            }
                        }
                    }
                    n++;
                }
            */
            i++;
        }
        int j = 0;
        while (j < vcl.size()) {                                                                                                                                                            //Loop through each Vaccine Centre.
            for (int k=0; k < vcl.get(j).getBooths().size(); k++) {                                                                                                                         //Loop through that Vaccine Centres Booths.
                for (int p=0; p < vcl.get(j).getBooths().get(k).getPendingAppointments().size(); p++) {                                                                                     //Loop through each Booths' Pending Appointments.
                    if (searchByDateDP.getValue() != null) {                                                                                                                                //If a date has been entered.
                        if (vcl.get(j).getBooths().get(k).getPendingAppointments().get(p).getDate().equals(searchByDateDP.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))) {  //If there is a Pending Appointment with that date.
                            if (!appointmentSearchTableView.getItems().contains(vcl.get(j).getBooths().get(k).getPendingAppointments().get(p))) {
                                appointmentSearchTableView.getItems().add(vcl.get(j).getBooths().get(k).getPendingAppointments().get(p));                                                   //Display that Appointment once in the table.
                            }
                        }
                    }
                    if (!searchByTypeTF.getText().equals("")) {                                                                                                                             //If a type has been entered.
                        if (vcl.get(j).getBooths().get(k).getPendingAppointments().get(p).getType().equals(searchByTypeTF.getText())) {                                                     //If there is a Pending Appointment with that type.
                            if (!appointmentSearchTableView.getItems().contains(vcl.get(j).getBooths().get(k).getPendingAppointments().get(p))) {
                                appointmentSearchTableView.getItems().add(vcl.get(j).getBooths().get(k).getPendingAppointments().get(p));                                                   //Display that Appointment once in the table.
                            }
                        }
                    }
                    if (!searchByCentreNameTF.getText().equals("")) {                                                                                                                       //If a Vaccine Centre name has been entered.
                        int m=0;
                        while (m < vcl.size()) {                                                                                                                                            //Loop through each Vaccine Centre.
                            if (vcl.get(m).getName().contains(searchByCentreNameTF.getText())) {                                                                                            //If there is a Vaccine Centre with that name.
                                if (vcl.get(j).getBooths().get(k).getPendingAppointments().get(p).getLocation().equals(vcl.get(m).getName())) {                                             //If they have an associated pending Appointment.
                                    if (!appointmentSearchTableView.getItems().contains(vcl.get(j).getBooths().get(k).getPendingAppointments().get(p))) {
                                        appointmentSearchTableView.getItems().add(vcl.get(j).getBooths().get(k).getPendingAppointments().get(p));                                           //Display that Appointment once in the table.
                                    }
                                }
                            }
                            m++;
                        }
                    }
                    /* Unused code to search for Pending Appointments by Patients name (working).
                    if (!searchByPatientNameTF.getText().equals("")) {                                                                                                                      //If a Patients name has been entered.
                        int m=0;
                        while (m < patients.size()) {                                                                                                                                       //Loop through all Patients.
                            if (patients.get(m).getName().contains(searchByPatientNameTF.getText())) {                                                                                      //If there is a Patient with that name (first or second).
                                if (vcl.get(j).getBooths().get(k).getPendingAppointments().get(p).getPatientPPS().equals(patients.get(m).getPpsNumber())) {                                 //If they have an associated pending Appointment.
                                    if (!appointmentSearchTableView.getItems().contains(vcl.get(j).getBooths().get(k).getPendingAppointments().get(p))) {
                                        appointmentSearchTableView.getItems().add(vcl.get(j).getBooths().get(k).getPendingAppointments().get(p));                                           //Display that Appointment once in the table.
                                    }
                                }
                            }
                            m++;
                        }
                    }
                    */
                }
            }
            j++;
        }
    }

    public void clear() {
        try {
            vcl.clear();
            patients.clear();
            completeAppointments.clear();
            updateVaccineCentreTable();
            updateAppointmentTableAll();
            updatePatientTable();
            saveLoadClear.setText("All data successfully cleared.");
        } catch (Exception e) {
            saveLoadClear.setText("An error occurred while clearing the system.");
        }
    }

    /**
     * Method to save the information which has been input by the user.
     * @throws Exception if the information cannot be saved.
     */
    @FXML
    public void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out1 = xstream.createObjectOutputStream(new FileWriter("centres.xml"));
        ObjectOutputStream out2 = xstream.createObjectOutputStream(new FileWriter("complete-appointments.xml"));
        ObjectOutputStream out3 = xstream.createObjectOutputStream(new FileWriter("patients.xml"));
        out1.writeObject(vcl);
        out2.writeObject(completeAppointments);
        out3.writeObject(patients);
        out1.close();
        out2.close();
        out3.close();
        saveLoadClear.setText("All data successfully saved.");
    }

    /** **LOAD NOT WORKING**
     * Method to load the information which has been input by the user.
     * @throws Exception if the information cannot be loaded.
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream read1 = xstream.createObjectInputStream(new FileReader("centres.xml"));
        ObjectInputStream read2 = xstream.createObjectInputStream(new FileReader("complete-appointments.xml"));
        ObjectInputStream read3 = xstream.createObjectInputStream(new FileReader("patients.xml"));
        vcl = (CustomList<VaccinationCentre>) read1.readObject();
        completeAppointments = (CustomList<Appointment>) read2.readObject();
        patients = (CustomList<Patient>) read3.readObject();
        read1.close();
        read2.close();
        read3.close();
        updateVaccineCentreTable();
        updateAppointmentTableAll();
        updatePatientTable();
        saveLoadClear.setText("All data successfully loaded.");
    }
}