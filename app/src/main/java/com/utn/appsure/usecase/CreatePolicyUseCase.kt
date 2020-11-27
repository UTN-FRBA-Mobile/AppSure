package com.utn.appsure.usecase

import android.util.Log
import com.utn.appsure.model.Policy
import com.utn.appsure.model.PolicyValue
import com.utn.appsure.service.PolicyService

class CreatePolicyUseCase (
    private val policyService: PolicyService
) : BaseUseCase<PolicyValue>() {
    override fun getData(value: Any?, callback: (PolicyValue?) -> Unit) {
        Log.i("prueba create policy", value.toString())
        val result = policyService.postPolicy(value as Policy)
        Log.i("prueba create policy", result.toString())
        onUiThread { callback(result) }
    }
}