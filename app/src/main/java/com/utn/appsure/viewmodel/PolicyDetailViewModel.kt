package com.utn.appsure.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utn.appsure.model.Policy
import com.utn.appsure.usecase.GetPolicyUseCase

class PolicyDetailViewModel(private var policyUseCase: GetPolicyUseCase) : ViewModel() {
    val patent = ObservableField<String>()
    val brand = ObservableField<String>()
    val model = ObservableField<String>()
    val year = ObservableField<String>()
    val colour = ObservableField<String>()

    fun getPolicy(policyLicenseID: String) {
        policyUseCase.execute(policyLicenseID) {
            patent.set("Patente: ${it?.license}")
            brand.set("Marca: ${it?.brand}")
            model.set("Modelo: ${it?.model}")
            year.set("Año: ${it?.year}")
            colour.set("Color: ${it?.colour}")
        }

    }

}
