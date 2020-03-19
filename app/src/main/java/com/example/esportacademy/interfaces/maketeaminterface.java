package com.example.esportacademy.interfaces;

import java.util.ArrayList;

public interface maketeaminterface {
     void setDescription(String desc);
     void setGenUpdating(boolean updating);
     void setAchUpdating(boolean updating);
     void setMemberUpdating(boolean updating);
     void setGames(ArrayList<String> games);
     void setAchievement(ArrayList<String>achievements);
     void setaAhievementDesc(ArrayList<String> achDesc);
     void setMember(ArrayList<String> member);
     void setMemberDesc(ArrayList<String>memberDesc);
     void click();
     String getDesc();
     ArrayList<String> getGames();
     ArrayList<String> getAchievement();
     ArrayList<String> getAchDesc();
     ArrayList<String> getMember();
     ArrayList<String> getMemberDesc();
     boolean getGenUpdating();
     boolean getAchUpdating();
     boolean getMemberUpdating();
}
