package com.utn.appsure.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utn.appsure.model.Policy
import com.utn.appsure.usecase.GetPolicyUseCase

class PolicyDetailViewModel(private var policyUseCase: GetPolicyUseCase) : ViewModel() {
    var patent = ObservableField<String>()
    val brand = ObservableField<String>()
    val model = ObservableField<String>()
    val year = ObservableField<String>()
    val colour = ObservableField<String>()

    fun getPolicy(policyLicenseID: String) {
        policyUseCase.execute(policyLicenseID) {
            Policy(
                patent.get() ?: it?.license.toString(),
                brand.get() ?: it?.brand.toString(),
                model.get() ?: it?.model.toString(),
                Integer.parseInt(year.get() ?: "2020"),
                colour.get() ?: it?.colour.toString(), 0, 0.0, 0.0
            )
            patent = ObservableField("TEST")
        }

    }

}
