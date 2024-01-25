package com.sevban.composetutorialssevbanbayir.Canvas

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private enum class State {
    Collapsed,
    Expanded
}

@Preview
@Composable
fun CrossingComposables() {
    val cardSize = 100.dp
    var currentState by remember { mutableStateOf(State.Collapsed) }

    val pathStationary = remember {
        Path()
    }


    val pathMoving = remember {
        Path()
    }
    val transition = updateTransition(
        targetState = currentState, label = "expand_transition"
    )
    val animateDpAsState by transition.animateDp(
        targetValueByState = { state ->
            when (state) {
                State.Expanded -> cardSize + 8.dp
                State.Collapsed -> 8.dp
            }
        },
        label = ""
    )

    Box(
        modifier = Modifier
            .width(cardSize + animateDpAsState)
            .clickable {
                currentState = if (currentState == State.Collapsed) {
                    State.Expanded
                } else {
                    State.Collapsed
                }
            }
    ) {
        Element(
            modifier = Modifier.onPlaced {
                pathStationary.reset()
                pathStationary.addRect(it.boundsInParent())
            },
            i = 1
        )
        Element(
            modifier = Modifier

                .drawWithContent {
                    val diffPath =
                        Path.combine(PathOperation.Difference, path1 = pathMoving, path2 = pathStationary)

                    clipPath(diffPath){
                        this@drawWithContent.drawContent()
                    }
                }
                .offset(x = animateDpAsState + 4.dp)
                .scale(0.90f)
                .onPlaced {
                    pathMoving.reset()
                    pathMoving.addRect(it.boundsInParent())
                }
            ,
            i = 2
        )
    }
}

@Composable
fun Element(modifier: Modifier = Modifier, i: Int) {
    Box(
        modifier = modifier
            .size(100.dp)
            .border(2.dp, Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "ITEM $i")
    }
}