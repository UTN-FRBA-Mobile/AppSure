package com.utn.appsure.usecase

import com.utn.appsure.db.PolicyRepository
import com.utn.appsure.model.Policy
import com.utn.appsure.service.PolicyService

class GetPoliciesUseCase(
    private val policyService: PolicyService,
    private val policyRepository: PolicyRepository
) : BaseUseCase<Array<Policy>>() {
    override fun getData(callback: (Array<Policy>?) -> Unit) {
        var policies = policyService.getPolicies()
        if (policies == null) {
            policies = policyRepository.getAll().toTypedArray()
        } else {
            policyRepository.insertAll(policies)
        }
        onUiThread {
            callback(policies)
        }
    }
}