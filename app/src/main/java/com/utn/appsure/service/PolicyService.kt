package com.utn.appsure.service

import com.google.gson.Gson
import com.utn.appsure.model.Policy
import okhttp3.OkHttpClient

class PolicyService(client: OkHttpClient) : BaseService(client) {
    fun getPolicies(): Array<Policy> {
        endpoint = "/policy"
        val result = super.get()
        return Gson().fromJson(result, Array<Policy>::class.java)
    }
}