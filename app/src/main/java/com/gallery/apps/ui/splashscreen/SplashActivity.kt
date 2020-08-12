package com.gallery.apps.ui.splashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gallery.apps.R
import com.gallery.apps.ui.home.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        CoroutineScope(Dispatchers.Default).launch {
            delay(2000)
            startActivity(HomeActivity.getIntent(this@SplashActivity))
        }
    }

}
