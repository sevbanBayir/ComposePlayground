package com.sevban.composetutorialssevbanbayir.Canvas.actual_donut_chart

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.sp
import com.sevban.composetutorialssevbanbayir.Canvas.DonutChart.ChartData
import com.sevban.composetutorialssevbanbayir.Canvas.DonutChart.asAngle
import com.sevban.composetutorialssevbanbayir.Canvas.DonutChart.degreeToRadian
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalTextApi::class)
class DonutChartState constructor(
    val chartData: List<ChartData>,
    private val textMeasurer: TextMeasurer,
    private val dpSize: DpSize,
) {
    val size = Size(dpSize.width.value * 3, dpSize.height.value * 3)
//    val animator = Animatable(1f)

    val textMeasureResult = chartData.map {
        textMeasurer.measure(
            text = "%${it.data.toInt()}",
            style = TextStyle(
                fontSize = 18.sp
            )
        )
    }

    val width = size.width
    val height = size.height
    val radius = width / 3
    val strokeWidth = 150f
    var startAngle = -90f
    val innerRadius = radius - strokeWidth
    val center = Offset(x = size.width / 2f, y = size.height / 2f)

    fun calculateXsegmentCenter(chartEntry: Float): Float {
        val sweepAngle = chartEntry.asAngle

        val angleInRadians = (startAngle + sweepAngle / 2).degreeToRadian
        var xSegmentCenter = center.y + (innerRadius + strokeWidth) * sin(angleInRadians)
        if (xSegmentCenter > 0 && xSegmentCenter <= width / 2f) xSegmentCenter -= 80f
        else xSegmentCenter += 80f
        return xSegmentCenter
    }

    fun calculateYsegmentCenter(chartEntry: Float): Float {
        val sweepAngle = chartEntry.asAngle

        val angleInRadians = (startAngle + sweepAngle / 2).degreeToRadian
        var ySegmentCenter = center.x + (innerRadius + strokeWidth) * cos(angleInRadians)
        when {
            ySegmentCenter > 0 && ySegmentCenter < height * (2 / 8f) -> ySegmentCenter -= 45f
            ySegmentCenter >= height * (2 / 8f) && ySegmentCenter <= height * (6 / 8f) -> 0f
            ySegmentCenter >= height * (6 / 8f) && ySegmentCenter <= height -> ySegmentCenter += 45f
        }
        return ySegmentCenter
    }

    fun calculateXdistance(xSegmentCenter: Float): Float {
        val xDistance =
            if (xSegmentCenter > 0 && xSegmentCenter <= width / 2f) -xSegmentCenter / 2f
            else (width - xSegmentCenter) / 2f

        return xDistance
    }

    fun calculateYdistance(ySegmentCenter: Float): Float {
        val yDistance = when {
            ySegmentCenter > 0 && ySegmentCenter < height * (2 / 8f) -> -ySegmentCenter / 2f
            ySegmentCenter >= height * (2 / 8f) && ySegmentCenter <= height * (6 / 8f) -> 0f
            ySegmentCenter >= height * (6 / 8f) && ySegmentCenter <= height -> (height - ySegmentCenter) / 2f
            else -> 0f
        }

        return yDistance
    }

    fun makePathForDetailLines(
        xSegmentCenter: Float,
        ySegmentCenter: Float,
        xDistance: Float,
        yDistance: Float
    ): Path {
        val path = Path().apply {
            moveTo(xSegmentCenter, ySegmentCenter)
            lineTo(xSegmentCenter + xDistance, ySegmentCenter + yDistance)
            lineTo(xSegmentCenter + xDistance + xDistance, ySegmentCenter + yDistance)
        }

        return path
    }

    fun calculateTextTopLeftX(
        xSegmentCenter: Float,
        xDistance: Float,
        entryIndex: Int
    ): Float {
        val xTopLeft =
            if (xSegmentCenter > 0 && xSegmentCenter <= width / 2f) xSegmentCenter + xDistance + xDistance
            else xSegmentCenter + xDistance + xDistance - textMeasureResult[entryIndex].size.width

        return xTopLeft
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun rememberDonutChartState(
    chartData: List<ChartData>,
    size: DpSize,
    textMeasurer: TextMeasurer
) = remember {
    DonutChartState(
        chartData = chartData,
        dpSize = size,
        textMeasurer = textMeasurer
    )
}