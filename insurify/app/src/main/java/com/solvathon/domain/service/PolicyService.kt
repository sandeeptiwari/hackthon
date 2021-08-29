package com.solvathon.domain.service

import com.solvathon.core.URLFactory
import com.solvathon.domain.pojo.Policy
import com.solvathon.domain.pojo.ResponseBody
import io.reactivex.Single
import retrofit2.http.GET
import java.util.ArrayList

interface PolicyService {

    @GET(URLFactory.Method.GET_POLICY_LIST)
    fun getPolicies(): Single<ResponseBody<ArrayList<Policy>>>
}