package com.sachin.sneaker_app.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sachin.sneaker_app.adapter.SneakersAdapter
import com.sachin.sneaker_app.model.SneakerResponse



@BindingAdapter("image_load")
fun loadImage(imageView: ImageView, loadLink: String) {
    Glide.with(imageView)
        .load(loadLink).into(imageView)
}