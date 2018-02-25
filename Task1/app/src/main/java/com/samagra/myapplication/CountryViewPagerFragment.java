package com.samagra.myapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by samagra on 24/2/18.
 */

public class CountryViewPagerFragment extends Fragment {
    private static final String EXTRA_INITIAL_ITEM_POS = "initial_item_pos";
    private static final String EXTRA_COUNTRY_ITEMS = "country_items";
    public CountryViewPagerFragment() {
        // Required empty public constructor
    }

    public static CountryViewPagerFragment newInstance(int currentItem, ArrayList<CountryItem> countryItems) {
        CountryViewPagerFragment countryViewPagerFragment = new CountryViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_INITIAL_ITEM_POS, currentItem);
        bundle.putParcelableArrayList(EXTRA_COUNTRY_ITEMS, countryItems);
        countryViewPagerFragment.setArguments(bundle);
        return countryViewPagerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        setSharedElementReturnTransition(null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_country_view_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int currentItem = getArguments().getInt(EXTRA_INITIAL_ITEM_POS);
        ArrayList<CountryItem> countryItems = getArguments().getParcelableArrayList(EXTRA_COUNTRY_ITEMS);

        CountryPagerAdapter countryPagerAdapter = new CountryPagerAdapter(getChildFragmentManager(), countryItems);

        ViewPager viewPager = view.findViewById(R.id.animal_view_pager);
        viewPager.setAdapter(countryPagerAdapter);
        viewPager.setCurrentItem(currentItem);
    }
}
