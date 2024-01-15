package com.sevban.composetutorialssevbanbayir.plain_state_holder_exp

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

@Composable
fun AnimatedButtonWithStateH() {

    var isLoading by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    val animatedButtonState = rememberAnimatedButtonState(isLoading)

    LaunchedEffect(key1 = isLoading) {
        animatedButtonState.animateButtonSizeWhenLoading()
    }

    ElevatedButton(
        onClick = {

            coroutineScope.launch {
                delay(1.seconds)
                isLoading = true
                delay(6.seconds)
                isLoading = false
            }
        },
        shape = RoundedCornerShape(36.dp),
        modifier = Modifier.size(animatedButtonState.animator.value * 96.dp + 96.dp).background(color = Color(Random.nextInt()))
    ) {
        if (isLoading)
            CircularProgressIndicator()
        else
            Text(text = "Log In")
    }
}