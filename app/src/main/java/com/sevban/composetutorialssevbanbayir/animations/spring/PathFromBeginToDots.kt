package com.sevban.composetutorialssevbanbayir.animations.spring

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect

@Composable
fun PathFromBeginToDots(offsetY: Float, offsetX: Float) {
    val path = remember {
        Path()
    }
    val animateY = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        animateY.animateTo(
            targetValue = 1f,
            animationSpec = tween(4000)
        )
    }
    path.apply {
        moveTo(150f, 1000f)
        if (offsetY < 1000f)
            cubicTo(offsetX / 3, offsetY / 3, offsetX / 2, 2 * (offsetY / 2), offsetX, offsetY)
        if (offsetY > 1000f)
            cubicTo(offsetX / 3, 3 * (offsetY / 3), offsetX / 2, 2 * (offsetY / 2), offsetX, offsetY)
    }
    Canvas(modifier = Modifier.fillMaxSize()) {
        clipRect(right = offsetX * animateY.value) {
            drawPath(
                path,
                color = Color(0xFF1565C0),
                style = Stroke(8f)
            )
        }
    }
}