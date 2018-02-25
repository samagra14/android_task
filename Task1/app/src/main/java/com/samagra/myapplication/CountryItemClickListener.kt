package com.samagra.myapplication

import android.widget.ImageView

/**
 * Created by samagra on 24/2/18.
 */

interface CountryItemClickListener {
    fun onCountryItemClick(pos: Int, countryItem: CountryItem, shareImageView: ImageView)
}
