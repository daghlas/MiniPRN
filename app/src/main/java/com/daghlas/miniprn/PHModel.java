package com.daghlas.miniprn;

public class PHModel {

    String datePaid, amountPaid, referenceNumber, phoneNumber, description;

    public PHModel(String datePaid, String amountPaid, String referenceNumber, String phoneNumber, String description) {
        this.datePaid = datePaid;
        this.amountPaid = amountPaid;
        this.referenceNumber = referenceNumber;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    public PHModel() {
        //default constructor, also no-argument constructor, also  empty constructor
        //Empty constructor for Firebase deserialization
    }

    public String getDatePaid() {
        return datePaid;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDescription() {
        return description;
    }
}
