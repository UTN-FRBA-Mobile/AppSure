package com.utn.appsure.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.utn.appsure.R


class CreatePolicyViewModel : ViewModel() {
    var createPolicyText = "Crear Póliza"
    var nextPageText = "Siguiente"

    fun goToNextPage(v: View) {
        v.findNavController().navigate(R.id.action_go_to_next_page)
    }

}