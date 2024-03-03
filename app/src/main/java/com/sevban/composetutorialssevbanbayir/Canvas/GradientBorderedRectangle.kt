package com.sevban.composetutorialssevbanbayir.Canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun GradientBorderedRectangle() {
    val gradient = Brush.horizontalGradient(listOf(Color.Green, Color.Blue, Color.Black))

    val path = Path()
    path.addRect(
        rect = Rect(
            Offset(0f, 0f),
            Size(1000f, 1000f)
        )
    )

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        drawPath(
            path = path,
            brush = gradient,
            style = Stroke(10f)
        )
    }
}

@Preview
@Composable
fun PreviewGradientBorderedRectangle() {
    GradientBorderedRectangle()
}
