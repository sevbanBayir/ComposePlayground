package com.sevban.composetutorialssevbanbayir.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun SpringAnimationInspection() {
    var offsetY by remember {
        mutableStateOf(0f)
    }

    ballList.forEach {
        println(it.stiffness)
        offsetY += 400f
        SpringBall(ballData = it, offsetY = offsetY)
    }
}

@Composable
fun SpringBall(
    ballData: SpringBall,
    offsetY: Float
) {
    val offsetXMultiplier = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        offsetXMultiplier.animateTo(
            1f,
            animationSpec = spring(
                dampingRatio = ballData.dampingRatio,
                stiffness = ballData.stiffness
            )
        )
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color(0xFF36A368),
                radius = 100f,
                center = Offset(950f * offsetXMultiplier.value, offsetY),
            )
        }
    }
}

data class SpringBall(
    val stiffness: Float = 0f,
    val dampingRatio: Float = 0f,
)

val ballList = listOf(
    //Gentle
    SpringBall(
        stiffness = 100f,
        dampingRatio = .75f
    ),
    //Quick
    SpringBall(
        stiffness = 300f,
        dampingRatio = .5773f
    ),
    //Bouncy
    SpringBall(
        stiffness = 600f,
        dampingRatio = .306f
    ),
    //Slow
    SpringBall(
        stiffness = 80f,
        dampingRatio = 1.18f
    ),
)

