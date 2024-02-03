package com.sevban.composetutorialssevbanbayir.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect

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

    var smallDotsVisible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = true) {

        offsetXMultiplier.animateTo(
            1f,
            animationSpec = spring(
                dampingRatio = ballData.dampingRatio,
                stiffness = ballData.stiffness
            )
        )

        smallDotsVisible = !offsetXMultiplier.isRunning

    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        BeginCircle()
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color(0xFF36A368),
                radius = 100f,
                center = Offset(950f * offsetXMultiplier.value, offsetY),
            )
        }
        AnimatedVisibility(visible = smallDotsVisible) {
            SmallDot(offsetY = offsetY)
            PathFromBeginToDots(offsetY)
        }
    }
}

@Composable
fun SmallDot(offsetY: Float) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            color = Color(0xFF4527A0),
            radius = 10f,
            center = Offset(850f, offsetY),
        )
    }
}

@Composable
fun BeginCircle() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            color = Color(0xFF4527A0),
            radius = 50f,
            center = Offset(100f, 1000f),
        )
    }
}

@Composable
fun PathFromBeginToDots(offsetY: Float) {
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
            cubicTo(850f / 3, offsetY / 3, 850f / 2, 2 * (offsetY / 2), 850f, offsetY)
        if (offsetY > 1000f)
            cubicTo(850f / 3, 3 * (offsetY / 3), 850f / 2, 2 * (offsetY / 2), 850f, offsetY)
    }
    Canvas(modifier = Modifier.fillMaxSize()) {
        clipRect(right = 850f * animateY.value) {
            drawPath(
                path,
                color = Color(0xFF1565C0),
                style = Stroke(8f)
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

