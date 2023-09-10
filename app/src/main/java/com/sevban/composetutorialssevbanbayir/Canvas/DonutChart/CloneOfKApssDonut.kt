package com.sevban.composetutorialssevbanbayir.Canvas.DonutChart

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun DonutChartWithCenteredText(
    donutInput: List<PieChartInput>,
    modifier: Modifier = Modifier,
    radius: Float = 500f,
    innerRadius: Float = 250f,
    transparentBorderWidth: Float = 75f,
    centerText: String = "",
) {
    var circleCenterOffset by remember { mutableStateOf(Offset.Zero) }
    var inputState by remember { mutableStateOf(donutInput) }
    var isTapped by remember { mutableStateOf(false) }

    Canvas(
        modifier = modifier
            .pointerInput(Unit) { //TODO
            },
    ) {
        val height = size.height
        val width = size.width
        val totalValue = inputState.sumOf { it.value }
        val unitAngle = 360f / totalValue
        circleCenterOffset = Offset(width / 2, height / 2)

        var currentSweepAngle = 0f

        inputState.forEach { pieChartInput ->
            val scale = if (pieChartInput.isTapped) 1.1f else 1f
            val sweepAngle = pieChartInput.value * unitAngle

            scale(scale) {
                drawArc(
                    color = pieChartInput.color,
                    startAngle = currentSweepAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    size = Size(
                        width = radius * 2f,
                        height = radius * 2f
                    ),
                    topLeft = Offset(
                        (width - radius * 2f) / 2f,
                        (height - radius * 2f) / 2f
                    )
                )
                currentSweepAngle += sweepAngle
            }
        }

    }
}