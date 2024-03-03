package com.sevban.composetutorialssevbanbayir

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sevban.composetutorialssevbanbayir.special_components.curvedbottomnav.b_2.CurvedRect
import com.sevban.composetutorialssevbanbayir.ui.theme.ComposeTutorialsSevbanBayirTheme
import org.kotlinmath.Complex
import org.kotlinmath.I
import org.kotlinmath.plus
import org.kotlinmath.pow
import org.kotlinmath.times

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialsSevbanBayirTheme {
/*//                AnimatedButtonWithStateH()
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
//                Screen()*/

                /*                Box (Modifier.fillMaxSize().border(2.dp, Color.Blue), contentAlignment = Alignment.Center) {
                                    SpringAnimationInspection()
                                }*/

//            FadingEdgeNumberPicker()
                Column(
                    modifier = Modifier.fillMaxSize().border(3.dp, Color.Green),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CurvedRect(
                        Modifier.border(2.dp, Color.Red)
                    )
                }
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

    val asd = imgFunction(2f).im
    val das = imgFunction(2f).re
}