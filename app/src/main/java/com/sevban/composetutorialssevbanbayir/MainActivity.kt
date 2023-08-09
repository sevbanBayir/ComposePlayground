package com.sevban.composetutorialssevbanbayir

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.sevban.composetutorialssevbanbayir.Canvas.customdarkmodeswitcher.CustomDarkModeSwithcer
import com.sevban.composetutorialssevbanbayir.Canvas.customdarkmodeswitcher.DayNightSwitch
import com.sevban.composetutorialssevbanbayir.ui.theme.ComposeTutorialsSevbanBayirTheme

class MainActivity : ComponentActivity() {
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
                DayNightSwitch()
            }
        }
    }
}