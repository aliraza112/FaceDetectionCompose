package com.example.facedetection.camera

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.facedetection.ml.FaceDetectorProcessor
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face

class CameraAnalyzer(
    private val processor: FaceDetectorProcessor,
    private val onFacesDetected: (List<Face>) -> Unit
) : ImageAnalysis.Analyzer {

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image ?: return

        val inputImage = InputImage.fromMediaImage(
            mediaImage,
            imageProxy.imageInfo.rotationDegrees
        )

        processor.process(inputImage) { faces ->
            onFacesDetected(faces)
            imageProxy.close()
        }
    }
}