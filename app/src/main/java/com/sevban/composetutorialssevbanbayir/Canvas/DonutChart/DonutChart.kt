package com.sevban.composetutorialssevbanbayir.Canvas.DonutChart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sevban.composetutorialssevbanbayir.ui.theme.Purple40
import com.sevban.composetutorialssevbanbayir.ui.theme.Purple80
import com.sevban.composetutorialssevbanbayir.ui.theme.PurpleGrey40
import com.sevban.composetutorialssevbanbayir.ui.theme.PurpleGrey80
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalTextApi::class)
@Composable
fun DonutChart(
    chartData: ChartData2
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(3.dp, Color.Blue),
        contentAlignment = Alignment.Center
    ) {

        val textMeasurer = rememberTextMeasurer()
        val textMeasureResults = remember(chartData) {
            chartData.amounts.map {
                textMeasurer.measure(
                    text = "%${it.toInt()}",
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }

        Canvas(
            modifier = Modifier
                .aspectRatio(1f)
                .aspectRatio(1f)
                .padding(6.dp)
                //.border(2.dp, Color.Black)
        ) {
            val width = size.width
            val height = size.height
            val radius =width / 3
            val strokeWidth = 150f
            var startAngle = -90f
            val innerRadius = radius - strokeWidth

            // draw each arc for each data entry in chart
            chartData.amounts.forEachIndexed { index ,chartEntry ->
                val sweepAngle = chartEntry.asAngle
                val angleInRadians = (startAngle + sweepAngle / 2).degreeToRadian

                drawArc(
                    color = chartData.colors[index],
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    topLeft = Offset(x = center.x - radius, y = center.y - radius),
                    style = Stroke(width = strokeWidth),
                    size = Size(2*radius, 2*radius)
                )



                var ySegmentCenter = center.y + (innerRadius + strokeWidth) * sin(angleInRadians)
                var xSegmentCenter = center.x + (innerRadius + strokeWidth) * cos(angleInRadians)

                if (xSegmentCenter > 0 && xSegmentCenter <= width / 2f) xSegmentCenter -= 80f
                else xSegmentCenter += 80f

                when {
                    ySegmentCenter > 0 && ySegmentCenter < height * (2/8f) -> ySegmentCenter -= 45f
                    ySegmentCenter >= height * (2/8f) && ySegmentCenter <= height * (6/8f) -> 0f
                    ySegmentCenter >= height * (6/8f) && ySegmentCenter <= height -> ySegmentCenter += 45f
                    else -> 0f
                }

                val xDistance =
                    if (xSegmentCenter > 0 && xSegmentCenter <= width / 2f) -xSegmentCenter / 2f
                    else (width - xSegmentCenter) / 2f

                val yyDistance = when {
                    ySegmentCenter > 0 && ySegmentCenter < height * (2/8f) -> -ySegmentCenter / 2f
                    ySegmentCenter >= height * (2/8f) && ySegmentCenter <= height * (6/8f) -> 0f
                    ySegmentCenter >= height * (6/8f) && ySegmentCenter <= height -> (height - ySegmentCenter) / 2f
                    else -> 0f
                }

                val path = Path().apply {
                    moveTo(xSegmentCenter, ySegmentCenter)
                    lineTo(xSegmentCenter + xDistance, ySegmentCenter + yyDistance)
                    lineTo(xSegmentCenter + xDistance + xDistance, ySegmentCenter + yyDistance)
                }

                drawPath(
                    path = path,
                    color = Color.Black,
                    style = Stroke(width = 5f)
                )
                val xTopLeft = if (xSegmentCenter > 0 && xSegmentCenter <= width / 2f) xSegmentCenter + xDistance + xDistance
                else xSegmentCenter + xDistance + xDistance - textMeasureResults[index].size.width

                drawText(
                    textLayoutResult = textMeasureResults[index],
                    color = chartData.colors[index],
                    topLeft = Offset(
                        x = xTopLeft,
                        y = ySegmentCenter + yyDistance
                    )
                )

                startAngle += chartEntry.asAngle // increase sweep angle
            }


        }
    }
}

val chartDataList = listOf(
    ChartData(Color.Gray, 12.5f),
    ChartData(Color.Blue, 12.5f),
    ChartData(Color.Green, 12.5f),
    ChartData(Color.Cyan, 12.5f),
    ChartData(Color.Magenta, 50f),
)

data class ChartData2(
    val amounts: List<Float> = emptyList(),
    val colors: List<Color> = emptyList()
)

val chart = ChartData2(
    amounts = listOf(12.5f, 12.5f, 12.5f, 12.5f, 12.5f, 12.5f, 12.5f, 12.5f,),
    colors = listOf(
        Color(0xFFC62828),
        Color(0xFFAD1457),
        Color(0xFF283593),
        Color(0xFFEF6C00),
        Color(0xFF2E7D32),
        Color(0xFF6A1B9A),
        Color(0xFFF9A825),
        Color(0xFF00838F),
    )
)

private val Float.degreeToAngle : Float
    get() = (this * Math.PI / 360f).toFloat()

@Preview
@Composable
fun PieChartPrev() {
    DonutChart(chartData = chart)
}

