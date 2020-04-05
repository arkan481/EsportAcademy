package com.example.esportacademy.models;

public class NewsTourModel {
    private int image;
    private String newsHead,newsFooter;

    public NewsTourModel(int image, String newsHead, String newsFooter) {
        this.image = image;
        this.newsHead = newsHead;
        this.newsFooter = newsFooter;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNewsHead() {
        return newsHead;
    }

    public void setNewsHead(String newsHead) {
        this.newsHead = newsHead;
    }

    public String getNewsFooter() {
        return newsFooter;
    }

    public void setNewsFooter(String newsFooter) {
        this.newsFooter = newsFooter;
    }
}
