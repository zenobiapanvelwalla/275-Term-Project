package com.backend.netflix.beans;

import java.math.BigInteger;

public class TopUser {

    private int id;
    private String email;
    private String displayName;
    //private boolean verified;
    //private String verificationCode;
    //private String role;
    //private int noOfPlays =0;

    private BigInteger playCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public BigInteger getPlayCount() {
        return playCount;
    }

    public void setPlayCount(BigInteger playCount) {
        this.playCount = playCount;
    }
}
