package com.vaccine_management_system;

public class VaccinationBooth {
    private String ID;
    private String floor;
    private Boolean accessibility;
    private final CustomList<Appointment> pendingAppointments = new CustomList<>();

    public VaccinationBooth(String ID, String floor, Boolean accessibility) {
        if(Utilities.validID(ID)) this.ID = ID;
        else this.ID = "Invalid";

        if(Utilities.validFloor(floor)) this.floor = floor;
        else this.floor = "Invalid";

        this.accessibility = accessibility;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Boolean getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(Boolean accessibility) {
        this.accessibility = accessibility;
    }

    public CustomList<Appointment> getPendingAppointments() {
        return pendingAppointments;
    }

    @Override
    public String toString() {
        String access = "is";
        if(!accessibility) access = "is not";
        return "Booth ID: " + ID + " is on Floor: " + floor + " and " + access + " wheelchair accessible.";
    }
}
