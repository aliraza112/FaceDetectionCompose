package com.example.facedetection.ui

import android.graphics.Rect
import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import com.google.mlkit.vision.face.Face

@Composable
fun FaceOverlay(
    faces: List<Face>
) {
    Canvas(
        modifier = Modifier.fillMaxSize()

    ) {
        faces.forEach { face ->
            val rect = mapRect(
                face.boundingBox,
                size.width,
                size.height,
                size.width,
                size.height
            )

            drawRect(
                color = Color.Green,
                topLeft = Offset(rect.left, rect.top),
                size = Size(rect.width(), rect.height()),
                style = Stroke(width = 4f)
            )
        }
    }
}

fun mapRect(
    boundingBox: Rect,
    imageWidth: Float,
    imageHeight: Float,
    previewWidth: Float,
    previewHeight: Float
): RectF {

    val scaleX = previewWidth / imageWidth
    val scaleY = previewHeight / imageHeight

    return RectF(
        boundingBox.left * scaleX,
        boundingBox.top * scaleY,
        boundingBox.right * scaleX,
        boundingBox.bottom * scaleY
    )
}
