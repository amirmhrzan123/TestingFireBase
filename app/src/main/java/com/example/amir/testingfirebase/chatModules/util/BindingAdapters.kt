package com.example.amir.testingfirebase.chatModules.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.example.amir.testingfirebase.chatModules.util.extension.loadImages

/**
 * Created by Amir on 10/13/2018.
 */



object BindingAdapters {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
        with(imageView) {
            loadImages(url)
        }
    }



}
