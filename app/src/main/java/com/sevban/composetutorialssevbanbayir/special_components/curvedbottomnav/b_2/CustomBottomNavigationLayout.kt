package com.sevban.composetutorialssevbanbayir.special_components.curvedbottomnav.b_2

import android.graphics.Path.FillType
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sevban.composetutorialssevbanbayir.CustomBottomNavItem
import com.sevban.composetutorialssevbanbayir.R
import com.sevban.composetutorialssevbanbayir.bottomBarItems
import com.sevban.composetutorialssevbanbayir.utils.getScreenSizeDp


@Composable
fun CurvedBottomBar() {
    val bottomBarWidth = getScreenSizeDp().screenWidth
    val bottomBarHeight = 72.dp
    val curveHeight = 20.dp
    val gradientCenterHeight = getScreenSizeDp().screenHeight - bottomBarHeight
    val bottomBarBackgroundColor = Color.Black
    CustomBottomNavigationLayout(
        modifier = Modifier
            .background(bottomBarBackgroundColor)
            .drawWithCache {
                val gradient = Brush.linearGradient(
                    colors = listOf(
                        Color(0XFF00B100).copy(alpha = 0f),
                        Color(0XFF0DACE1),
                        Color(0XFF00B100).copy(alpha = 0f),
                    ),
                )
                val cp1 = Offset(bottomBarWidth.toPx() *.38f, 0f)
                val cp2 = Offset(bottomBarWidth.toPx() *.40f, -curveHeight.toPx())
                val cp3 = Offset(bottomBarWidth.toPx() *.57f, -curveHeight.toPx())
                val cp4 = Offset(bottomBarWidth.toPx() *.61f, 0f)
                onDrawBehind {
                    val path = Path().apply {
                        moveTo(0f, 0f)
                        lineTo(300f,0f)
                        cubicTo(
                            x1 = cp1.x,
                            y1 = cp1.y,
                            x2 = cp2.x,
                            y2 = cp2.y,
                            x3 = bottomBarWidth.toPx() / 2,
                            y3 = -curveHeight.toPx()
                        )
                        cubicTo(
                            x1 = cp3.x,
                            y1 = cp3.y,
                            x2 = cp4.x,
                            y2 = cp4.y,
                            x3 = cp4.x + 100F,
                            y3 = 0f
                        )
                        lineTo(bottomBarWidth.toPx(),0f)
                    }
                    drawPath(
                        path = path,
                        color = bottomBarBackgroundColor,
                    )
                    drawPath(
                        path = path,
                        brush = gradient,
                        style = Stroke(10f)
                    )

                }
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










@Composable
fun CustomBottomNavigationLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->

        val placeables = measurables.map {
            it.measure(constraints = constraints)
        }

        val gap = calculateGap(placeables, constraints.maxWidth)
        val otherItemsYPos = placeables.minOf { it.height } / 4

        layout(
            width = constraints.maxWidth,
            height = placeables.minOf { it.height } + 40
        ) {
            var xPos = gap
            placeables.forEachIndexed { index, placeable ->
                println(placeable.width)
                placeable.place(
                    x = xPos,
                    y = if (index == 2) -placeable.height / 4 else otherItemsYPos
                )
                xPos += placeable.width + gap
            }
        }
    }


}

fun calculateGap(placeables: List<Placeable>, width: Int): Int {
    val allWidth = placeables.sumOf { it.width }
    return (width - allWidth) / (placeables.size + 1)
}
