package com.samagra.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by samagra on 24/2/18.
 */

public class CountryItem implements Parcelable{
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("population")
    @Expose
    private String population;
    @SerializedName("flag")
    @Expose
    private String flag;

    public CountryItem(Integer rank, String country, String population, String flag) {
        this.rank = rank;
        this.country = country;
        this.population = population;
        this.flag = flag;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public static Creator<CountryItem> getCREATOR() {
        return CREATOR;
    }

    protected CountryItem(Parcel in) {
        if (in.readByte() == 0) {
            rank = null;
        } else {
            rank = in.readInt();
        }
        country = in.readString();
        population = in.readString();
        flag = in.readString();
    }

    public static final Creator<CountryItem> CREATOR = new Creator<CountryItem>() {
        @Override
        public CountryItem createFromParcel(Parcel in) {
            return new CountryItem(in);
        }

        @Override
        public CountryItem[] newArray(int size) {
            return new CountryItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (rank == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(rank);
        }
        parcel.writeString(country);
        parcel.writeString(population);
        parcel.writeString(flag);
    }
}
