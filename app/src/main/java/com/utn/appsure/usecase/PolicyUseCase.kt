package com.utn.appsure.usecase

import androidx.lifecycle.MutableLiveData
import com.utn.appsure.model.Policy
import com.utn.appsure.service.PolicyService

class GetPoliciesUseCase(private val policyService: PolicyService) : BaseUseCase<Array<Policy>>() {
    override fun getData(liveData: MutableLiveData<Array<Policy>>) {
        liveData.postValue(policyService.getPolicies())
    }
}