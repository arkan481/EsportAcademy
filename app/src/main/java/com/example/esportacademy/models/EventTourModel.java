package com.example.esportacademy.models;

import android.os.Parcel;
import android.os.Parcelable;

public class EventTourModel implements Parcelable {

    private int eventImage;

    public EventTourModel(int eventImage) {
        this.eventImage = eventImage;
    }

    protected EventTourModel(Parcel in) {
        eventImage = in.readInt();
    }

    public static final Creator<EventTourModel> CREATOR = new Creator<EventTourModel>() {
        @Override
        public EventTourModel createFromParcel(Parcel in) {
            return new EventTourModel(in);
        }

        @Override
        public EventTourModel[] newArray(int size) {
            return new EventTourModel[size];
        }
    };

    public void setEventImage(int eventImage) {
        this.eventImage = eventImage;
    }

    public int getEventImage() {
        return eventImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(eventImage);
    }
}
