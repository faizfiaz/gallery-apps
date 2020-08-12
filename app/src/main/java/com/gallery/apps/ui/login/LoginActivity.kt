package com.gallery.apps.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.gallery.apps.BR
import com.gallery.apps.R
import com.gallery.apps.ViewModelProviderFactory
import com.gallery.apps.databinding.ActivityLoginBinding
import com.gallery.apps.ui.base.BaseActivity
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding?, LoginViewModel>(), LoginNavigator {
    @kotlin.jvm.JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null
    private var mLoginViewModel: LoginViewModel? = null
    private var mActivityLoginBinding: ActivityLoginBinding? = null
    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_login

    override val viewModel: LoginViewModel
        get() {
            mLoginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
            return mLoginViewModel!!
        }

    override fun handleError(throwable: Throwable?) {
        System.out.println("ASDASDASDAS")
    }

    override fun login() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLoginBinding = viewDataBinding
        mLoginViewModel!!.setNavigator(this)
    }

    companion object {
        fun newIntent(context: Context?): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}