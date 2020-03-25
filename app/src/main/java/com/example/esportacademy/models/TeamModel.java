package com.example.esportacademy.models;

public class TeamModel {

    private String teamname,bglink,logolink;

    public TeamModel(String bglink,String logolink, String teamname) {
        this.bglink = bglink;
        this.logolink = logolink;
        this.teamname = teamname;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getBglink() {
        return bglink;
    }

    public void setBglink(String bglink) {
        this.bglink = bglink;
    }

    public String getLogolink() {
        return logolink;
    }

    public void setLogolink(String logolink) {
        this.logolink = logolink;
    }
}
