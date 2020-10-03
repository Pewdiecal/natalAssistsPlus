package com.caltech.natalassistsplus;

public class UserAccount {

    public String username;
    public String recoveryStatus;
    public String docID;

    public UserAccount(String username, String recoveryStatus, String docID) {
        this.username = username;
        this.recoveryStatus = recoveryStatus;
        this.docID = docID;
    }

    public String getUsername() {
        return username;
    }

    public String getRecoveryStatus() {
        return recoveryStatus;
    }

    public String getDocID() {
        return docID;
    }
}
