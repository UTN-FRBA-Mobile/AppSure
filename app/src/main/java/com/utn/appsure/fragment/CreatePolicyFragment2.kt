package com.utn.appsure.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.utn.appsure.databinding.FragmentCreatePolicy2Binding
import com.utn.appsure.viewmodel.CreatePolicyViewModel2
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatePolicyFragment2 : Fragment() {

    private val viewModel by viewModel<CreatePolicyViewModel2>()
    private lateinit var binding: FragmentCreatePolicy2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCreatePolicy2Binding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        viewModel.finish.observe(viewLifecycleOwner, Observer {
            if (it) activity?.finish()
        })
        binding.btnGeneratePolicy.setOnClickListener { createBiometricPrompt() }
        return binding.root
    }

    private fun createBiometricPrompt() {
        val biometricPrompt = activity?.let {
            BiometricPrompt(
                it, ContextCompat.getMainExecutor(it),
                object : BiometricPrompt.AuthenticationCallback() {

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        viewModel.generatePolicy(it)
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        Toast.makeText(
                            context, "Falló la autenticación :(",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                })
        }

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Verifique su identidad")
            .setSubtitle("Para crear la póliza es necesario que ingrese su huella")
            .setNegativeButtonText("Cancelar")
            .build()

        biometricPrompt?.authenticate(promptInfo)
    }
}