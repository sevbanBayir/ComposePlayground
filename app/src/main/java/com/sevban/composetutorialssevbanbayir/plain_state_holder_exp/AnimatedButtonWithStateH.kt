package com.sevban.composetutorialssevbanbayir.plain_state_holder_exp

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@Composable
fun AnimatedButtonWithStateH() {

    var isLoading by remember { mutableStateOf(false) }
    val animator = remember {
        Animatable(0f)
    }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = isLoading) {
        if (animator.value == 1f && !isLoading)
            animator.animateTo(0f)
        if (isLoading)
            animator.animateTo(1f)
    }

    ElevatedButton(
        onClick = {

            coroutineScope.launch {
                delay(3.seconds)
                isLoading = true
                delay(3.seconds)
                isLoading = false
            }
        },
        shape = RoundedCornerShape(36.dp),
        modifier = Modifier.size(
             48.dp * animator.value + 96.dp
        ).animateContentSize { initialValue, targetValue ->
        }
    ) {
        if (isLoading)
            CircularProgressIndicator()
        else
            Text(text = "Log In")
    }
}