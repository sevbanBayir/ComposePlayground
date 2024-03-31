package com.sevban.composetutorialssevbanbayir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.sevban.composetutorialssevbanbayir.canvas.weightpicker.Scale
import com.sevban.composetutorialssevbanbayir.canvas.weightpicker.ScaleStyle
import com.sevban.composetutorialssevbanbayir.special_components.curvedbottomnav.b_1.Screen
import com.sevban.composetutorialssevbanbayir.ui.theme.ComposeTutorialsSevbanBayirTheme
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
                /*                Scaffold(
                                    bottomBar = {
                                        val curveSize = 120.dp
                                        val bottomBarWidth = getScreenSizeDp().screenWidth
                                        val bottomBarHeight = 120.dp
                                        val cpHeight = 20.dp

                                        CustomBottomNavigationLayout(
                                            modifier = Modifier
                                                .drawBehind {
                                                    val gradient = Brush.radialGradient(
                                                        listOf(
                                                            Color(0XFF0DACE1),
                                                            Color(0XFF00B100),
                                                            Color.Black,
                                                        ),
                                                        center = Offset((bottomBarWidth.toPx() / 2f), 0f)
                                                    )

                                                    val halfWidth = bottomBarWidth.toPx() / 2
                                                    val halfMinusHalf = halfWidth - curveSize.toPx() / 2
                                                    val halfPlusHalf = halfWidth + curveSize.toPx() / 2
                                                    val halfOfCurve = curveSize.toPx() / 2

                                                    val bottomBarPath = Path().apply {
                                                        lineTo(halfMinusHalf - 450f, 0f)
                                                        cubicTo(
                                                            x1 = halfWidth,
                                                            y1 = 0f,
                                                            x2 = halfMinusHalf,
                                                            y2 = -cpHeight.toPx(),
                                                            x3 = halfWidth,
                                                            y3 = -cpHeight.toPx(),
                                                        )
                                                        cubicTo(
                                                            x1 = halfPlusHalf,
                                                            y1 = -cpHeight.toPx(),
                                                            x2 = halfWidth,
                                                            y2 = 0f,
                                                            x3 = halfPlusHalf + 450f,
                                                            y3 = 0f,
                                                        )
                                                        lineTo(bottomBarWidth.toPx(), 0f)
                                                        lineTo(bottomBarWidth.toPx(), bottomBarHeight.toPx())
                                                        lineTo(0f, bottomBarHeight.toPx())
                                                        lineTo(0f, 0f)
                                                        close()
                                                    }

                                                    drawPath(
                                                        path = bottomBarPath,
                                                        brush = gradient,
                                                        style = Stroke(20f)
                                                    )

                                                    drawPath(
                                                        path = bottomBarPath,
                                                        color = Color.Black,
                                                    )
                                                }
                                        ) {
                                            bottomBarItems.forEachIndexed { index, item ->
                                                if (index == 2) {

                                                    CustomBottomNavItem(
                                                        icon = {
                                                            Icon(
                                                                painter = painterResource(id = R.drawable.bi_lightning),
                                                                contentDescription = null,
                                                                tint = Color.Unspecified,
                                                                modifier = Modifier.size(90.dp)
                                                            )
                                                        },
                                                        label = null,
                                                        selected = true,
                                                        onClick = {
                                                        }
                                                    )

                                                } else {
                                                    CustomBottomNavItem(
                                                        icon = {
                                                            Icon(
                                                                imageVector = item.icon,
                                                                contentDescription = null,
                                                                tint = Color.White
                                                            )
                                                        },
                                                        label = item.titleResId,
                                                        selected = true,
                                                        onClick = {
                                                        }
                                                    )

                                                }
                                            }
                                        }

                                    }
                                ) {
                                    Column(
                                        Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = "Sevban")
                                        Icon(
                                            painter = painterResource(id = R.drawable.bi_lightning),
                                            contentDescription = null,
                                            modifier = Modifier.size(50.dp),
                                            tint = Color.Unspecified
                                        )
                                    }
                                }*/

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


                Box(modifier = Modifier.fillMaxSize()) {
                    Scale(
                        style = ScaleStyle(scaleWidth = 150.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .align(Alignment.BottomCenter),
                        onWeightChange = { })
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