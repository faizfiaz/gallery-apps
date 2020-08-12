package com.gallery.apps.ui.registration

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import com.gallery.apps.R
import com.gallery.apps.domain.entities.response.RegistrationResponse
import com.gallery.apps.domain.exceptions.MapperException
import com.gallery.apps.domain.usecases.user.IUserUsecases
import com.gallery.apps.ui.base.BaseViewModel
import com.gallery.apps.utils.AndroidUtils
import com.gallery.apps.utils.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegistrationViewModel(baseUsecase: IUserUsecases, schedulerProvider: SchedulerProvider) : BaseViewModel<IUserUsecases?, RegistrationNavigator?>(baseUsecase, schedulerProvider) {
    var username = ObservableField<String>()
    var email = ObservableField<String>()
    var password = ObservableField<String>()
    override fun onPositiveButtonClick(view: View?) {
        registration()
    }

    override fun onNegativeButtonClick(view: View?) {}
    override fun defineLayout() {
        positiveButton.set(AndroidUtils.getString(R.string.registration_page_positive_button))
    }

    fun registration() {
        isLoading(true)
        try {
            compositeDisposable.add(baseUsecase!!.registration(email.get(), username.get(), password.get())
            !!.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response: RegistrationResponse? -> this.onSuccess(response) }) { throwable: Throwable? -> onError(throwable!!) })
        } catch (e: MapperException) {
            e.printStackTrace()
            onError(e)
        }
    }

    private fun onSuccess(response: RegistrationResponse?) {
        Log.d(this.javaClass.name, "Token: " + response?.jwt)
    }
}