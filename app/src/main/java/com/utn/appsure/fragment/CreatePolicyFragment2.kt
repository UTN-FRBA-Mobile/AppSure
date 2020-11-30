package com.utn.appsure.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.utn.appsure.activity.RecognizeTextActivity
import com.utn.appsure.databinding.FragmentCreatePolicy2Binding
import com.utn.appsure.viewmodel.CreatePolicyViewModel2
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class CreatePolicyFragment2 : Fragment() {

    private val viewModel by viewModel<CreatePolicyViewModel2>()
    private lateinit var binding: FragmentCreatePolicy2Binding
    private val LAUNCH_RECOGNIZE_TEXT = 555

    private var mFusedLocationClient: FusedLocationProviderClient? = null

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
        binding.btnRcognizeText.setOnClickListener{starRecognizeText()}

        activity?.let {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(it)
        }

        getLastLocation()

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

    private fun starRecognizeText(){
        val intent = Intent(this.context, RecognizeTextActivity::class.java)
        startActivityForResult(intent,LAUNCH_RECOGNIZE_TEXT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==LAUNCH_RECOGNIZE_TEXT){
            if(resultCode== Activity.RESULT_OK){
                var recognizeTextResult = data?.getStringExtra("result")
                if(!recognizeTextResult.isNullOrEmpty()){
                    viewModel.patent.set(recognizeTextResult)
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        activity?.let{
            mFusedLocationClient!!.lastLocation
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful && task.result != null) {
                        var mLastLocation = task.result

                        val lat = (mLastLocation )!!.latitude
                        val lon = (mLastLocation )!!.longitude

                        viewModel.lat.value = lat
                        viewModel.lon.value = lon
                    }
                }
        }

    }

}