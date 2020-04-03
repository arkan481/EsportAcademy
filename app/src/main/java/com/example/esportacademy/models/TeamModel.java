package com.example.esportacademy.models;

public class TeamModel {

    private String id,teamname,bglink,logolink,desc;

    public TeamModel(String id, String bglink, String logolink, String teamname,String desc) {
        this.id = id;
        this.teamname = teamname;
        this.bglink = bglink;
        this.logolink = logolink;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
