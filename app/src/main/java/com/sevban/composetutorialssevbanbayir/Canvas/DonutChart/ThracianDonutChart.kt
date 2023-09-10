package com.sevban.composetutorialssevbanbayir.Canvas.DonutChart

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalTextApi::class)
@Preview
@Composable
fun PieChartWithText() {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val chartDataList = listOf(
            ChartData(Color(0xFFE1F5FE), 12.5f),
            ChartData(Color(0xFF316D88), 12.5f),
            ChartData(Color(0xFFF9A825), 12.5f),
            ChartData(Color(0xFFEF6C00), 12.5f),
            ChartData(Color(0xFFD84315), 12.5f),
            ChartData(Color(0xFF2E7D32), 12.5f),
            ChartData(Color(0xFF6A1B9A), 12.5f),
            ChartData(Color(0xFF1565C0), 12.5f),
        )

        val textMeasurer = rememberTextMeasurer()
        val textMeasureResults = remember(chartDataList) {
            chartDataList.map {
                textMeasurer.measure(
                    text = "%${it.data.toInt()}",
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .size(400.dp)
                .border(1.dp, Color.Blue)
                .padding(72.dp)
        ) {
            val width = size.width
            val height = size.height
            val radius = width / 2f
            val strokeWidth = radius * .4f
            val innerRadius = radius - strokeWidth
            var startAngle = -90f

            for (index in 0..chartDataList.lastIndex) {

                val chartData = chartDataList[index]
                val sweepAngle = chartData.data.asAngle
                val angleInRadians = (startAngle + sweepAngle / 2).degreeToRadian
                val textMeasureResult = textMeasureResults[index]
                val textSize = textMeasureResult.size

                val textCenter = textSize.center
                val yCenterSegment = (innerRadius + strokeWidth / 2) * sin(angleInRadians)
                val xCenterSegment = (innerRadius + strokeWidth / 2) * cos(angleInRadians)
                val lineStartX = -textCenter.x + center.x + xCenterSegment
                val lineStartY = -textCenter.y + center.y + yCenterSegment


                val xDistance =
                    if (lineStartX > 0 && lineStartX <= width / 2f) -lineStartX
                    else (width - lineStartX)

                val yyDistance = when {
                    lineStartY > 0 && lineStartY <= radius / 4 -> -lineStartY
                    lineStartY > radius / 4 && lineStartY <= radius * (3 / 4) -> 0f
                    lineStartY > radius * (3 / 4) && lineStartY < radius -> (radius - lineStartY)
                    else -> 0f
                }

                val yDistance =
                    if (lineStartY > 0 && lineStartY <= height / 2) -lineStartY
                    else (height - lineStartY)

                val path = Path().apply {
                    moveTo(lineStartX, lineStartY)
                    lineTo(lineStartX + xDistance, lineStartY + yyDistance)
                    lineTo(lineStartX + xDistance + (xDistance / 2), lineStartY + yyDistance)
                }

                println("center X: " + lineStartX)
                println("center Y: " + lineStartY)
                println("distance x: " + xDistance)
                println("distance y: " + yDistance)
                println("width: " + width)
                println("height: " + height)
                println(" ")


                drawPath(
                    path = path,
                    color = Color(0xFF000000),
                    style = Stroke(width = 5f)
                )

                drawArc(
                    color = chartData.color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
                    size = Size(width - strokeWidth, width - strokeWidth),
                    style = Stroke(strokeWidth)
                )
                /*
                                drawText(
                                    textLayoutResult = textMeasureResult,
                                    color = Color.Gray,
                                    topLeft = Offset(
                                        -textCenter.x + lineStartX + xDistance + (xDistance),
                                        -textCenter.y + lineStartY + yDistance
                                    )
                                )*/

                startAngle += sweepAngle
            }
        }
    }
}


@Immutable
data class ChartData(val color: Color, val data: Float)

@OptIn(ExperimentalTextApi::class)
@Preview
@Composable
fun AnimatedChart() {

    val animatable = remember {
        Animatable(0f)
    }

    val finalValue = 360f

    LaunchedEffect(key1 = animatable) {
        animatable.animateTo(
            targetValue = finalValue,
            animationSpec = tween(
                delayMillis = 1000,
                durationMillis = 2000
            )
        )
    }
    val currentSweepAngle = animatable.value


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        val chartDataList = listOf(
            ChartData(Color.Gray, 10f),
            ChartData(Color.Blue, 20f),
            ChartData(Color.Green, 15f),
            ChartData(Color.Cyan, 5f),
            ChartData(Color.Magenta, 50f),
        )

        val textMeasurer = rememberTextMeasurer()
        val textMeasureResults = remember(chartDataList) {
            chartDataList.map {
                textMeasurer.measure(
                    text = "%${it.data.toInt()}",
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            val width = size.width
            val radius = width / 2f
            val strokeWidth = radius * .4f
            val innerRadius = radius - strokeWidth
            val lineStrokeWidth = 3.dp.toPx()

            var startAngle = 0f

            for (index in 0..chartDataList.lastIndex) {

                val chartData = chartDataList[index]
                val sweepAngle = chartData.data.asAngle
                val angleInRadians = (startAngle + sweepAngle / 2).degreeToRadian
                val textMeasureResult = textMeasureResults[index]
                val textSize = textMeasureResult.size

                if (startAngle <= currentSweepAngle) {
                    drawArc(
                        color = chartData.color,
                        startAngle = startAngle,
                        sweepAngle = sweepAngle.coerceAtMost(currentSweepAngle - startAngle),
                        useCenter = false,
                        topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
                        size = Size(width - strokeWidth, width - strokeWidth),
                        style = Stroke(strokeWidth)
                    )
                }

                rotate(
                    90f + startAngle
                ) {
                    drawLine(
                        color = Color.White,
                        start = Offset(radius, strokeWidth),
                        end = Offset(radius, 0f),
                        strokeWidth = lineStrokeWidth
                    )
                }

                val textCenter = textSize.center

                if (currentSweepAngle == finalValue) {
                    drawText(
                        textLayoutResult = textMeasureResult,
                        color = Color.Red,
                        topLeft = Offset(
                            -textCenter.x + center.x + (innerRadius + strokeWidth / 2) * cos(
                                angleInRadians
                            ),
                            -textCenter.y + center.y + (innerRadius + strokeWidth / 2) * sin(
                                angleInRadians
                            )
                        )
                    )
                }

                startAngle += sweepAngle
            }
        }
    }
}

val Float.degreeToRadian
    get() = (this * Math.PI / 180f).toFloat()

val Float.asAngle: Float
    get() = this * 360f / 100f