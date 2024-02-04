package com.sevban.composetutorialssevbanbayir.animations.spring

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset

@Composable
fun SpringBallGraph(
    ballData: SpringBallData,
    radius: Float,
    springBallLocation: Offset,
) {
    val offsetXMultiplier = remember {
        Animatable(0f)
    }

    var isSmallDotsVisible by remember {
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
        isSmallDotsVisible = !offsetXMultiplier.isRunning
    }

    BeginCircle()
    SpringBall(ballData, radius, springBallLocation)
    AnimatedVisibility(visible = isSmallDotsVisible) {
        SmallDot(offsetX = springBallLocation.x - radius, offsetY = springBallLocation.y)
        PathFromBeginToDots(
            offsetX = springBallLocation.x - radius,
            offsetY = springBallLocation.y
        )
    }
}