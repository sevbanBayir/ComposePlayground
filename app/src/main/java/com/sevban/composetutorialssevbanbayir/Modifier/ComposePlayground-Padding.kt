package com.sevban.composetutorialssevbanbayir.Modifier

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PaddingExercise1() {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .border(4.dp, Color.LightGray, RoundedCornerShape(80.dp))
            .clip(RoundedCornerShape(80.dp))
            .padding(0.dp)
            .clickable { }
    ) {

    }
}

@Composable
fun PaddingExercise2() {
    Box(
        modifier = Modifier

            .border(4.dp, Color.LightGray, RoundedCornerShape(24.dp))
            .padding(16.dp)
            .clickable { }
    ) {

    }
}

@Composable
fun PaddingExercise3() {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .border(4.dp, Color.LightGray, RoundedCornerShape(0.dp))
            .padding(16.dp)
            .clickable { }
    ) {

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PaddingExercisePreview1() {
    PaddingExercise1()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PaddingExercisePreview2() {
    PaddingExercise2()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PaddingExercisePreview3() {
    PaddingExercise3()
}