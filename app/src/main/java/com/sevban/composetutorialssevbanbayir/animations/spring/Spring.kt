package com.sevban.composetutorialssevbanbayir.animations.spring

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import com.sevban.composetutorialssevbanbayir.utils.getScreenSizePx

@Composable
fun SpringAnimationInspection() {
    var offsetY by remember {
        mutableStateOf(0f)
    }

    val screenSizePx = getScreenSizePx()
    val radius by remember {
        mutableStateOf(100f)
    }
    ballList.forEach {
        offsetY += 400f

        SpringBallGraph(
            ballData = it,
            radius = radius,
            springBallLocation = Offset(
                screenSizePx.screenWidth - radius,
                offsetY
            ),
        )
    }
}