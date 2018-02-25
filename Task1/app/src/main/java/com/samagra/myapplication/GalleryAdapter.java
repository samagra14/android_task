package com.samagra.myapplication;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by samagra on 24/2/18.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ImageViewHolder> {
    private final CountryItemClickListener countryItemClickListener;
    private ArrayList<CountryItem> countryItems;

    public GalleryAdapter(ArrayList<CountryItem> countryItems, CountryItemClickListener countryItemClickListener) {
        this.countryItems = countryItems;
        this.countryItemClickListener = countryItemClickListener;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_layout, parent, false));
    }

    @Override
    public int getItemCount() {
        return countryItems.size();
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {
        final CountryItem countryItem = countryItems.get(position);

        Picasso.with(holder.itemView.getContext())
                .load(countryItem.getFlag())
                .into(holder.animalImageView);

        ViewCompat.setTransitionName(holder.animalImageView, countryItem.getCountry());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countryItemClickListener.onCountryItemClick(holder.getAdapterPosition(), countryItem, holder.animalImageView);
            }
        });
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView animalImageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            animalImageView = (ImageView) itemView.findViewById(R.id.item_animal_square_image);
        }
    }
}
