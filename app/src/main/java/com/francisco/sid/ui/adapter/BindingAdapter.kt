package com.francisco.sid.ui.adapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.francisco.sid.R

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, path: String) {
    val imgUri = path.toUri().buildUpon().scheme("http").build()
    Glide.with(imgView.context)
        .load(imgUri)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        ).into(imgView)
}