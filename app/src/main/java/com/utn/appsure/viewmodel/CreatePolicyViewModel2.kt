package com.utn.appsure.viewmodel

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.utn.appsure.model.Policy
import com.utn.appsure.usecase.CreatePolicyUseCase


class CreatePolicyViewModel2(private val createPolicyUseCase: CreatePolicyUseCase) : ViewModel() {
    var createPolicyText = "Crear Póliza"
    var generatePolicy = "Generar Póliza"
    val patent = ObservableField<String>()
    val brand = ObservableField<String>()
    val model = ObservableField<String>()
    val year = ObservableField<String>()
    val colour = ObservableField<String>()

    fun generatePolicy(v: View) {
        createPolicyUseCase.execute (Policy(patent.get() ?: "",
            brand.get() ?: "",
            model.get() ?: "",
            Integer.parseInt(year.get() ?: "2020") ,
        colour.get() ?: "", 0,0.0,0.0)){}
    }

}