package com.sevban.composetutorialssevbanbayir.Canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Polygon() {
    Surface(modifier = Modifier.size(200.dp)) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val cx = canvasWidth / 2f
            val cy = canvasHeight / 2f

            val radius = (canvasHeight - 20.dp.toPx()) / 2
            val path = createPolygonPath(cx, cy, 10, radius)

            drawPath(
                path = path,
                color = Color.Black,
                style = Stroke(
                    width = 4.dp.toPx(),
                    miter = 990f,
                    pathEffect = PathEffect.cornerPathEffect(40f),
                    join = StrokeJoin.Round
                )
            )
        }
    }
}

fun createPolygonPath(cx: Float, cy: Float, sides: Int, radius: Float): Path {
    val angle = 2.0 * Math.PI / sides

    return Path().apply {
        moveTo(
            cx + (radius * cos(0.0)).toFloat(),
            cy + (radius * sin(0.0)).toFloat()
        )
        for (i in 1 until sides) {
            lineTo(
                cx + (radius * cos(angle * i)).toFloat(),
                cy + (radius * sin(angle * i)).toFloat()
            )
        }
        close()
    }
}

@Composable
fun ConcaveDecagon() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val points = arrayListOf<Offset>()

        for (angle in 0 until 360 step 30) {
            points.add(
                Offset(
                    (cos(angle * Math.PI / 2) * 100f).toFloat(),
                    (sin(angle * Math.PI / 2) * 100f).toFloat()
                )
            )
        }

        drawPath(
            path = Path().apply {
                moveTo(points[0].x, points[0].y)
                for (point in points) {
                    lineTo(point.x, point.y)
                }
                close()
                // Round the corners
                for (i in 0 until 10) {
                    addArc(
                        oval = Rect.Zero,
                        startAngleDegrees = (i - 1) * 30f,
                        sweepAngleDegrees = 30f
                    )
                    // Add a concave curve
                    addArc(
                        oval = Rect.Zero,
                        startAngleDegrees = (i - 1) * 30f + 15f,
                        sweepAngleDegrees = -15f
                    )
                }
            },
            color = Color.Blue,
            style = Stroke(width = 5f)
        )
    }
}

