package com.sevban.composetutorialssevbanbayir.Canvas.customdarkmodeswitcher

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

private val yellow1 = Color(0xFFFABB51)
private val dark1 = Color(0xFF191919)
private val light1 = Color(0xFFF7E8C3)

@Composable
fun CustomDarkModeSwithcer(
    modifier: Modifier = Modifier,
) {
    val animatable = remember { Animatable(0f) }

    val center = remember { Offset.Zero }

    LaunchedEffect(key1 = true) {
        animatable.animateTo(1f, animationSpec = tween(5000))
    }
    Box(Modifier.fillMaxSize().background(color = light1), contentAlignment = Alignment.Center) {
        Canvas(modifier = modifier.size(200.dp)) {

            val height = size.height
            val width = size.width

            drawCircle(
                center = Offset(width / 2, height / 2),
                radius = 140f,
                color = yellow1
            )

            drawCircle(
                center = Offset((width / 2) - 40f, (height / 2) - 40f),
                radius = animatable.value * 140f,
                color = light1
            )
        }
    }
}