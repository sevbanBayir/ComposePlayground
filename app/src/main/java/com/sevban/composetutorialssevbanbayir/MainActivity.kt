package com.sevban.composetutorialssevbanbayir

import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.sevban.composetutorialssevbanbayir.special_components.curvedbottomnav.b_1.Screen
import com.sevban.composetutorialssevbanbayir.special_components.curvedbottomnav.b_2.CurvedBottomBar
import com.sevban.composetutorialssevbanbayir.ui.theme.ComposeTutorialsSevbanBayirTheme
import org.kotlinmath.Complex
import org.kotlinmath.I
import org.kotlinmath.plus
import org.kotlinmath.pow
import org.kotlinmath.times
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialsSevbanBayirTheme {
                Scaffold(
                    bottomBar = {
                        CurvedBottomBar()
                    }
                ) { scaffoldPadding ->
                    Column(
                        Modifier
                            .background(Color.White)

                            .padding(scaffoldPadding)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        repeat(50) {
                            val randomFloat = Random.nextDouble(10.0, 100.0)
                            Card(
                                modifier = Modifier
                                    .size(200.dp)
                                    .drawWithContent {
                                        drawContent()
                                        /*                                drawLine(
                                            start = Offset(0f, size.height / 2),
                                            end = Offset(size.width, size.height / 2),
                                            color = Color.Red
                                        )*/

                                        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                                            strokeWidth = 10f
                                            color = android.graphics.Color.BLACK
                                            style = Paint.Style.STROKE
                                            setShadowLayer(
                                                randomFloat.toFloat(),
                                                0f,
                                                0f,
                                                android.graphics.Color.BLACK
                                            )
                                        }

                                        val androidPath = Path().apply {
                                            moveTo(0f, size.height / 2)
                                            lineTo(size.width, size.height / 2)
                                        }
                                        drawContext.canvas.nativeCanvas.apply {

                                            drawPath(
                                                androidPath,
                                                paint
                                            )
                                        }
                                    },
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                onClick = { },
                                shape = RectangleShape
                            ) {
                                Text(text = "Item $it")
                            }
                        }
                    }
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
        BottomNavItem.Operations,
        BottomNavItem.Charge,
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
        titleResId = "Add",
        icon = Icons.Default.Add
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