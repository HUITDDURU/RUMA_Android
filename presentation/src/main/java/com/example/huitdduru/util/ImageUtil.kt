package com.example.huitdduru.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class ImageUtil {
    @BindingAdapter("loadImage")
    fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView).load(url)
            .centerCrop()
            .into(imageView)
    }
}