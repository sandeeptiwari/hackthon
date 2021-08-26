package com.solvathon.ui.home

import com.solvathon.domain.pojo.ApiRequest
import com.solvathon.domain.pojo.DataWrapper
import com.solvathon.domain.pojo.Policy
import com.solvathon.domain.repo.PolicyRepository
import com.solvathon.ui.base.APILiveData
import com.solvathon.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val policyRepository: PolicyRepository
) : BaseViewModel() {

    val getPolicyLiveData = APILiveData<ArrayList<Policy>>()

    fun fetchPolicies(apiRequest: ApiRequest) {
        policyRepository.getPolicies(apiRequest).subscribe(withLiveData(getPolicyLiveData))
    }
}


