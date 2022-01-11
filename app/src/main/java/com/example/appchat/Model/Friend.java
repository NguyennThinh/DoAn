package com.example.appchat.Model;

import java.io.Serializable;

public class Friend implements Serializable {
    private String userId;
    private String fullName;
    private String imageProfile;
    private String userName;
    private String status;

    public Friend(String userId, String fullName, String imageProfile, String userName, String status) {
        this.userId = userId;
        this.fullName = fullName;
        this.imageProfile = imageProfile;
        this.userName = userName;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
