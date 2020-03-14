package com.example.esportacademy.models;

public class TeamModel {
    private int image1;
    private int image2;
    private int image3;
    private int image4;
    private int teamlogo;
    private String teamname;

    public TeamModel(int image1, int image2, int image3, int image4, int teamlogo, String teamname) {
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.teamlogo = teamlogo;
        this.teamname = teamname;
    }

    public int getTeamlogo() {
        return teamlogo;
    }

    public void setTeamlogo(int teamlogo) {
        this.teamlogo = teamlogo;
    }

    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage2(int image2) {
        this.image2 = image2;
    }

    public int getImage3() {
        return image3;
    }

    public void setImage3(int image3) {
        this.image3 = image3;
    }

    public int getImage4() {
        return image4;
    }

    public void setImage4(int image4) {
        this.image4 = image4;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }
}
