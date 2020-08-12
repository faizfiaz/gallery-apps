package com.gallery.apps.ui.home

import com.gallery.apps.databinding.ItemThumbnailBinding
import com.gallery.apps.domain.models.GalleryThumbnail
import com.gallery.apps.ui.base.BaseNavigator

interface HomeNavigator : BaseNavigator {
    fun showImageDialog(item: GalleryThumbnail, binding: ItemThumbnailBinding)

}