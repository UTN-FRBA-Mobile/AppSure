package com.utn.appsure.service

import com.google.gson.Gson
import com.utn.appsure.model.Policy
import com.utn.appsure.model.PolicyValue
import okhttp3.OkHttpClient

class PolicyService(client: OkHttpClient) : BaseService(client) {
    fun getPolicies(): Array<Policy>? {
        endpoint = "/policy"
        val result = super.get()
        return if (!result.isNullOrEmpty()) Gson().fromJson(
            result,
            Array<Policy>::class.java
        ) else null
    }

    fun postPolicy(policy: Policy): PolicyValue? {
        endpoint = "/policy"
        val result = super.post(Gson().toJson(policy))
        return if (!result.isNullOrEmpty()) Gson().fromJson(
            result,
            PolicyValue::class.java
        ) else null
    }
}