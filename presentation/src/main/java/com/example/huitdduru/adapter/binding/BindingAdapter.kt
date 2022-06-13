package com.example.huitdduru.adapter.binding

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.huitdduru.R

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url: String?) {
    imageView.clipToOutline = true
    Glide.with(imageView).load(url)
        .centerCrop()
        .error(R.drawable.ic_profile)
        .into(imageView)
}

@BindingAdapter("loadDiaryImage")
fun loadDiaryImage(imageView: ImageView, url: String?) {
    Glide.with(imageView).load(url)
        .centerCrop()
        .into(imageView)
}

@SuppressLint("SetTextI18n")
@BindingAdapter("hourToDay")
fun hourToDay(textView: TextView, hour: Int) {
    if(hour < 24) textView.text = "$hour 시간 전"
    else textView.text = "${hour / 24}일 전"
}
