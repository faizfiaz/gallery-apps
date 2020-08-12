package com.gallery.apps.ui.home

import android.app.Dialog
import android.content.DialogInterface
import android.database.Cursor
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.provider.MediaStore
import android.provider.MediaStore.MediaColumns
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.lifecycle.viewModelScope
import com.gallery.apps.App
import com.gallery.apps.databinding.ItemThumbnailBinding
import com.gallery.apps.domain.models.GalleryThumbnail
import com.gallery.apps.ui.base.BaseViewModel
import com.gallery.apps.ui.home.adapter.HomeMenuAdapter
import com.gallery.apps.utils.SchedulerProvider
import kotlinx.coroutines.launch


class HomeViewModel(baseUsecase: Any?, schedulerProvider: SchedulerProvider)
    : BaseViewModel<Any?, HomeNavigator>(baseUsecase, schedulerProvider) {

    var adapterImage: HomeMenuAdapter = HomeMenuAdapter(ArrayList(), ::displayImage)

    override fun defineLayout() {

    }

    override fun onPositiveButtonClick(view: View?) {

    }

    override fun onNegativeButtonClick(view: View?) {

    }

    fun getAdapter(): HomeMenuAdapter {
        return this.adapterImage
    }

    fun displayImage(item: GalleryThumbnail, binding: ItemThumbnailBinding) {
        Log.d("TAG", "displayImage: " + item.pathImage)
        navigator?.showImageDialog(item, binding)
    }

    fun loadImageGallery() {
        showProgressBar.set(true)
        viewModelScope.launch {
            val cursor: Cursor
            val columnIndexData: Int
            val columnIndexFolderName: Int

            var absolutePathOfImage: String? = null
            val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val projection = arrayOf(MediaColumns.DATA,
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            try {
                cursor = App.appContext?.contentResolver?.query(uri, projection, null,
                        null, null)!!
                columnIndexData = cursor.getColumnIndexOrThrow(MediaColumns.DATA)
                columnIndexFolderName = cursor
                        .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
                while (cursor.moveToNext()) {
                    absolutePathOfImage = cursor.getString(columnIndexData)

                    adapterImage.addItem(GalleryThumbnail(columnIndexData, absolutePathOfImage))
                }
                cursor.close()
            } catch (e: Exception) {

            }
            showProgressBar.set(false)
        }
    }
}