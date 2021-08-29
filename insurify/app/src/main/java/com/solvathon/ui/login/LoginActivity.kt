package com.solvathon.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.solvathon.MainActivity
import com.solvathon.R
import com.solvathon.Util.AppUtil
import com.solvathon.core.AppSession
import com.solvathon.core.Common
import com.solvathon.databinding.ActivityLoginBinding
import com.solvathon.domain.pojo.ApiRequest
import com.solvathon.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    companion object {
        val TAG: String = LoginActivity::class.java.simpleName
    }

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeSigninResposnse()
        binding.btnLogin.setOnClickListener {
            onSignInClick(it)
        }
    }

    override fun findContentView(): Int {
        return R.layout.activity_login
    }

    override fun bindViewWithViewBinding(view: View) {
        binding = ActivityLoginBinding.bind(view)
    }

    fun onSignInClick(view: View) {
        val username = binding.userName.text.toString()
        val password = binding.password.text.toString()
        Log.v(TAG, "Received Username: $username & Password: $password")

        if (username.isEmpty() || password.isEmpty()) {
            Log.v(TAG, "Username or Password not provided.")
            showSnackBar(
                findViewById(R.id.myCoordinatorLayout),
                R.string.username_password_is_blank,
            )
        } else {
            if (AppUtil.isNetworkConnected(this)) {
                callLoginApi()
            }
        }
    }

    private fun callLoginApi() {
        val apiRequest = ApiRequest()
        apiRequest.username = binding.userName.text.toString()
        apiRequest.password = binding.password.text.toString()
        showLoader(true)
        val session = AppSession(appPreference, this);
        authViewModel.logInWs(apiRequest, session)
    }

    private fun observeSigninResposnse() {
        authViewModel.logInLiveData.observe(this,
            { responseBody ->
                showLoader(false)
                Log.i(TAG, responseBody.data.toString())
                loadActivity(MainActivity::class.java)
                    .byFinishingAll()
                    .start()
                appPreference.putBoolean(Common.IS_LOGIN, true)
            }
        ) { throwable ->
            showLoader(false)
            true
        }
    }
}