package com.solvathon.core

import okhttp3.HttpUrl

object URLFactory {

    private const val IS_LIVE = false
    private val SCHEME = if (IS_LIVE) "http" else "http"
    private val HOST = if (IS_LIVE) "xyz" else "192.168.0.104"
    private val PORT = if (IS_LIVE) 8506 else 8080
    private val API_PATH = if (IS_LIVE) "v1/api/" else "v1/api/"

    fun provideHttpUrl(): HttpUrl {
        return HttpUrl.Builder()
            .scheme(SCHEME)
            .port(PORT)
            .host(HOST)
            .addPathSegments(API_PATH)
            .build()
    }

    object Method {
        const val GET_POLICY_LIST = "policy/"
        const val LOGIN = "login/"
    }
}