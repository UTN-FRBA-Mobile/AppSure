package com.utn.appsure.usecase

import com.utn.appsure.model.Policy
import com.utn.appsure.service.PolicyService

class GetPoliciesUseCase(private val policyService: PolicyService) : BaseUseCase<Array<Policy>>() {
    override fun getData(callback: (Array<Policy>) -> Unit) {
        val policies = policyService.getPolicies()
        onUiThread {
            callback(policies)
        }
    }
}