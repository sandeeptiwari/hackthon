package com.solvathon.domain.repo

import com.solvathon.domain.pojo.ApiRequest
import com.solvathon.domain.pojo.DataWrapper
import com.solvathon.domain.pojo.Policy
import io.reactivex.Single
import retrofit2.http.Body

interface PolicyRepository {

    fun observePolicies(): Single<DataWrapper<ArrayList<Policy>>>

    fun getPolicies(@Body requestData: ApiRequest): Single<DataWrapper<ArrayList<Policy>>>

    fun refreshPolicies()

    fun observePolicy(policyId: Long): Single<DataWrapper<ArrayList<Policy>>>

    fun getPolicy(policyId: Long): Single<DataWrapper<Policy>>

    fun refreshPolicy(policyId: Long)
}