package com.sevban.composetutorialssevbanbayir.Canvas.DonutChart

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.dp
import com.sevban.composetutorialssevbanbayir.Canvas.actual_donut_chart.DonutChartState

@OptIn(ExperimentalTextApi::class)
@Composable
fun DonutChart(
    donutChartState: DonutChartState,
) {

    LaunchedEffect(key1 = true) {
        donutChartState.animator.animateTo(1f, animationSpec = tween(1000))
    }

    Canvas(
        modifier = Modifier
            .size(350.dp)
            .padding(6.dp)
    ) {

        donutChartState.chartData.forEachIndexed { index, chartEntry ->
            val sweepAngle = chartEntry.data.asAngle

            val ySegmentCenter =
                donutChartState.calculateYsegmentCenter(chartEntry = chartEntry.data)
            val xSegmentCenter =
                donutChartState.calculateXsegmentCenter(chartEntry = chartEntry.data)


            val xDistance = donutChartState.calculateXdistance(xSegmentCenter)

            val yDistance = donutChartState.calculateYdistance(ySegmentCenter)

            val path = donutChartState.makePathForDetailLines(
                xSegmentCenter, ySegmentCenter, xDistance, yDistance
            )

            val xTextTopLeft = donutChartState.calculateTextTopLeftX(
                xSegmentCenter, xDistance, index
            )

            drawArc(
                color = chartEntry.color,
                startAngle = donutChartState.startAngle,
                sweepAngle = sweepAngle * donutChartState.animator.value,
                useCenter = false,
                topLeft = Offset(
                    x = donutChartState.center.x - donutChartState.radius,
                    y = donutChartState.center.y - donutChartState.radius
                ),
                style = Stroke(width = donutChartState.strokeWidth),
                size = Size(2 * donutChartState.radius, 2 * donutChartState.radius)
            )

            clipRect(right = size.width * donutChartState.animator.value) {
                drawPath(
                    path = path,
                    color = Color.Black,
                    style = Stroke(width = 8f)
                )
            }

            drawText(
                textLayoutResult = donutChartState.textMeasureResult[index],
                color = chartEntry.color,
                topLeft = Offset(
                    x = xTextTopLeft,
                    y = ySegmentCenter + yDistance
                )
            )
            donutChartState.startAngle += chartEntry.data.asAngle // increase sweep angle
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