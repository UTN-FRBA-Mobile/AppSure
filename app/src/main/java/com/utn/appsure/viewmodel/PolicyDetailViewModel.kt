package com.utn.appsure.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utn.appsure.R
import com.utn.appsure.model.Policy
import com.utn.appsure.usecase.GetPoliciesUseCase

class PolicyDetailViewModel (private var policyUseCase: GetPoliciesUseCase) : ViewModel() {
    val policy = MutableLiveData<Policy>()

    fun getPolicy(policyLicenseID : String){
        policyUseCase.execute(policyLicenseID) {policy}
    }

}
