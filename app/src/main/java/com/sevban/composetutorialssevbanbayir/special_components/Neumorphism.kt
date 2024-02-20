package com.sevban.composetutorialssevbanbayir.special_components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

val gray100 = Color(0xffe5e5e5)
val gray200 = Color(0xffd0d0d0)

@Composable
fun Neumorphism() {

    var pressed by remember {
        mutableStateOf(false)
    }
    val offset by animateIntAsState(targetValue = if (pressed) 5 else 20)
    val blur by animateDpAsState(targetValue = if (pressed) 4.dp else 10.dp)
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        Modifier
            .fillMaxSize()
            .background(gray100),
        contentAlignment = Alignment.Center
    ) {

        Box(
            Modifier
                .size(100.dp)
                .offset { IntOffset(0, -offset) }
                .blur(blur, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                .background(Color.White, CircleShape)
        )

        Box(
            Modifier
                .size(100.dp)
                .offset { IntOffset(0, offset) }
                .blur(blur, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                .background(Color.Black.copy(alpha = .2f), CircleShape)
        )

        Box(
            Modifier
                .clickable(
                    indication = null,
                    interactionSource = interactionSource
                ) {
                    pressed = !pressed
                }
                .size(100.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            gray100,
                            gray200,
                        )
                    ),
                    shape = CircleShape
                )
                .border(
                    width = 1.dp,
                    shape = CircleShape,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White,
                            Color.Black.copy(alpha = .15f),
                        )
                    )
                )
        )
    }
}