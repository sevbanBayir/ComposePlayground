package com.sevban.composetutorialssevbanbayir

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.sevban.composetutorialssevbanbayir.animations.SpringAnimationInspection
import com.sevban.composetutorialssevbanbayir.ui.theme.ComposeTutorialsSevbanBayirTheme
import org.kotlinmath.Complex
import org.kotlinmath.I
import org.kotlinmath.plus
import org.kotlinmath.pow
import org.kotlinmath.times

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialsSevbanBayirTheme {
//                AnimatedButtonWithStateH()
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
                SpringAnimationInspection()

            }
        }
    }

    /*it should be close to z(t) = e^(-it) + 3e^nit ,
    where I guess the ratio n is anywhere near 1.0625 ,
    i is imaginary number and e is euler’s constant,
    t is the angle which changes with frames
*/
    val complex = 4 + 4.I
    fun imgFunction(angleT: Float): Complex {
        return pow(Math.E, -I * angleT) + pow(3 * Math.E, 1.0625 * I * angleT)
    }
}