package com.gallery.apps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.gallery.apps.data.remote.UserRepository
import com.gallery.apps.domain.mappers.UserMapper
import com.gallery.apps.domain.usecases.user.IUserUsecases
import com.gallery.apps.domain.usecases.user.UserUsecases
import com.gallery.apps.ui.home.HomeViewModel
import com.gallery.apps.ui.login.LoginViewModel
import com.gallery.apps.ui.registration.RegistrationViewModel
import com.gallery.apps.utils.SchedulerProvider
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelProviderFactory @Inject constructor(private val schedulerProvider: SchedulerProvider) : NewInstanceFactory() {
    private val userUsecases: IUserUsecases
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                return LoginViewModel(userUsecases, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(RegistrationViewModel::class.java) -> {
                return RegistrationViewModel(userUsecases, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(null, schedulerProvider) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    init {
        userUsecases = UserUsecases(UserMapper(), UserRepository.instance!!)
    }
}