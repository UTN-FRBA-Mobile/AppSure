package com.utn.appsure.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utn.appsure.usecase.GetPoliciesUseCase

class MainListViewModel(private val policiesUseCase: GetPoliciesUseCase) : ViewModel() {
    var holiwis = MutableLiveData("HOLIWISSS")

    fun getPruebaData() {
        policiesUseCase.execute {
            holiwis.value = it.toString()
        }
    }
}