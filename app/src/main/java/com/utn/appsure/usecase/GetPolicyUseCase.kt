package com.utn.appsure.usecase

import com.utn.appsure.db.PolicyRepository
import com.utn.appsure.model.Policy
import com.utn.appsure.service.PolicyService

class GetPolicyUseCase (
    private val policyRepository: PolicyRepository
) : BaseUseCase<Policy>() {
    override fun getData(value: Any?, callback: (Policy?) -> Unit) {
        val policy = policyRepository.getById(value as String)
        onUiThread { callback(policy) }
    }
}