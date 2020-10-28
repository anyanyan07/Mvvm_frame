package com.ayy.base.databinding

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class DataBindingUtils {
    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String) {
            if (!TextUtils.isEmpty(url)) {
                Glide.with(imageView.context)
                    .load(url)
                    .centerCrop()
                    .into(imageView)
            }
        }
    }
}