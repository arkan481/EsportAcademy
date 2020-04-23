package com.example.esportacademy.models;

import android.os.Parcel;
import android.os.Parcelable;

public class GameModel implements Parcelable {
    private String title;
    private int image;
    private int gameHeader;

    public GameModel(String title, int image,int gameHeader) {
        this.title = title;
        this.image = image;
        this.gameHeader = gameHeader;
    }

    protected GameModel(Parcel in) {
        title = in.readString();
        image = in.readInt();
        gameHeader = in.readInt();
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(image);
        dest.writeInt(gameHeader);
    }
}
