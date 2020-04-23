package com.example.esportacademy.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class GameModel implements Parcelable {
    private String title;
    private int image;
    private int gameHeader;
    private int imageNewsHeader;
    private String textNewsHeader;
    private ArrayList<NewsTourModel> newsTourModels;
    private ArrayList<EventTourModel> eventTourModels;

    public GameModel(String title, int image,int gameHeader,int imageNewsHeader, String textNewsHeader,ArrayList<NewsTourModel> newsTourModels, ArrayList<EventTourModel> eventTourModels) {
        this.title = title;
        this.image = image;
        this.gameHeader = gameHeader;
        this.imageNewsHeader = imageNewsHeader;
        this.textNewsHeader = textNewsHeader;
        this.newsTourModels = newsTourModels;
        this.eventTourModels = eventTourModels;
    }

    public GameModel(String title, int image,int gameHeader,int imageNewsHeader, String textNewsHeader) {
        this.title = title;
        this.image = image;
        this.gameHeader = gameHeader;
        this.imageNewsHeader = imageNewsHeader;
        this.textNewsHeader = textNewsHeader;
    }

    protected GameModel(Parcel in) {
        title = in.readString();
        image = in.readInt();
        gameHeader = in.readInt();
        imageNewsHeader = in.readInt();
        textNewsHeader = in.readString();
        newsTourModels = in.readArrayList(NewsTourModel.class.getClassLoader());
        eventTourModels = in.readArrayList(EventTourModel.class.getClassLoader());
    }

    public static final Creator<GameModel> CREATOR = new Creator<GameModel>() {
        @Override
        public GameModel createFromParcel(Parcel in) {
            return new GameModel(in);
        }

        @Override
        public GameModel[] newArray(int size) {
            return new GameModel[size];
        }
    };

    public ArrayList<NewsTourModel> getNewsTourModels() {
        return newsTourModels;
    }

    public void setNewsTourModels(ArrayList<NewsTourModel> newsTourModels) {
        this.newsTourModels = newsTourModels;
    }

    public int getImageNewsHeader() {
        return imageNewsHeader;
    }

    public void setImageNewsHeader(int imageNewsHeader) {
        this.imageNewsHeader = imageNewsHeader;
    }

    public void setTextNewsHeader(String textNewsHeader) {
        this.textNewsHeader = textNewsHeader;
    }

    public String getTextNewsHeader() {
        return textNewsHeader;
    }

    public void setGameHeader(int gameHeader) {
        this.gameHeader = gameHeader;
    }

    public int getGameHeader() {
        return gameHeader;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void setEventTourModels(ArrayList<EventTourModel> eventTourModels) {
        this.eventTourModels = eventTourModels;
    }

    public ArrayList<EventTourModel> getEventTourModels() {
        return eventTourModels;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(image);
        dest.writeInt(gameHeader);
        dest.writeInt(imageNewsHeader);
        dest.writeString(textNewsHeader);
        dest.writeList(newsTourModels);
        dest.writeList(eventTourModels);
    }
}
