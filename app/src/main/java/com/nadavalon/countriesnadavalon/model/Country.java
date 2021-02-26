package com.nadavalon.countriesnadavalon.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country implements Parcelable {

    @SerializedName("nativeName")
    private String nativeName;

    @SerializedName("name")
    private String name;

    @SerializedName("area")
    private Double area;

    @SerializedName("borders")
    private List<String> borders;

    public Country(String nativeName, String name, Double area, List<String> borders) {
        this.nativeName = nativeName;
        this.name = name;
        this.area = area;
        this.borders = borders;
    }

    protected Country(Parcel in) {
        nativeName = in.readString();
        name = in.readString();
        area = in.readDouble();
        borders = in.createStringArrayList();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public String getNativeName() {
        return nativeName;
    }

    public String getName() {
        return name;
    }

    public Double getArea() {
        return area;
    }

    public List<String> getBorders() {
        return borders;
    }

    @Override
    public String toString() {
        return
                "CountriesListItem{" +
                        "nativeName = '" + nativeName + '\'' +
                        ",name = '" + name + '\'' +
                        ",area = '" + area + '\'' +
                        ",borders = '" + borders + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nativeName);
        dest.writeString(this.name);
        dest.writeDouble(this.area);
        dest.writeStringList(this.borders);

    }
}
