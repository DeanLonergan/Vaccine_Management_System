package com.vaccine_management_system;

public class Patient {
    private String name;
    private String dob;
    private String ppsNumber;
    private String address;
    private String phone;
    private boolean wheelchair;

    public Patient(String name, String dob, String ppsNumber, String address, String phone, boolean wheelchair) {
        if(name.equals("")) this.name="Invalid";
        else if (!Utilities.max30Chars(name)) this.name=name.substring(0,26)+"...";
        else this.name = name;

        if (dob != null) this.dob=dob;
        else this.dob = "Invalid";

        if(Utilities.validPPS(ppsNumber)) this.ppsNumber=ppsNumber;
        else this.ppsNumber = "Invalid";

        if(address.equals("")) this.address="Invalid";
        else if(!Utilities.max100Chars(address)) this.address = address.substring(0,96)+"...";
        else this.address = address;

        if (phone.equals("") || phone.length()>10) this.phone="Invalid";
        else this.phone = phone;

        this.wheelchair = wheelchair;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPpsNumber() {
        return ppsNumber;
    }

    public void setPpsNumber(String ppsNumber) {
        this.ppsNumber = ppsNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isWheelchair() {
        return wheelchair;
    }

    public void setWheelchair(boolean wheelchair) {
        this.wheelchair = wheelchair;
    }
}
