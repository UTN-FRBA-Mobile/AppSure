package com.utn.appsure.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.utn.appsure.R
import com.utn.appsure.model.Policy
import com.utn.appsure.usecase.CreatePolicyUseCase


class CreatePolicyViewModel2(private val createPolicyUseCase: CreatePolicyUseCase) : ViewModel() {
    val patent = ObservableField<String>()
    val brand = ObservableField<String>()
    val model = ObservableField<String>()
    val year = ObservableField<String>()
    val colour = ObservableField<String>()
    val finish = MutableLiveData(false)

    fun generatePolicy(c: Context) {
        createPolicyUseCase.execute(
            Policy(
                patent.get() ?: "",
                brand.get() ?: "",
                model.get() ?: "",
                Integer.parseInt(year.get() ?: "2020"),
                colour.get() ?: "", 0, 0.0, 0.0
            )
        ) {
            val builder = AlertDialog.Builder(c).setCancelable(false)
            it?.let {
                builder
                    .setTitle("Éxito")
                    .setMessage("La póliza fue creada, tiene un valor de $${it.value}")
            } ?: run {
                builder
                    .setTitle("Error")
                    .setMessage("Hubo un error, inténtelo más tarde.")
            }
            builder
                .setPositiveButton("Aceptar") { _, _ -> finish.value = true }
                .show()
        }
    }
}