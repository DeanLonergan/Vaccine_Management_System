package com.vaccine_management_system;

public class Appointment {
    private String location;
    private String booth;
    private String date;
    private String time;
    private String dose;
    private String type;
    private String batch;
    private String nurseID;
    private String patientPPS;
    private boolean complete;


    public Appointment(String location, String booth, String date, String time, String dose, String type, String batch, String nurseID, String patientPPS, boolean complete) {
        this.location = location;
        this.booth = booth;
        this.date = date;
        this.time = time;
        this.dose = dose;
        this.type = type;
        this.batch = batch;
        this.nurseID = nurseID;
        this.patientPPS = patientPPS;
        this.complete = complete;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBooth() {
        return booth;
    }

    public void setBooth(String booth) {
        this.booth = booth;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getNurseID() {
        return nurseID;
    }

    public void setNurseID(String nurseID) {
        this.nurseID = nurseID;
    }

    public String getPatientPPS() {
        return patientPPS;
    }

    public void setPatientPPS(String patientPPS) {
        this.patientPPS = patientPPS;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
