package com.example.appchat.Model;

public class Message {
    private String personSender;
    private String personReceiver;
    private String message;
    private byte[] photoPerson;
    private String time;

    public Message(String personSender, String personReceiver, String message, byte[] photoPerson, String time) {
        this.personSender = personSender;
        this.personReceiver = personReceiver;
        this.message = message;
        this.photoPerson = photoPerson;
        this.time = time;
    }

    public String getPersonSender() {
        return personSender;
    }

    public void setPersonSender(String personSender) {
        this.personSender = personSender;
    }

    public String getPersonReceiver() {
        return personReceiver;
    }

    public void setPersonReceiver(String personReceiver) {
        this.personReceiver = personReceiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public byte[] getPhotoPerson() {
        return photoPerson;
    }

    public void setPhotoPerson(byte[] photoPerson) {
        this.photoPerson = photoPerson;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
