package com.sevban.composetutorialssevbanbayir.animations.spring

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun SpringBall(
    ballData: SpringBallData,
    radius: Float,
    springBallLocation: Offset,
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

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            color = Color(0xFF36A368),
            radius = radius,
            center = Offset(
                springBallLocation.x * offsetXMultiplier.value,
                springBallLocation.y
            ),
        )
    }
}

data class SpringBallData(
    val stiffness: Float = 0f,
    val dampingRatio: Float = 0f,
)

val ballList = listOf(
    //Gentle
    SpringBallData(
        stiffness = 100f,
        dampingRatio = .75f
    ),
    //Quick
    SpringBallData(
        stiffness = 300f,
        dampingRatio = .5773f
    ),
    //Bouncy
    SpringBallData(
        stiffness = 600f,
        dampingRatio = .306f
    ),
    //Slow
    SpringBallData(
        stiffness = 80f,
        dampingRatio = 1.18f
    ),
)