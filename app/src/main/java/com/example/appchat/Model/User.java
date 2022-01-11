package com.example.appchat.Model;

public class User {
    private String id;
    private String phone;
    private String password;
    private String fullName;
    private String birthday;
    private String gender;
    private byte[] photoProfile;

    public User(String id, String phone, String password, String fullName, String birthday, String gender, byte[] photoProfile) {
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.fullName = fullName;
        this.birthday = birthday;
        this.gender = gender;
        this.photoProfile = photoProfile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public byte[] getPhotoProfile() {
        return photoProfile;
    }

    public void setPhotoProfile(byte[] photoProfile) {
        this.photoProfile = photoProfile;
    }
}
