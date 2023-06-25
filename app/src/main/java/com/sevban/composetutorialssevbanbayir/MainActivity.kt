package com.sevban.composetutorialssevbanbayir

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.sevban.composetutorialssevbanbayir.Canvas.DrawOnCanvasWithSVGPath
import com.sevban.composetutorialssevbanbayir.Canvas.DrawingExample8
import com.sevban.composetutorialssevbanbayir.Canvas.DrawingExample9
import com.sevban.composetutorialssevbanbayir.Canvas.SmoothLineGraph
import com.sevban.composetutorialssevbanbayir.State_Compose.SaveableExpandable
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
                  DrawOnCanvasWithSVGPath()

            }
        }
    }
}