package com.sevban.composetutorialssevbanbayir.plain_state_holder_exp

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times

class AnimatedButtonState(private val isLoading: Boolean) {
    val animator = Animatable(0f)
    var size = 96.dp

    suspend fun animateButtonSizeWhenLoading() {
        if (animator.value == 1f && !isLoading) {
            animator.animateTo(0f)
            size += animator.value * 96.dp
        }
        if (isLoading) {
            animator.animateTo(1f)
            size += animator.value * 96.dp
        }
    }
}

@Composable
fun rememberAnimatedButtonState(isLoading: Boolean) = remember(isLoading) {
    AnimatedButtonState(isLoading)
}