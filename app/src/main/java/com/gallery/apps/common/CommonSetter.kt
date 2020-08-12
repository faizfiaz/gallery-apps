package com.gallery.apps.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.gallery.apps.R
import com.gallery.apps.utils.GlideApp

object CommonSetter {
    @BindingAdapter("imageSource")
    fun imageSource(view: ImageView, imageSource: Int) {
        view.setImageResource(imageSource)
    }

    @JvmStatic
    @BindingAdapter("imagePath")
    fun imageSourceUrl(view: ImageView, url: String) {
        if (url.isNullOrEmpty()) {
            GlideApp.with(view.context).load(R.drawable.error_image).into(view)
        } else {
            GlideApp.with(view.context)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.loading_image)
                    .into(view)
        }
    }

    @kotlin.jvm.JvmStatic
    @BindingAdapter("isVisible")
    fun setIsVisible(view: View, isVisible: Boolean?) {
        if (isVisible == null) {
            return
        }
        view.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}