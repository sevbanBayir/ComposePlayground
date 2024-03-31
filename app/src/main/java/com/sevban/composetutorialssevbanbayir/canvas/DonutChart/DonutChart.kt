package com.sevban.composetutorialssevbanbayir.canvas.DonutChart

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalTextApi::class)
@Composable
fun DonutChart(
    chartData: List<ChartData>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(3.dp, Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        val floatAnimatable = remember {
            Animatable(0f)
        }

        LaunchedEffect(key1 = true) {
            floatAnimatable.animateTo(1f, animationSpec = tween(1000))
        }

        val textMeasurer = rememberTextMeasurer()
        val textMeasureResults = remember(chartData) {
            chartData.map {
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
                .size(350.dp)
                .padding(6.dp)
        ) {
            val width = size.width
            val height = size.height
            val radius = width / 3
            val strokeWidth = 150f
            var startAngle = -90f
            val innerRadius = radius - strokeWidth

            chartData.forEachIndexed { index, chartEntry ->
                val sweepAngle = chartEntry.data.asAngle
                val angleInRadians = (startAngle + sweepAngle / 2).degreeToRadian

                var ySegmentCenter = center.y + (innerRadius + strokeWidth) * sin(angleInRadians)
                var xSegmentCenter = center.x + (innerRadius + strokeWidth) * cos(angleInRadians)

                if (xSegmentCenter > 0 && xSegmentCenter <= width / 2f) xSegmentCenter -= 80f
                else xSegmentCenter += 80f

                when {
                    ySegmentCenter > 0 && ySegmentCenter < height * (2 / 8f) -> ySegmentCenter -= 45f
                    ySegmentCenter >= height * (2 / 8f) && ySegmentCenter <= height * (6 / 8f) -> 0f
                    ySegmentCenter >= height * (6 / 8f) && ySegmentCenter <= height -> ySegmentCenter += 45f
                    else -> 0f
                }

                val xDistance =
                    if (xSegmentCenter > 0 && xSegmentCenter <= width / 2f) -xSegmentCenter / 2f
                    else (width - xSegmentCenter) / 2f

                val yDistance = when {
                    ySegmentCenter > 0 && ySegmentCenter < height * (2 / 8f) -> -ySegmentCenter / 2f
                    ySegmentCenter >= height * (2 / 8f) && ySegmentCenter <= height * (6 / 8f) -> 0f
                    ySegmentCenter >= height * (6 / 8f) && ySegmentCenter <= height -> (height - ySegmentCenter) / 2f
                    else -> 0f
                }

                val path = Path().apply {
                    moveTo(xSegmentCenter, ySegmentCenter)
                    lineTo(xSegmentCenter + xDistance, ySegmentCenter + yDistance)
                    lineTo(xSegmentCenter + xDistance + xDistance, ySegmentCenter + yDistance)
                }

                val xTopLeft =
                    if (xSegmentCenter > 0 && xSegmentCenter <= width / 2f) xSegmentCenter + xDistance + xDistance
                    else xSegmentCenter + xDistance + xDistance - textMeasureResults[index].size.width

                drawArc(
                    color = chartEntry.color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle * floatAnimatable.value,
                    useCenter = false,
                    topLeft = Offset(x = center.x - radius, y = center.y - radius),
                    style = Stroke(width = strokeWidth),
                    size = Size(2 * radius, 2 * radius)
                )

                clipRect(right = size.width * floatAnimatable.value) {
                    drawPath(
                        path = path,
                        color = Color.Black,
                        style = Stroke(width = 8f)
                    )
                }

                drawText(
                    textLayoutResult = textMeasureResults[index],
                    color = chartEntry.color,
                    topLeft = Offset(
                        x = xTopLeft,
                        y = ySegmentCenter + yDistance
                    )
                )
                startAngle += chartEntry.data.asAngle // increase sweep angle
            }
        }
    }
}

val chartDataList = listOf(
    ChartData(Color(0xFFC62828), 12.5f),
    ChartData(Color(0xFFEF6C00), 12.5f),
    ChartData(Color(0xFF00838F), 12.5f),
    ChartData(Color(0xFF2E7D32), 12.5f),
    ChartData(Color(0xFF6A1B9A), 12.5f),
    ChartData(Color(0xFFD84315), 12.5f),
    ChartData(Color(0xFF283593), 12.5f),
    ChartData(Color(0xFF00695C), 12.5f),
    ChartData(Color(0xFFAD1457), 12.5f),
)

@Preview
@Composable
fun PieChartPrev() {
    DonutChart(chartData = chartDataList)
}

