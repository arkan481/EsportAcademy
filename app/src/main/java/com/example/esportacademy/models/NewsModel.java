package com.example.esportacademy.models;

public class NewsModel {

    private int image;
    private String newstitle;

    public NewsModel(int image, String newstitle) {
        this.image = image;
        this.newstitle = newstitle;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }
}
