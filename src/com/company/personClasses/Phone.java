package com.company.personClasses;

public class Phone {
    private String phoneNumber;
    private String landline;

    public Phone(String phoneNumber, String landline) {
        this.phoneNumber = phoneNumber;
        this.landline = landline;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", landline='" + landline + '\'' +
                '}';
    }
}
