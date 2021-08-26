package com.solvathon.di

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class InsurifyApp : Application() {

    companion object {
        var isActive = false
        var mContext: Context? = null
        var token: String = ""

    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }
}