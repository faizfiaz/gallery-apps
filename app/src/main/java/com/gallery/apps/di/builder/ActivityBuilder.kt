package com.gallery.apps.di.builder

import com.gallery.apps.ui.home.HomeActivity
import com.gallery.apps.ui.login.LoginActivity
import com.gallery.apps.ui.registration.RegistrationActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [])
    abstract fun bindLoginActivity(): LoginActivity?

    @ContributesAndroidInjector(modules = [])
    abstract fun bindRegistrationActivity(): RegistrationActivity?

    @ContributesAndroidInjector(modules = [])
    abstract fun bindHomeActivity(): HomeActivity?
}