package com.sevban.composetutorialssevbanbayir.animations.spring

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun SmallDot(offsetY: Float, offsetX: Float) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            color = Color(0xFF4527A0),
            radius = 10f,
            center = Offset(offsetX, offsetY),
        )
    }
}