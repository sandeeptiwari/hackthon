package com.solvathon.core

import com.solvathon.domain.pojo.User

interface Session {

    var apiKey: String

    var userSession: String

    var userId: String

    val deviceId: String

    var user: User?

    val language: String

    fun clearSession()

    companion object {

        const val API_KEY = "api_key"
        const val USER_SESSION = "authorization"
        const val USER_LOGIN = "LOGIN"
        const val USER_ID = "USER_ID"
        const val DEVICE_TYPE = "A"
        const val APP = "app"
        var APP_VALUE: String = "U"

    }
}

