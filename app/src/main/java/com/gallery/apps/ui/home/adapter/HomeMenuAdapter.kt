package com.gallery.apps.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gallery.apps.databinding.ItemThumbnailBinding
import com.gallery.apps.domain.models.GalleryThumbnail
import com.gallery.apps.ui.base.BaseAdapter

class HomeMenuAdapter(data: ArrayList<GalleryThumbnail>, var actionClick: ((GalleryThumbnail, ItemThumbnailBinding) -> Unit)?)
    : BaseAdapter<GalleryThumbnail>(data) {

    private lateinit var binding: ItemThumbnailBinding

    override fun createHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemThumbnailBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)

        return GenericViewHolder(binding)
    }

    override fun bindingViewHolder(holder: GenericViewHolder, position: Int) {
        (holder.viewDataBinding as ItemThumbnailBinding).itemViewModel =
                HomeMenuViewModel(getItem(position), binding, actionClick)
    }

}