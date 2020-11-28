package com.utn.appsure.usecase

import com.utn.appsure.db.PolicyRepository
import com.utn.appsure.model.Policy
import com.utn.appsure.model.PolicyValue
import com.utn.appsure.service.PolicyService

class CreatePolicyUseCase(
    private val policyService: PolicyService,
    private val policyRepository: PolicyRepository
) : BaseUseCase<PolicyValue>() {
    override fun getData(value: Any?, callback: (PolicyValue?) -> Unit) {
        val result = policyService.postPolicy(value as Policy)
        result?.let {
            policyRepository.insert(value)
            onUiThread { callback(result) }
        } ?: run {
            onUiThread { callback(null) }
        }
    }
}