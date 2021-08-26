package com.solvathon.domain.datasource

import com.solvathon.domain.pojo.ApiRequest
import com.solvathon.domain.pojo.DataWrapper
import com.solvathon.domain.pojo.Policy
import com.solvathon.domain.repo.PolicyRepository
import com.solvathon.domain.service.PolicyService
import io.reactivex.Single
import javax.inject.Inject

class PolicyLiveDataSource @Inject constructor(private val policyService: PolicyService) :
    BaseDataSource(), PolicyRepository {
    override fun observePolicies(): Single<DataWrapper<ArrayList<Policy>>> {
        TODO("Not yet implemented")
    }

    override fun getPolicies(apiRequest: ApiRequest): Single<DataWrapper<ArrayList<Policy>>> {
        return execute(policyService.getPolicies())
    }

    override fun refreshPolicies() {
        TODO("Not yet implemented")
    }

    override fun observePolicy(policyId: Long): Single<DataWrapper<ArrayList<Policy>>> {
        TODO("Not yet implemented")
    }

    override fun getPolicy(policyId: Long): Single<DataWrapper<Policy>> {
        TODO("Not yet implemented")
    }

    override fun refreshPolicy(policyId: Long) {
        TODO("Not yet implemented")
    }
}