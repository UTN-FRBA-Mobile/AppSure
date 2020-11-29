package com.utn.appsure.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utn.appsure.model.Policy
import com.utn.appsure.usecase.GetPolicyUseCase

class PolicyDetailViewModel(private var policyUseCase: GetPolicyUseCase) : ViewModel() {
    var patent = ObservableField<String>()
    var brand = ObservableField<String>()
    var model = ObservableField<String>()
    var year = ObservableField<String>()
    var colour = ObservableField<String>()

    fun getPolicy(policyLicenseID: String) {
        policyUseCase.execute(policyLicenseID) {
            patent = ObservableField(it?.license.toString())
            brand = ObservableField(it?.brand.toString())
            model = ObservableField(it?.model.toString())
            year = ObservableField(it?.year.toString())
            colour = ObservableField(it?.colour.toString())
        }

    }

}
