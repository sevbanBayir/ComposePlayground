package com.sevban.composetutorialssevbanbayir.Canvas.customdarkmodeswitcher

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

val yellow = Color(0xFFFABB51)
val dark = Color(0xFF191919)
val light = Color(0xFFF7E8C3)

@Composable
fun DayNightSwitch() {

    var sh by remember {
        mutableStateOf(0f)
    }
    var sw by remember {
        mutableStateOf(0f)
    }

    var shouldDrag by remember {
        mutableStateOf(false)
    }

    var tipOffSet by remember {
        mutableStateOf(Offset(0f, 0f))
    }

    var animate by remember {
        mutableStateOf(true)
    }

    var animStartOffSet by remember {
        mutableStateOf(Offset(0f, 0f))
    }

    val animateRetract by animateOffsetAsState(
        targetValue = if (animate) tipOffSet else animStartOffSet,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )

    var day by remember {
        mutableStateOf(true)
    }

    val bgColor by animateColorAsState(
        targetValue = if(day) light else dark,
        animationSpec = tween(1000)
    )

    val circleColor by animateColorAsState(
        targetValue = if (day) yellow else Color.White,
        animationSpec = tween(1000)
    )

    val overLaySize by animateFloatAsState(
        targetValue = if(day) 0f else 0.2f * sh,
        animationSpec = tween(1000)
    )

    LaunchedEffect(sw, sh) {
        println("$tipOffSet")
        delay(300)
        tipOffSet = Offset(
            x = sw / 2,
            y = (sh / 2) + 300f
        )
        println("$tipOffSet")
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onDragEnd = {
                        if (shouldDrag) {
                            tipOffSet = Offset(
                                x = sw / 2,
                                y = (sh / 2) + 300f
                            )
                            animate = true
                            day = !day
                        }
                    },
                    onDragStart = {
                        //This block gives us the pointer offset when the related (vertical drag) gesture
                        //is detected.

                        //With this conditions, we will be ensure that the user put the pointer to a
                        //position that is close to our circle and line.

                        val condition1 = it.x > (sw / 2) - 20 && it.x < (sw / 2) + 20

                        val condition2 = it.y > ((sh / 2) + 300f) - 20

                        val condition3 = it.y < ((sh / 2) + 300f) + 20

                        shouldDrag = condition1 && condition2 && condition3
                    }
                ) { change, _ ->
                    if (shouldDrag) {
                        tipOffSet = Offset(
                            tipOffSet.x,
                            change.position.y
                        )
                        animStartOffSet = change.position
                    }
                }
            }
    ) {
        val height = size.height
        val width = size.width
        sh = size.height
        sw = size.width

        drawCircle(
            center = Offset(width / 2, height / 2),
            radius = (.2f * size.height).coerceAtMost(192f),
            color = circleColor
        )

        drawCircle(
            center = Offset((width / 2) - 40f, (height / 2) - 40f),
            radius = overLaySize,
            color = bgColor
        )

        drawLine(
            color = circleColor,
            start = Offset(width / 2, (height / 2) + (.2f * size.height)),
            end = animateRetract,
            strokeWidth = 10f,
        )

        drawCircle(
            color = circleColor,
            radius = 20f,
            center = animateRetract
        )
    }
}