package com.utn.appsure.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utn.appsure.model.Policy
import com.utn.appsure.usecase.GetPoliciesUseCase

class PolicyDetailViewModel (private val policiesUseCase: GetPoliciesUseCase) : ViewModel() {
    val policies = MutableLiveData<List<Policy>>()

    fun getPolicies() {
        policiesUseCase.execute { policies.value = it?.toList() ?: listOf() }
    }
}