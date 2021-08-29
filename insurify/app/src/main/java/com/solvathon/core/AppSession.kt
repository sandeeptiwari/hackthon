package com.solvathon.core

import android.content.Context
import android.provider.Settings
import com.google.gson.Gson
import com.solvathon.domain.pojo.User
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AppSession @Inject
constructor(private var appPreferences: AppPreferences,
            private val context: Context) : Session {
    private val gson: Gson = Gson()

    override var user: User? = null
        get() {
            if (field == null) {
                val userJSON = appPreferences.getString(Session.USER_JSON)
                field = gson.fromJson(userJSON, User::class.java)
            }
            return field
        }
        set(value) {
            field = value
            val userJson = gson.toJson(value)
            if (userJson != null)
                appPreferences.putString(Session.USER_JSON, userJson)
        }

    var userLogin: Boolean
        get() = appPreferences.getBoolean(Session.USER_LOGIN)
        set(userLogin) = appPreferences.putBoolean(Session.USER_LOGIN, userLogin)

    override var userSession: String
        get() = appPreferences.getString(Session.USER_SESSION)
        set(userSession) = appPreferences.putString(Session.USER_SESSION, userSession)


    override var userId: String
        get() = appPreferences.getString(Session.USER_ID)
        set(userId) = appPreferences.putString(Session.USER_ID, userId)


    override/* open below comment after Firebase integration *///token = FirebaseInstanceId.getInstance().getToken();
    val deviceId: String
        get() {
            var token = appPreferences.getString(Common.DEVICE_TOKEN)

            return if (token.isNullOrEmpty()) {
                token =
                    Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                token
            } else {
                token
            }
        }

    override//  return StringUtils.equalsIgnoreCase(appPreferences.getString(Common.LANGUAGE), "ar") ? LANGUAGE_ARABIC : LANGUAGE_ENGLISH;
    val language: String
        get() = "en"


    override fun clearSession() {
        appPreferences.clearAll()
    }
}
