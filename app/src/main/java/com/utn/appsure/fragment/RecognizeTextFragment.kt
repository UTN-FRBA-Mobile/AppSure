package com.utn.appsure.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.utn.appsure.databinding.FragmentRecognizeTextBinding
import com.utn.appsure.utils.ImageAnalyzer
import com.utn.appsure.viewmodel.CreatePolicyViewModel2
import com.utn.appsure.viewmodel.RecognizeTextViewModel
import kotlinx.android.synthetic.main.fragment_recognize_text.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class RecognizeTextFragment : Fragment() {

    //private val viewModel by viewModel<RecognizeTextViewModel>()
    private val viewModel: CreatePolicyViewModel2 by activityViewModels()
    private lateinit var binding: FragmentRecognizeTextBinding


    private lateinit var cameraExecutor: ExecutorService
    //para ejecutar el codigo de leer texto en imagen
    private var imageAnalyzer: ImageAnalysis? = null
    private val recognizeText: ImageAnalyzer = ImageAnalyzer()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRecognizeTextBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            requestPermissions(
                REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }

        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    override fun onStart() {
        super.onStart()
        recognizeText.listener={text->
            viewModel.changeResult(text)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(this.context,
                    "Camera permissions not granted by the user.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            this.requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }



    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this.requireContext())

        cameraProviderFuture.addListener(Runnable {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(viewFinder.createSurfaceProvider())
                }

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            //esta parte es para lo de leer texto, lo de IA
            imageAnalyzer = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST) //STRATEGY_KEEP_ONLY_LATEST es para que sea mas performante, y no realice bloqueo
                .build()
                .also{
                    it.setAnalyzer(cameraExecutor, recognizeText)
                }


            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageAnalyzer)   // de esta maner ejecuta el codigo analyze del ImageAnalyzer

            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this.context))
    }




    companion object{
        private const val TAG = "CameraXBasic"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        fun newInstance() = RecognizeTextFragment()
    }

}