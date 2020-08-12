package com.gallery.apps.ui.login

import android.text.Editable
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import com.gallery.apps.R
import com.gallery.apps.domain.exceptions.MapperException
import com.gallery.apps.domain.usecases.user.IUserUsecases
import com.gallery.apps.ui.base.BaseViewModel
import com.gallery.apps.utils.AndroidUtils
import com.gallery.apps.utils.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(baseUsecase: IUserUsecases, schedulerProvider: SchedulerProvider) : BaseViewModel<IUserUsecases?, LoginNavigator?>(baseUsecase, schedulerProvider) {
    @kotlin.jvm.JvmField
    var username = ObservableField<String>()
    @kotlin.jvm.JvmField
    var password = ObservableField<String>()

    override fun onPositiveButtonClick(view: View?) {
        login()
    }

    override fun onNegativeButtonClick(view: View?) {}
    override fun defineLayout() {
        positiveButton.set(AndroidUtils.getString(R.string.login_page_positive_button))
        appBarTitle.set(AndroidUtils.getString(R.string.login_page_app_bar_title))
    }

    fun afterUsernameChanged(s: Editable) {
        username.set(s.toString())
    }

    fun afterPasswordChanged(s: Editable) {
        password.set(s.toString())
    }

    fun login() {
        isLoading(true)
        try {
            compositeDisposable.add(baseUsecase!!.login(username.get(), password.get())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ isSuccessfull: Boolean? -> this.onSuccess(isSuccessfull) }) { throwable: Throwable? -> onError(throwable!!) })
        } catch (e: MapperException) {
            e.printStackTrace()
            onError(e)
        }
    }

    fun onSuccess(isSuccessfull: Boolean?) {
        showProgressBar.set(false)
        Log.d(this.javaClass.name, "Login successful!")
    }
}