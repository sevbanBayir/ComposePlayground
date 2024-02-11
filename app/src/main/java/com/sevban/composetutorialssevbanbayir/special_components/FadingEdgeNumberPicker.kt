package com.sevban.composetutorialssevbanbayir.special_components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FadingEdgeNumberPicker() {

    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    val leftRightFade = Brush.horizontalGradient(
        0f to Color.Transparent,
        0.5f to Color.Red,
        0.5f to Color.Red,
        1f to Color.Transparent
    )

    LazyRow(
        state = lazyListState,
        flingBehavior = snapBehavior,
        modifier = Modifier
            .fadingEdge(brush = leftRightFade)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(100) {
            BorderedText(lazyListState = lazyListState, index = it)
        }
    }
}

fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }

@Composable
fun BorderedText(lazyListState: LazyListState, index: Int) {

    val borderColor by remember {
        derivedStateOf {
            val layoutInfo = lazyListState.layoutInfo
            val visibleItemsInfo = layoutInfo.visibleItemsInfo
            val itemInfo = visibleItemsInfo.firstOrNull { it.index == index }

            itemInfo?.let {

                val delta = it.size / 2 //use your custom logic
                val center = lazyListState.layoutInfo.viewportEndOffset / 2
                val childCenter = it.offset + it.size / 2
                val target = childCenter - center
                if (target in -delta..delta) return@derivedStateOf Red
            }
            Transparent
        }
    }


    Text(
        text = index.toString(),
        style = TextStyle(fontSize = 20.sp),
        modifier = Modifier
            .size(50.dp)
            .border(2.dp, borderColor, CircleShape)
            .wrapContentHeight(),
        textAlign = TextAlign.Center
    )
}