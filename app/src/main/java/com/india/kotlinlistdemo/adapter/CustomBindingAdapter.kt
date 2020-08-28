package com.india.kotlinlistdemo.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object CustomBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageAdapter(view: ImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .fitCenter()
            .into(view)

    }
}