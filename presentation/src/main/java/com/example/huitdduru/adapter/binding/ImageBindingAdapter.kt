package com.example.huitdduru.adapter.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url: String?) {
    if (url != null) {
        Glide.with(imageView).load(url)
            .centerCrop()
            .into(imageView)
    }
}
