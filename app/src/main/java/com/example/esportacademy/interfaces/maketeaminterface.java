package com.example.esportacademy.interfaces;

import android.net.Uri;
import android.os.Parcelable;

import java.io.Serializable;
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
     void setTempMemberPhoto(Uri uri);
     void setMemberDesc(ArrayList<String>memberDesc);
     void setTempRecruitmentImage(Uri tempRecruitmentImage);
     void setTempGalleryImages(Uri uri);
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
     Uri getTempAchImage();
     Uri getTempRecruitmentImage();
     Uri getTempGalleryImages();
     Uri getTempMemberPhoto();
}
