package com.samagra.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by samagra on 24/2/18.
 */

public class CountryDetailFragment extends Fragment {
    private static final String EXTRA_COUNTRY_ITEM = "country_item";
    private static final String EXTRA_TRANSITION_NAME = "transition_name";

    public CountryDetailFragment() {
        // Required empty public constructor
    }

    public static CountryDetailFragment newInstance(CountryItem animalItem, String transitionName) {
        CountryDetailFragment countryDetailFragment = new CountryDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_COUNTRY_ITEM, animalItem);
        bundle.putString(EXTRA_TRANSITION_NAME, transitionName);
        countryDetailFragment.setArguments(bundle);
        return countryDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_country_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CountryItem countryItem = getArguments().getParcelable(EXTRA_COUNTRY_ITEM);
        String transitionName = getArguments().getString(EXTRA_TRANSITION_NAME);

        TextView detailTextView =  view.findViewById(R.id.animal_detail_text);
        detailTextView.setText(new StringBuilder().append("Rank: ").append(countryItem.getRank()).append("\n").append("Name: ").append(countryItem.getCountry()).append("\n").append("Population : ").append(countryItem.getPopulation()).toString());

        ImageView imageView = view.findViewById(R.id.animal_detail_image_view);
        imageView.setTransitionName(transitionName);

        Picasso.with(getContext())
                .load(countryItem.getFlag())
                .noFade()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        startPostponedEnterTransition();
                    }

                    @Override
                    public void onError() {
                        startPostponedEnterTransition();
                    }
                });
    }

}
