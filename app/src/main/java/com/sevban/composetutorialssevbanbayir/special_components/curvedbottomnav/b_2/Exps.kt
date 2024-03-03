package com.sevban.composetutorialssevbanbayir.special_components.curvedbottomnav.b_2

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sevban.composetutorialssevbanbayir.ui.theme.ComposeTutorialsSevbanBayirTheme
import com.sevban.composetutorialssevbanbayir.utils.getScreenSizeDp

@Composable
fun CurvedRect(
    modifier: Modifier = Modifier
) {

    val curveSize = 120.dp
    val bottomBarWidth = getScreenSizeDp().screenWidth
    val bottomBarHeight = 60.dp
    val cpHeight = 20.dp
    val screenHeight = getScreenSizeDp().screenHeight
    val bottomBarBackgroundColor = MaterialTheme.colorScheme.onBackground

    Canvas(modifier = Modifier.size(450.dp)) {
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
}


@Preview(apiLevel = 31)
@Composable
fun CurvedRectPrev() {
    ComposeTutorialsSevbanBayirTheme {
        CurvedRect()
    }


}