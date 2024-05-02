package com.sevban.composetutorialssevbanbayir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sevban.composetutorialssevbanbayir.canvas.weightpicker.Scale
import com.sevban.composetutorialssevbanbayir.canvas.weightpicker.ScaleStyle
import com.sevban.composetutorialssevbanbayir.special_components.curvedbottomnav.b_1.Screen
import com.sevban.composetutorialssevbanbayir.special_components.curvedbottomnav.b_2.CurvedBottomBar
import com.sevban.composetutorialssevbanbayir.special_components.curvedbottomnav.b_2.CustomBottomNavigationLayout
import com.sevban.composetutorialssevbanbayir.ui.theme.ComposeTutorialsSevbanBayirTheme
import com.sevban.composetutorialssevbanbayir.utils.getScreenSizeDp
import org.kotlinmath.Complex
import org.kotlinmath.I
import org.kotlinmath.plus
import org.kotlinmath.pow
import org.kotlinmath.times

class MainActivity : ComponentActivity() {
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
                                Scaffold(
                                    bottomBar = {
                                        CurvedBottomBar()
                                    }
                                ) {padding ->
                                    Column(
                                        Modifier
                                            .padding(padding)
                                            .fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Card (modifier = Modifier.fillMaxSize(), onClick = { }, shape = RectangleShape) {
                                            Canvas(modifier = Modifier.fillMaxSize()) {
                                                val gradient = Brush.horizontalGradient(
                                                    colors = listOf(
                                                        Color(0XFF00B100).copy(alpha = 0f),
                                                        Color(0XFF00B100).copy(alpha = 0.2f),
                                                        Color(0XFF0DACE1),
                                                        Color(0XFF00B100).copy(alpha = 0.2f),
                                                        Color(0XFF00B100).copy(alpha = 0f),
                                                    ),
                                                )

                                                drawLine(
                                                    brush = gradient,
                                                    start = Offset(0f,1000f),
                                                    end = Offset(1400f, 1000f),
                                                    strokeWidth = 50f
                                                )
                                            }
                                        }
                                    }
                                }

                /*                Column {

                                    HyperlinkText(
                                        stringResource = R.string.membership_agreement_text,
                                        modifier = Modifier.padding(16.dp),
                                        onClick = { hyperlink ->
                                            println(hyperlink)
                                        }
                                    )
                                    HyperlinkText(
                                        stringResource = R.string.explicit_consent_text,
                                        modifier = Modifier.padding(16.dp),
                                        onClick = { hyperlink ->
                                            println(hyperlink)
                                        }
                                    )
                                    HyperlinkText(
                                        stringResource = R.string.membership_agreement_text_tr,
                                        modifier = Modifier.padding(16.dp),
                                        onClick = { hyperlink ->
                                            println(hyperlink)
                                        }
                                    )
                                    HyperlinkText(
                                        stringResource = R.string.explicit_consent_text_tr,
                                        modifier = Modifier.padding(16.dp),
                                        onClick = { hyperlink ->
                                            println(hyperlink)
                                        }
                                    )
                                }*/


/*                Box(modifier = Modifier.fillMaxSize()) {
                    Scale(
                        style = ScaleStyle(scaleWidth = 150.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .align(Alignment.BottomCenter),
                        onWeightChange = { })
                }*/
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

val bottomBarItems =
    listOf(
        BottomNavItem.Home,
        BottomNavItem.Charge,
        BottomNavItem.Operations,
        BottomNavItem.Profile,
        BottomNavItem.Menu
    )

sealed class BottomNavItem(
    val route: String,
    val titleResId: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = Screen.Home.route,
        titleResId = "Home",
        icon = Icons.Default.Home
    )

    object Menu : BottomNavItem(
        route = Screen.Search.route,
        titleResId = "Search",
        icon = Icons.Default.Menu
    )

    object Operations : BottomNavItem(
        route = Screen.Search.route,
        titleResId = "Search",
        icon = Icons.Default.DateRange
    )

    object Profile : BottomNavItem(
        route = Screen.Search.route,
        titleResId = "Search",
        icon = Icons.Default.Person
    )

    object Charge : BottomNavItem(
        route = Screen.Search.route,
        titleResId = "Charge",
        icon = Icons.Default.Search
    )
}

@Composable
fun CustomBottomNavItem(
    icon: @Composable () -> Unit,
    label: String?,
    selected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.clickable(onClick = onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        icon()
        Spacer(modifier = Modifier.height(4.dp))
        if (label != null)
            Text(text = label, color = Color.White)
    }
}