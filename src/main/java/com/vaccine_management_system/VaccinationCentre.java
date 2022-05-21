package com.vaccine_management_system;

public class VaccinationCentre {
    private String name;
    private String address;
    private String eircode;
    private String parkingSpaces;
    private final CustomList<VaccinationBooth> booths = new CustomList<>();

    public VaccinationCentre(String name, String address, String eircode, String parkingSpaces) {
        if(name.length()==0) this.name = "Invalid";
        else if(Utilities.max50Chars(name)) this.name=name;
        else this.name = name.substring(0,46)+"...";

        if(address.length()==0) this.address = "Invalid";
        else if(!Utilities.max100Chars(address)) this.address = address.substring(0,96)+"...";
        else this.address = address;

        if(Utilities.validEircode(eircode)) this.eircode=eircode;
        else this.eircode = "Invalid";

        if(parkingSpaces.length()==0) this.parkingSpaces = "Invalid";
        else if(parkingSpaces.length()<=4) this.parkingSpaces = parkingSpaces;
        else this.parkingSpaces = "Invalid";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public String getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(String parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public CustomList<VaccinationBooth> getBooths() {
        return booths;
    }

    public void addBooth(String ID, String floor, Boolean accessibility) {
        booths.add(new VaccinationBooth(ID, floor, accessibility));
    }

    @Override
    public String toString() {
        return name + " vaccination centre is located at " + address + ", " + eircode + " with " + parkingSpaces + " available parking spaces.\n";
    }
}
