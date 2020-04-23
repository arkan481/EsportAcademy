package com.example.esportacademy.models;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsTourModel implements Parcelable {
    private int image;
    private String newsHead,newsFooter;

    public NewsTourModel(int image, String newsHead, String newsFooter) {
        this.image = image;
        this.newsHead = newsHead;
        this.newsFooter = newsFooter;
    }

    protected NewsTourModel(Parcel in) {
        image = in.readInt();
        newsHead = in.readString();
        newsFooter = in.readString();
    }

    public static final Creator<NewsTourModel> CREATOR = new Creator<NewsTourModel>() {
        @Override
        public NewsTourModel createFromParcel(Parcel in) {
            return new NewsTourModel(in);
        }

        @Override
        public NewsTourModel[] newArray(int size) {
            return new NewsTourModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(newsHead);
        dest.writeString(newsFooter);
    }
}
