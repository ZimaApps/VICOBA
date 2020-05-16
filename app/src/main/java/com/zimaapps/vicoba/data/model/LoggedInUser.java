package com.zimaapps.vicoba.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;
    private String phone;

    public LoggedInUser(String userId, String displayName, String phone) {
        this.userId = userId;
        this.displayName = displayName;
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public String getPhone() {
        return phone;
    }

    public String getDisplayName() {
        return displayName;
    }
}
