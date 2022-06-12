package com.example.huitdduru.adapter.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.huitdduru.R

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url: String?) {
    Glide.with(imageView).load(url)
        .centerCrop()
        .error(R.drawable.ic_profile)
        .into(imageView)
}
