package com.example.amir.testingfirebase.chatModules.util.extension

import android.widget.ImageView
import com.example.amir.testingfirebase.R

/**
 * Created by Amir on 10/18/2018.
 */

fun ImageView.loadImages(url: String?) {
    if (!url.isNullOrEmpty()) {


        GlideApp.with(context)
                .load(url)
                .placeholder(R.color.background)
                .into(this)
    } else {
        GlideApp.with(context)
                .load(R.color.background)
                .placeholder(R.color.background)
                .into(this)
    }
}