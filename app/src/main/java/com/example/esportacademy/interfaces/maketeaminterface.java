package com.example.esportacademy.interfaces;

import android.net.Uri;

import java.util.ArrayList;

public interface maketeaminterface {
     void setDescription(String desc);
     void setGenUpdating(boolean updating);
     void setAchUpdating(boolean updating);
     void setGallUpdating(boolean updating);
     void setMemberUpdating(boolean updating);
     void setGames(ArrayList<String> games);
     void setAchievement(ArrayList<String>achievements);
     void setaAhievementDesc(ArrayList<String> achDesc);
     void setMember(ArrayList<String> member);
     void setMemberPhoto(byte[]bytes);
     void setTempMemberPhoto(Uri uri);
     void setMemberDesc(ArrayList<String>memberDesc);
     void setRecruitmentImage(byte[] bytes);
     void setTempRecruitmentImage(Uri tempRecruitmentImage);
     void setGalleryImages(byte[] bytes);
     void setTempGalleryImages(Uri uri);
     void setAchImage(byte[]bytes);
     void setTempAchImage(Uri uri);
     String getDesc();
     ArrayList<String> getGames();
     ArrayList<String> getAchievement();
     ArrayList<String> getAchDesc();
     ArrayList<String> getMember();
     ArrayList<String> getMemberDesc();
     boolean getGenUpdating();
     boolean getAchUpdating();
     boolean getMemberUpdating();
     boolean getGallUpdating();
     byte[] getRecruitmentImage();
     byte[] getAchImage();
     Uri getTempAchImage();
     Uri getTempRecruitmentImage();
     byte[] getGalleryImages();
     Uri getTempGalleryImages();
     byte[] getMemberPhoto();
     Uri getTempMemberPhoto();
}
