package com.caltech.natalassistsplus;

public class Patient {
    public String getUsername() {
        return username;
    }

    public String getDocID() {
        return docID;
    }

    public Patient(String username, String docID) {
        this.username = username;
        this.docID = docID;
    }

    private String username;
    private String docID;

    public Patient(){}
}
