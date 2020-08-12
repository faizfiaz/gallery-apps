package com.gallery.apps.ui.home.adapter

import androidx.core.view.ViewCompat
import androidx.databinding.ObservableField
import com.gallery.apps.databinding.ItemThumbnailBinding
import com.gallery.apps.domain.models.GalleryThumbnail
import java.util.*

class HomeMenuViewModel(itemData: GalleryThumbnail?, var binding: ItemThumbnailBinding,
                        var actionClick: ((GalleryThumbnail, ItemThumbnailBinding) -> Unit)?) : Observable() {

    val image = ObservableField<String>()

    var data: GalleryThumbnail? = itemData

    init {
        image.set(data?.pathImage)
    }

    fun seeThumbnail() {
        ViewCompat.setTransitionName(binding.ivImage, "imageKey")
        with(binding) {
            executePendingBindings()
        }
        actionClick?.let { it(data!!, binding) }
    }

}