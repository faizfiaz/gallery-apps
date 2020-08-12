package com.gallery.apps.ui.registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.gallery.apps.BR
import com.gallery.apps.R
import com.gallery.apps.ViewModelProviderFactory
import com.gallery.apps.databinding.ActivityRegistrationBinding
import com.gallery.apps.ui.base.BaseActivity
import javax.inject.Inject

class RegistrationActivity : BaseActivity<ActivityRegistrationBinding?, RegistrationViewModel>(), RegistrationNavigator {
    @kotlin.jvm.JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null
    private var mRegistrationViewModel: RegistrationViewModel? = null
    private var mActivityRegistrationBinding: ActivityRegistrationBinding? = null
    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_registration

    override val viewModel: RegistrationViewModel
        get() {
            mRegistrationViewModel = ViewModelProviders.of(this, factory).get(RegistrationViewModel::class.java)
            return mRegistrationViewModel!!
        }

    override fun handleError(throwable: Throwable?) {}
    override fun registration() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityRegistrationBinding = viewDataBinding
        mRegistrationViewModel!!.setNavigator(this)
    }

    companion object {
        fun newIntent(context: Context?): Intent {
            return Intent(context, RegistrationActivity::class.java)
        }
    }
}