package com.sevban.composetutorialssevbanbayir.special_components.curvedbottomnav.b_2

import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import com.sevban.composetutorialssevbanbayir.CustomBottomNavItem
import com.sevban.composetutorialssevbanbayir.bottomBarItems
import com.sevban.composetutorialssevbanbayir.utils.getScreenSizeDp
import kotlin.math.max

@Composable
fun CurvedBottomBar() {
    val bottomBarWidth = getScreenSizeDp().screenWidth
    val curveHeight = 20.dp
    val bottomBarBackgroundColor = Color.Black
    val gradientColors =
        listOf(
            Color(0xFF4527A0),
            Color(0xFF4527A0),
        )
    CustomBottomNavigationLayout(
        modifier = Modifier
            .background(bottomBarBackgroundColor)
            .drawBottomBarCurve(gradientColors,bottomBarBackgroundColor)
    ) {
        bottomBarItems.forEachIndexed { index, item ->
            if (index == 2) {

                CustomBottomNavItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            tint = Color.White,
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

        val placeables =
            measurables.map {
                it.measure(constraints = constraints)
            }

        val gaps = calculateWeightedGap(placeables.map { it.width }, constraints.maxWidth)
        val otherItemsYPos = placeables.minOf { it.height } / 4

        layout(
            width = constraints.maxWidth,
            height = placeables.minOf { it.height } + 50 // Padding for icon texts
        ) {
            var xPos = gaps.first()
            placeables.forEachIndexed { index, placeable ->
                placeable.place(
                    x = if (index == 2) (constraints.maxWidth - placeable.width) / 2 else xPos,
                    y = if (index == 2) -placeable.height / 4 else otherItemsYPos
                )
                xPos += if (index == 2) placeable.width else placeable.width + gaps[index + 1]
            }
        }
    }
}

fun calculateWeightedGap(
    itemLengths: List<Int>,
    maxWidth: Int
): List<Int> {
    val allWidth = itemLengths.sumOf { it }
    val baseGap = (maxWidth - allWidth) / (itemLengths.size + 1)

    val gaps = mutableListOf(baseGap)

    for (itemLength in itemLengths) {
        val weight = itemLength.toFloat() / allWidth
        val gap = baseGap * (1.3 - weight)
        gaps.add(max(gap.toInt(), 0))
    }
    return gaps
}

private fun Modifier.drawBottomBarCurve(
    gradientColors: List<Color>,
    bottomBarBackgroundColor: Color
) = drawWithCache {
    val bottomBarWidth = size.width
    val curveHeight = 20.dp
    val cp1 = Offset(bottomBarWidth * .38f, 0f)
    val cp2 = Offset(bottomBarWidth * .40f, -curveHeight.toPx())
    val cp3 = Offset(bottomBarWidth * .57f, -curveHeight.toPx())
    val cp4 = Offset(bottomBarWidth * .61f, 0f)

    onDrawBehind {
        val gradientAndroid =
            LinearGradient(
                0f,
                0f,
                size.width,
                0f,
                gradientColors.map { it.toArgb() }.toIntArray(),
                null,
                Shader.TileMode.CLAMP
            )

        val path =
            android.graphics.Path()
                .apply {
                    moveTo(0f, 0f)
                    lineTo(bottomBarWidth * .32f, 0f)
                    cubicTo(
                        cp1.x,
                        cp1.y,
                        cp2.x,
                        cp2.y,
                        bottomBarWidth / 2,
                        -curveHeight.toPx()
                    )
                    cubicTo(
                        cp3.x,
                        cp3.y,
                        cp4.x,
                        cp4.y,
                        bottomBarWidth * .68f,
                        0f
                    )
                    lineTo(bottomBarWidth, 0f)
                }

        drawContext.canvas.nativeCanvas.apply {
            val paint = Paint(Paint.ANTI_ALIAS_FLAG)
            drawPath(
                path,
                paint.apply {
                    style = Paint.Style.FILL
                    color = bottomBarBackgroundColor.toArgb()
                }
            )
            drawPath(
                path,
                paint.apply {
                    style = Paint.Style.STROKE
                    shader = gradientAndroid
                    strokeWidth = 10f
                    setShadowLayer(
                        260f,
                        0f,
                        0f,
                        android.graphics.Color.BLACK
                    )
                }
            )
        }
    }
}
/*
fun Modifier.drawBottomBarCurve(
    bottomBarBackgroundColor: Color,
    gradientColors: List<Color>
) = drawWithCache {
    val bottomBarWidth = size.width
    val curveHeight = 20.dp
    val gradient = Brush.horizontalGradient(colors = gradientColors)
    val cp1 = Offset(bottomBarWidth * .38f, 0f)
    val cp2 = Offset(bottomBarWidth * .40f, -curveHeight.toPx())
    val cp3 = Offset(bottomBarWidth * .57f, -curveHeight.toPx())
    val cp4 = Offset(bottomBarWidth * .61f, 0f)
    onDrawBehind {
        val path =
            Path().apply {
                moveTo(0f, 0f)
                lineTo(bottomBarWidth * .32f, 0f)
                cubicTo(
                    x1 = cp1.x,
                    y1 = cp1.y,
                    x2 = cp2.x,
                    y2 = cp2.y,
                    x3 = bottomBarWidth / 2,
                    y3 = -curveHeight.toPx()
                )
                cubicTo(
                    x1 = cp3.x,
                    y1 = cp3.y,
                    x2 = cp4.x,
                    y2 = cp4.y,
                    x3 = bottomBarWidth * .68f,
                    y3 = 0f
                )
                lineTo(bottomBarWidth, 0f)
            }
        drawPath(
            path = path,
            color = bottomBarBackgroundColor
        )
        drawPath(
            path = path,
            brush = gradient,
            style = Stroke(5f)
        )
    }
}
*/
