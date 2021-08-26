package com.solvathon.ui.base

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.solvathon.core.AppPreferences
import com.solvathon.ui.home.HomeViewModel
import javax.inject.Inject

abstract class BaseFragment : Fragment() {
    companion object {
        const val TAG = "BaseFragment"
    }

    val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var appPreferences: AppPreferences

    fun onError(throwable: Throwable) {
        throwable.message?.let { Log.i(TAG, it) }
    }

    fun toggleLoader(isLoader: Boolean) {}

    abstract fun showLoader(b: Boolean)
}