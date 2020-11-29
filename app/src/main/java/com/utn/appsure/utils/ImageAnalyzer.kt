package com.utn.appsure.utils

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition

class ImageAnalyzer : ImageAnalysis.Analyzer, ImageCapture.OnImageCapturedCallback()  {

    var listener: ((String)->Unit)? = null //con esto puedo hacer el callback al RecognizeTextFragment

    @androidx.camera.core.ExperimentalGetImage  // esta linea es para que no tire error en imageProxy.image
    override fun analyze(imageProxy: ImageProxy) {

        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees) //preparo la imagen a procesar

            val recognizer = TextRecognition.getClient()

            recognizer.process(image)
                .addOnSuccessListener { visionText ->
                    //visionText tiene el resultado del TextRecognition
                    val resultText = visionText.text    //visionText.text devuelve el resultado total en un string, es decir une todos los bloques con su propio texto en uno solo


                    if(!resultText.isNullOrEmpty()){
                        if(resultText.length in 4..35){  //Esto es solo un detalle, para que me devuelva una cantidad "coherente" de letras que tiene una patente
                            listener?.invoke(resultText)
                        }
                    }else{
                       // listener?.invoke("No se pudo reconocer el texto") //esto es solo para probar el callback en el emulador, para que arroje un resultado aunque no se pueda leer un texto
                    }
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