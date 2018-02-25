package com.samagra.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by samagra on 24/2/18.
 */

public class Response {
    @SerializedName("worldpopulation")
    @Expose
    private List<CountryItem> countryItemList = null;

    public List<CountryItem> getWorldpopulation() {
        return countryItemList;
    }

    public void setWorldpopulation(List<CountryItem> countryItemList) {
        this.countryItemList = countryItemList;
    }
}
