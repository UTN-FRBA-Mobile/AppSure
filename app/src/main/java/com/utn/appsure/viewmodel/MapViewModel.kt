package com.utn.appsure.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utn.appsure.model.Policy
import com.utn.appsure.usecase.GetPoliciesUseCase
import com.utn.appsure.usecase.GetPolicyUseCase

class MapViewModel(private val policiesUseCase: GetPoliciesUseCase,private val policyUseCase: GetPolicyUseCase) : ViewModel() {
    val policies = MutableLiveData<List<Policy>>()

    fun getPolicies() {
        policiesUseCase.execute { policies.value = it?.toList() ?: listOf() }
    }

    fun getPolicy(license : String) {
        policyUseCase.execute (license) { it?.let { policies.value = listOf(it) }}
    }
}