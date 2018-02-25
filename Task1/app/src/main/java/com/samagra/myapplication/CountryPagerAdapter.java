package com.samagra.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by samagra on 24/2/18.
 */

public class CountryPagerAdapter extends FragmentStatePagerAdapter{

    private List<CountryItem> countryItems;

    CountryPagerAdapter(FragmentManager fm, List<CountryItem> countryItems) {
        super(fm);
        this.countryItems = countryItems;
    }

    @Override
    public Fragment getItem(int position) {
        CountryItem countryItem= countryItems.get(position);
        return CountryDetailFragment.newInstance(countryItem, countryItem.getCountry());
    }

    @Override
    public int getCount() {
        return countryItems.size();
    }
}
