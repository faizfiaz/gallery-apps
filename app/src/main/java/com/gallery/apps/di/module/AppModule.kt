package com.gallery.apps.di.module

import android.app.Application
import android.content.Context
import com.gallery.apps.R
import com.gallery.apps.utils.AndroidUtils
import com.gallery.apps.utils.AppSchedulerProvider
import com.gallery.apps.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @Singleton
    fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
        return CalligraphyConfig.Builder()
                .setDefaultFontPath(AndroidUtils.getString(R.string.font_path_caviardreams))
                .setFontAttrId(R.attr.fontPath)
                .build()
    }
}