package com.example.esportacademy.models;

public class TeamMemberModel {
    private String username;
    private String photo;


    public TeamMemberModel(String username, String photo) {
        this.username = username;
        this.photo = photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
