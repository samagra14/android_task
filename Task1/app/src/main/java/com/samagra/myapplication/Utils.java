package com.samagra.myapplication;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by samagra on 24/2/18.
 */

public class Utils {
    public static ArrayList<CountryItem> generateCountryItems(Context context) {
        ArrayList<CountryItem> countryItems = new ArrayList<>();
        countryItems.add(new CountryItem(1,"China","1,354,040,000","http://www.androidbegin.com/tutorial/flag/china.png"));
        countryItems.add(new CountryItem(2,"India","1,210,193,422","http://www.androidbegin.com/tutorial/flag/india.png"));
        countryItems.add(new CountryItem(3,"United States","315,761,000","http://www.androidbegin.com/tutorial/flag/unitedstates.png"));
        countryItems.add(new CountryItem(4,"Indonesia","237,641,326","http://www.androidbegin.com/tutorial/flag/indonesia.png"));
        countryItems.add(new CountryItem(5,"Brazil","193,946,886","http://www.androidbegin.com/tutorial/flag/brazil.png"));
        countryItems.add(new CountryItem(6,"Pakistan","182,912,000","http://www.androidbegin.com/tutorial/flag/pakistan.png"));
        countryItems.add(new CountryItem(7,"Nigeria","170,901,000","http://www.androidbegin.com/tutorial/flag/nigeria.png"));
        countryItems.add(new CountryItem(8,"Bangladesh","152,518,015","http://www.androidbegin.com/tutorial/flag/bangladesh.png"));
        countryItems.add(new CountryItem(9,"Russia","143,369,806","http://www.androidbegin.com/tutorial/flag/russia.png"));
        countryItems.add(new CountryItem(10,"Japan","127,360,000","http://www.androidbegin.com/tutorial/flag/japan.png"));
        return countryItems;
    }
}
