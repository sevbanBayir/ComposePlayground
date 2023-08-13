package com.sevban.composetutorialssevbanbayir.Canvas.PieChart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sevban.composetutorialssevbanbayir.ui.theme.Purple40
import com.sevban.composetutorialssevbanbayir.ui.theme.Purple80
import com.sevban.composetutorialssevbanbayir.ui.theme.PurpleGrey40
import com.sevban.composetutorialssevbanbayir.ui.theme.PurpleGrey80
import kotlin.random.Random

@Composable
fun PieChart(
    chartData: ChartData2
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(3.dp, Color.Blue),
        contentAlignment = Alignment.Center
    ) {

        Canvas(
            modifier = Modifier
                .size(200.dp)
                .border(2.dp, Color.Black)
                .aspectRatio(1f)
        ) {
            val width = size.width
            val radius = width / 2
            val strokeWidth = radius * 0.5f
            var startAngle = -90f


            // draw each arc for each data entry in chart
            chartData.amounts.forEachIndexed { index ,chartEntry ->
                val sweepAngle = chartEntry.asAngle
                drawArc(
                    color = chartData.colors[index],
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,

                    style = Stroke(width = strokeWidth),
                    size = Size(width - strokeWidth, width - strokeWidth)
                )
                startAngle += chartEntry.asAngle // increase sweep angle
            }
        }
    }
}

data class ChartData2(
    val amounts: List<Float> = emptyList(),
    val colors: List<Color> = emptyList()
)

val chart = ChartData2(
    amounts = listOf(30f, 40f, 8f, 6f, 16f),
    colors = listOf(
        Purple40,
        Purple80,
        PurpleGrey40,
        PurpleGrey80,
        Purple80
    )
)

private val Float.degreeToAngle : Float
    get() = (this * Math.PI / 360f).toFloat()

private val Float.asAngle: Float
    get() = this * 360f / 100f


@Preview
@Composable
fun PieChartPrev() {
    PieChart(chartData = chart)
}

