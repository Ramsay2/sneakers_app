package com.sachin.sneaker_app.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("image_load")
fun loadImage(imageView: ImageView, loadLink: String) {
    Glide.with(imageView)
        .load(loadLink).into(imageView)
}