package com.solvathon.core

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferences @Inject
constructor(private val preferences: SharedPreferences) {

    @SuppressLint("CommitPrefEdits")
    fun putBoolean(name: String, value: Boolean) {
        val editor = preferences.edit()
        editor!!.putBoolean(name, value)
        editor.apply()
    }

    fun getBoolean(name: String): Boolean {
        return preferences.getBoolean(name, false)
    }

    fun getString(userJson: String): String {
        TODO("Not yet implemented")
    }

    fun putString(userJson: String, userJson1: String) {
        TODO("Not yet implemented")
    }

    fun clearAll() {
    }
}