package com.final_test.moneylovely.model;

public class User {
    private String userID;
    private String username;
    private String full_name;
    private String password;
    private String address;


    public User() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User(String userID, String username, String full_name, String password, String address) {
        this.userID = userID;
        this.username = username;
        this.full_name = full_name;
        this.password = password;
        this.address = address;
    }

}
