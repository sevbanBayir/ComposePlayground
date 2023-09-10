package com.sevban.composetutorialssevbanbayir.Canvas.AppIconsExerciseWithCanvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun InstagramIcon() {
    val instaColors = listOf(Color.Yellow, Color.Red, Color.Magenta)
    Canvas(
        modifier = Modifier
            .size(400.dp)
            .padding(26.dp)
    ) {
        drawRoundRect(
            brush = Brush.linearGradient(colors = instaColors),
            cornerRadius = CornerRadius(160f, 160f),
            style = Stroke(
                width = 15f * size.height * .0075f,
                cap = StrokeCap.Round
            )
        )
        drawCircle(
            brush = Brush.linearGradient(colors = instaColors),
            radius = 45f * size.height * .0045f,
            style = Stroke(width = 15f * size.height * .006f, cap = StrokeCap.Round)
        )
        drawCircle(
            brush = Brush.linearGradient(colors = instaColors),
            radius = 13f * size.height * .0075f,
            center = Offset(size.width * .80f, size.height * 0.20f),
        )
    }
}