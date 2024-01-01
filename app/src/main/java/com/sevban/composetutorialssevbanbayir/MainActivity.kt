package com.sevban.composetutorialssevbanbayir

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Slider
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import com.sevban.composetutorialssevbanbayir.Canvas.DonutChart.DonutChart
import com.sevban.composetutorialssevbanbayir.Canvas.DonutChart.chartDataList
import com.sevban.composetutorialssevbanbayir.Canvas.actual_donut_chart.rememberDonutChartState
import com.sevban.composetutorialssevbanbayir.ui.theme.ComposeTutorialsSevbanBayirTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalTextApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialsSevbanBayirTheme {
//                DrawingExample2()
//                DrawingExample3()
//                DrawingExample4()
//                DrawingExample5()
//                DrawingExample6()
//                DrawingExample7()
//                DrawingExample8()
//                  DrawingExample9()
                //SmoothLineGraph()
//                SaveableExpandable()
//                  DrawOnCanvasWithSVGPath()
//                ConcaveDecagon()
//                CustomDarkModeSwithcer()
//                DayNightSwitch()
//                FacebookIcon()
//                DonutChart(chartData = chartDataList)
//                PieChartWithText()
//                AnimatedChart()
//                Screen()
                BoxWithConstraints (
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val donutChartState = rememberDonutChartState(
                        chartData = chartDataList,
                        size = Size(
                            1200f,
                            1200f
                        ),
                        textMeasurer = rememberTextMeasurer()
                    )

                    DonutChart(donutChartState = donutChartState)
                }
            }
        }
    }
}