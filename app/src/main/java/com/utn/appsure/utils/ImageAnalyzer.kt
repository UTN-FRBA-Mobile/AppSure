package com.utn.appsure.utils

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.utn.appsure.fragment.RecognizeTextFragment
import kotlinx.android.synthetic.main.fragment_recognize_text.*

class ImageAnalyzer (fragment: RecognizeTextFragment) : ImageAnalysis.Analyzer, ImageCapture.OnImageCapturedCallback()  {

    private val recognizeTextFragment : RecognizeTextFragment = fragment  // de esta manera puedo llamar al fragment para que muestre en pantalla el resultado del reconocimiento del texto. No se si es lindo hacer esto, pero funciona

    @androidx.camera.core.ExperimentalGetImage  // esta linea es para que no tire error en imageProxy.image
    override fun analyze(imageProxy: ImageProxy) {

        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees) //preparo la imagen a procesar

            val recognizer = TextRecognition.getClient()

            val result = recognizer.process(image)
                .addOnSuccessListener { visionText ->
                    //visionText tiene el resultado del TextRecognition
                    val resultText = visionText.text    //visionText.text devuelve el resultado total en un string, es decir une todos los bloques con su propio texto en uno solo

                    recognizeTextFragment.resultImageAnalizer.text = resultText   //supuestamente el resultado ya se puede ver en el layout

                }
                .addOnFailureListener { e ->
                    //TODO hacer algo con el error
                }
                .addOnCompleteListener {
                    //Es necesario cerrar el imageProxy porque sino hay un fallo y deja de funcionar el TextRecognition
                    imageProxy.close()
                }


        }

    }

    companion object{
        fun newInstance() = ImageAnalyzer
    }
}