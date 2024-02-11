package com.sevban.composetutorialssevbanbayir.State_Compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StateExp() {
    var text by remember {
        mutableStateOf("")
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val interactionSource2 = remember {
        MutableInteractionSource()
    }
    val focused by interactionSource.collectIsFocusedAsState()
    val focused2 by interactionSource2.collectIsFocusedAsState()

    val focusedPlaceholderColor = Color.Blue
    val unfocusedPlaceholderColor = Color.Red

    val animatedColor by animateColorAsState(
        targetValue = if (focused) focusedPlaceholderColor else unfocusedPlaceholderColor,
        animationSpec = tween(durationMillis = 2000), label = ""
    )
    val animatedColor2 by animateColorAsState(
        targetValue = if (focused2) focusedPlaceholderColor else unfocusedPlaceholderColor,
        animationSpec = tween(durationMillis = 2000), label = ""
    )

    Column {
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text(text = "Enter your name", color = animatedColor) },
            interactionSource = interactionSource
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text(text = "Enter your name", color = animatedColor2) },
            interactionSource = interactionSource2
        )
    }
}