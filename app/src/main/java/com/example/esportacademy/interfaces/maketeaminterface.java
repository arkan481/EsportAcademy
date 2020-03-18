package com.example.esportacademy.interfaces;

import java.util.ArrayList;

public interface maketeaminterface {
     void setDescription(String desc);
     void setGames(ArrayList<String> games);
     void setAchievement(ArrayList<String>achievements);
     void setaAhievementDesc(ArrayList<String> achDesc);
     void setMember(ArrayList<String> member);
     void setMemberDesc(ArrayList<String>memberDesc);
     void setGenReady(boolean ready);
     void setAchReady(boolean ready);
     void setGallReady(boolean ready);
     void setMemberReady(boolean ready);
     String getDesc();
     ArrayList<String> getGames();
     ArrayList<String> getAchievement();
     ArrayList<String> getAchDesc();
     ArrayList<String> getMember();
     ArrayList<String> getMemberDesc();
}
