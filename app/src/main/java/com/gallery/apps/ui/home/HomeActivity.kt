package com.gallery.apps.ui.home

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.gallery.apps.BR
import com.gallery.apps.R
import com.gallery.apps.ViewModelProviderFactory
import com.gallery.apps.databinding.ActivityHomeBinding
import com.gallery.apps.databinding.ItemThumbnailBinding
import com.gallery.apps.domain.models.GalleryThumbnail
import com.gallery.apps.ui.base.BaseActivity
import javax.inject.Inject


class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeNavigator {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_home
    override val viewModel: HomeViewModel
        get() = ViewModelProvider(this, factory!!).get(HomeViewModel::class.java)

    private var requestCode: Int = 99
    private var permissions: Array<String> = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

    companion object {
        fun getIntent(context: Context): Intent {
            var intent = Intent(context, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        requestPermission()
    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat
                    .requestPermissions(
                            this,
                            permissions,
                            requestCode);
        }else{
            viewModel.loadImageGallery()
        }
    }

    override fun showImageDialog(item: GalleryThumbnail, binding: ItemThumbnailBinding) {
        val builder = Dialog(this)
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE)
        builder.window?.setBackgroundDrawable(
                ColorDrawable(Color.TRANSPARENT))
        builder.setOnDismissListener(DialogInterface.OnDismissListener {
            //nothing;
        })
        val imageView = ImageView(this)
        imageView.setImageURI(Uri.parse(item.pathImage))
        builder.addContentView(imageView, RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT))
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        builder.show()
    }

    override fun handleError(throwable: Throwable?) {

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestCode
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            viewModel.loadImageGallery()
        }
    }
}