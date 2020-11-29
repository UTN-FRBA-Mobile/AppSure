package com.utn.appsure.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.utn.appsure.R
import com.utn.appsure.utils.ImageAnalyzer
import kotlinx.android.synthetic.main.activity_recognize_text.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class RecognizeTextActivity : AppCompatActivity() {


    private lateinit var cameraExecutor: ExecutorService
    //para ejecutar el codigo de leer texto en imagen
    private var imageAnalyzer: ImageAnalysis? = null
    private val recognizeText: ImageAnalyzer = ImageAnalyzer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recognize_text)

        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            requestPermissions(
                REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }

        cameraExecutor = Executors.newSingleThreadExecutor()

        btnAcceptResult.setOnClickListener { respondIntent(resultImageAnalizer.text.toString()) }

    }




    override fun onStart() {
        super.onStart()
        recognizeText.listener={ text->
            resultImageAnalizer.text = text
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(
                    this,
                    "Camera permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            this, it
        ) == PackageManager.PERMISSION_GRANTED
    }



    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

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
                .also {
                    it.setAnalyzer(cameraExecutor, recognizeText)
                }


            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageAnalyzer
                )   // de esta maner ejecuta el codigo analyze del ImageAnalyzer

            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }


    private fun respondIntent(result: String){
        val returnIntent = Intent()
        returnIntent.putExtra("result", result)
        setResult(RESULT_OK, returnIntent)
        finish()
    }


    companion object{
        private const val TAG = "CameraXBasic"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        fun newInstance() = RecognizeTextActivity()
    }

}