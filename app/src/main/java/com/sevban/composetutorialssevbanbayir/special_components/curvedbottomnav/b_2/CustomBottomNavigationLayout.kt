package com.sevban.composetutorialssevbanbayir.special_components.curvedbottomnav.b_2

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable

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
