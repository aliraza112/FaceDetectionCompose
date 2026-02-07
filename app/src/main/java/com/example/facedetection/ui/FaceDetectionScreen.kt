package com.example.facedetection.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.facedetection.camera.CameraAnalyzer
import com.example.facedetection.camera.CameraPermissionDenied
import com.example.facedetection.camera.CameraPreview
import com.example.facedetection.ml.FaceDetectorProcessor
import com.example.facedetection.utils.CameraPermissionHandler
import com.google.mlkit.vision.face.Face

@Composable
fun FaceDetectionScreen() {

    var faces by remember {
        mutableStateOf(emptyList<Face>())
    }

    val processor = remember {
        FaceDetectorProcessor()
    }

    val analyzer = remember {
        CameraAnalyzer(processor) {
            faces = it
        }
    }

    CameraPermissionHandler(
        onPermissionGranted = {
            Box {
                CameraPreview(analyzer)
                FaceOverlay(faces)
            }
        },
        onPermissionDenied = {
            CameraPermissionDenied()
        }
    )
}
