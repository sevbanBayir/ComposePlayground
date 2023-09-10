package com.sevban.composetutorialssevbanbayir.Canvas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.dp

@Composable
fun DrawingExample1() {
    Spacer(modifier = Modifier
        .fillMaxSize()
        .drawBehind { drawCircle(Color.Cyan) })
}

@Composable
fun DrawingExample2() {
    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            drawCircle(
                color = Color.Cyan,
                center = Offset(x = 20.dp.toPx(), y = 100.dp.toPx()),
                radius = 60.dp.toPx()
            )
        }
    )
}

@Composable
fun DrawingExample3() {
    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            scale(scaleX = 10f, scaleY = 15f) {
                drawCircle(
                    color = Color.Cyan,
                    radius = 20.dp.toPx()
                )
            }
        }
    )
}

@Composable
fun DrawingExample4() {
    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            translate(left = 100f, top = -300f) {
                drawCircle(
                    color = Color.Cyan,
                    radius = 200.dp.toPx(),
                    style = Stroke(90f)
                )
            }
        }
    )
}

@Composable
fun DrawingExample5() {
    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            rotate(degrees = 45f) {
                drawRect(
                    color = Color(0xFF4CAF50),
                    topLeft = Offset(
                        x = size.width / 3f,
                        y = size.height / 3f
                    ),
                    size = size / 3f
                )
            }
        }
    )
}

@Composable
fun DrawingExample6() {
    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            val quadrantSize = size / 2f
            inset(horizontal = 10f, vertical = 10f) { //works like padding
                drawRect(
                    color = Color(0xFF4CAF50),
                    size = quadrantSize
                )
            }
        }
    )
}

@Composable
fun DrawingExample7() {
    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            val quadrantSize = size / 2f
            withTransform({
                translate(left = 120f, top = 220f)
                rotate(degrees = 45f)
            })
            { //works like padding
                drawRect(
                    color = Color(0xFF4CAF50),
                    size = quadrantSize
                )

                drawCircle(
                    color = Color(0xFF4CAF50),
                    radius = 60.dp.toPx()
                )
            }
        }
    )
}

@Composable
fun DrawingExample8() {
    Box(
        modifier = Modifier
            .background(Color(0xFF916FF0))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .padding(8.dp)
                .aspectRatio(3 / 2f)
                .fillMaxSize(),
            onDraw = {
                val bardWidthPx = 1.dp.toPx()
                drawRect(color = Color(0xFF6650a4), style = Stroke(bardWidthPx))

                val verticalLines = 4
                val verticalLineSize = size.width / (verticalLines + 1)
                repeat(verticalLines) { i ->
                    val startX = verticalLineSize * (i + 1)
                    drawLine(
                        color = Color(0xFF6650a4),
                        start = Offset(startX, 0f),
                        end = Offset(startX, size.height),
                        strokeWidth = bardWidthPx
                    )
                }

                val horizontalLines = 3
                val sectionSize = size.height / (horizontalLines + 1)
                repeat(horizontalLines) { i ->
                    val startY = sectionSize * (i + 1)
                    drawLine(
                        color = Color(0xFF6650a4),
                        start = Offset(0f, startY),
                        end = Offset(size.width, startY),
                        strokeWidth = bardWidthPx
                    )
                }
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DrawingExample9() {
    val animationProgress = remember {
        Animatable(0f)
    }
    Box(
        modifier = Modifier
            .background(Color(0xFF916FF0))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Spacer(
            modifier = Modifier
                .padding(8.dp)
                .aspectRatio(3 / 2f)
                .fillMaxSize()
                .drawWithCache {

                    val path = generatePath(graphData, size)
                    val filledPath = Path()
                    filledPath.addPath(path)
                    filledPath.relativeLineTo(0f, size.height)
                    filledPath.lineTo(0f, size.height)
                    filledPath.close()

                    onDrawBehind {

                        val bardWidthPx = 1.dp.toPx()
                        drawRect(color = Color(0xFF6650a4), style = Stroke(bardWidthPx))

                        val verticalLines = 4
                        val verticalLineSize = size.width / (verticalLines + 1)
                        repeat(verticalLines) { i ->
                            val startX = verticalLineSize * (i + 1)
                            drawLine(
                                color = Color(0xFF6650a4),
                                start = Offset(startX, 0f),
                                end = Offset(startX, size.height),
                                strokeWidth = bardWidthPx
                            )
                        }

                        val horizontalLines = 3
                        val sectionSize = size.height / (horizontalLines + 1)
                        repeat(horizontalLines) { i ->
                            val startY = sectionSize * (i + 1)
                            drawLine(
                                color = Color(0xFF6650a4),
                                start = Offset(0f, startY),
                                end = Offset(size.width, startY),
                                strokeWidth = bardWidthPx
                            )
                        }
                        clipRect(right = size.width) {
                            drawPath(path, Color.Green, style = Stroke(2.dp.toPx()))

                            drawPath(
                                filledPath,
                                brush = Brush.verticalGradient(
                                    listOf(
                                        Color.Green.copy(alpha = 0.4f),
                                        Color.Transparent
                                    )
                                ),
                                style = Fill
                            )
                        }
                    }
                }
        )
    }
}

