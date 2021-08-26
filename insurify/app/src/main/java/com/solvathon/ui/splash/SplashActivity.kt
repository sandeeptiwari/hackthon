package com.solvathon.ui.splash

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.solvathon.R
import com.solvathon.core.Common
import com.solvathon.databinding.ActivitySplashBinding
import com.solvathon.ui.base.BaseActivity
import com.solvathon.ui.claims.ClaimActivity
import com.solvathon.ui.home.HomeActivity
import com.solvathon.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    lateinit var binding: ActivitySplashBinding

    private val runnable = Runnable {

        if (appPreference.getBoolean(Common.IS_LOGIN)) {
            loadActivity(HomeActivity::class.java)
                .byFinishingAll()
                .start()
        } else {
            Log.i("LOGIN", "User is not logined open login screen")
            loadActivity(ClaimActivity::class.java)
                .byFinishingAll()
                .start()
        }
    }

    companion object {
        const val TIME_OUT = 1000L
    }

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars());
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
            )
        }

        val splashAnimation = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.anim_splash)
        binding.tvAppName.animation = splashAnimation

        splashAnimation.setAnimationListener(SplashAnimation())
    }

    override fun bindViewWithViewBinding(view: View) {
        binding = ActivitySplashBinding.bind(view)
    }

    override fun findContentView(): Int {
        return R.layout.activity_splash
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }

    private inner class SplashAnimation : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation?) {
            handler.postDelayed(runnable, TIME_OUT)
        }

        override fun onAnimationRepeat(animation: Animation?) {
        }

    }
}