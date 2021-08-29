package com.solvathon.domain.service

import com.solvathon.core.URLFactory
import com.solvathon.domain.pojo.ApiRequest
import com.solvathon.domain.pojo.ResponseBody
import com.solvathon.domain.pojo.User
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {
    @POST(URLFactory.Method.LOGIN)
    fun logIn(@Body apiRequest: ApiRequest): Single<ResponseBody<User>>
}