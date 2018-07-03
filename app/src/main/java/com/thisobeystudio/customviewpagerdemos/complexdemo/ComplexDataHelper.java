package com.thisobeystudio.customviewpagerdemos.complexdemo;

/*
 * Created by Endika Aguilera on 31/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.os.Parcel;
import android.os.Parcelable;

public class ComplexDataHelper implements Parcelable {

    private int posY;
    private float rating;
    private final int color;

    ComplexDataHelper(int color) {
        this.posY = 0;
        this.rating = 0;
        this.color = color;
    }

    private ComplexDataHelper(Parcel in) {
        posY = in.readInt();
        rating = in.readFloat();
        color = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(posY);
        dest.writeFloat(rating);
        dest.writeInt(color);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ComplexDataHelper> CREATOR = new Creator<ComplexDataHelper>() {
        @Override
        public ComplexDataHelper createFromParcel(Parcel in) {
            return new ComplexDataHelper(in);
        }

        @Override
        public ComplexDataHelper[] newArray(int size) {
            return new ComplexDataHelper[size];
        }
    };

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getColor() {
        return color;
    }

}
