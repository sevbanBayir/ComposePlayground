package com.sevban.composetutorialssevbanbayir.special_components.curvedbottomnav.b_2

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomAppBarCutoutOffset
import androidx.compose.material.BottomAppBarRoundedEdgeRadius
import androidx.compose.material.FabPlacement
import androidx.compose.material.calculateCutoutCircleYIntercept
import androidx.compose.material.calculateRoundedEdgeIntercept
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun BottomBarLayout(
    content: @Composable RowScope.() -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .drawBehind {
            val width = size.width
            val height = size.height

        }) {

    }
}

private data class BottomAppBarCutoutShape(
    val cutoutShape: Shape,
    val fabPlacement: com.sevban.composetutorialssevbanbayir.special_components.curvedbottomnav.b_2.FabPlacement
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val boundingRectangle = Path().apply {
            addRect(Rect(0f, 0f, size.width, size.height))
        }
        val path = Path().apply {
            addCutoutShape(layoutDirection, density)
            // Subtract this path from the bounding rectangle
            op(boundingRectangle, this, PathOperation.Difference)
        }
        return Outline.Generic(path)
    }

    /**
     * Adds the filled [cutoutShape] to the [Path]. The path can the be subtracted from the main
     * rectangle path used for the app bar, to create the resulting cutout shape.
     */
    private fun Path.addCutoutShape(layoutDirection: LayoutDirection, density: Density) {
        // The gap on all sides between the FAB and the cutout
        val cutoutOffset = with(density) { BottomAppBarCutoutOffset.toPx() }

        val cutoutSize = Size(
            width = fabPlacement.width + (cutoutOffset * 2),
            height = fabPlacement.height + (cutoutOffset * 2)
        )

        val cutoutStartX = fabPlacement.left - cutoutOffset
        val cutoutEndX = cutoutStartX + cutoutSize.width

        val cutoutRadius = cutoutSize.height / 2f
        // Shift the cutout up by half its height, so only the bottom half of the cutout is actually
        // cut into the app bar
        val cutoutStartY = -cutoutRadius

        addOutline(cutoutShape.createOutline(cutoutSize, layoutDirection, density))
        translate(Offset(cutoutStartX, cutoutStartY))

        // TODO: consider exposing the custom cutout shape instead of just replacing circle shapes?
        if (cutoutShape == CircleShape) {
            val edgeRadius = with(density) { BottomAppBarRoundedEdgeRadius.toPx() }
            // TODO: possibly support providing a custom vertical offset?
            addRoundedEdges(cutoutStartX, cutoutEndX, cutoutRadius, edgeRadius, 0f)
        }
    }

    /**
     * Adds rounded edges to the [Path] representing a circular cutout in a BottomAppBar.
     *
     * Adds a curve for the left and right edges, with a straight line drawn between them - this
     * combined with the cutout shape results in the overall cutout path that can be subtracted
     * from the bounding rect of the app bar.
     *
     * @param cutoutStartPosition the absolute start position of the cutout
     * @param cutoutEndPosition the absolute end position of the cutout
     * @param cutoutRadius the radius of the cutout's circular edge - for a typical circular FAB
     * this will just be the radius of the circular cutout, but in the case of an extended FAB, we
     * can model this as two circles on either side attached to a rectangle.
     * @param roundedEdgeRadius how far from the points where the cutout intersects with the app bar
     * should the rounded edges be drawn to.
     * @param verticalOffset how far the app bar is from the center of the cutout circle
     */
    private fun Path.addRoundedEdges(
        cutoutStartPosition: Float,
        cutoutEndPosition: Float,
        cutoutRadius: Float,
        roundedEdgeRadius: Float,
        verticalOffset: Float
    ) {
        // Where the cutout intersects with the app bar, as if the cutout is not vertically aligned
        // with the app bar, the intersect will not be equal to the radius of the circle.
        val appBarInterceptOffset = calculateCutoutCircleYIntercept(cutoutRadius, verticalOffset)
        val appBarInterceptStartX = cutoutStartPosition + (cutoutRadius + appBarInterceptOffset)
        val appBarInterceptEndX = cutoutEndPosition - (cutoutRadius + appBarInterceptOffset)

        // How far the control point is away from the cutout intercept. We set this to be as small
        // as possible so that we have the most 'rounded' curve.
        val controlPointOffset = 1f

        // How far the control point is away from the center of the radius of the cutout
        val controlPointRadiusOffset = appBarInterceptOffset - controlPointOffset

        // The coordinates offset from the center of the radius of the cutout, where we should
        // draw the curve to
        val (curveInterceptXOffset, curveInterceptYOffset) = calculateRoundedEdgeIntercept(
            controlPointRadiusOffset,
            verticalOffset,
            cutoutRadius
        )

        // Convert the offset relative to the center of the cutout circle into an absolute
        // coordinate, by adding the radius of the shape to get a pure relative offset from the
        // leftmost edge, and then positioning it next to the cutout
        val curveInterceptStartX = cutoutStartPosition + (curveInterceptXOffset + cutoutRadius)
        val curveInterceptEndX = cutoutEndPosition - (curveInterceptXOffset + cutoutRadius)

        // Convert the curveInterceptYOffset which is relative to the center of the cutout, to an
        // absolute position
        val curveInterceptY = curveInterceptYOffset - verticalOffset

        // Where the rounded edge starts
        val roundedEdgeStartX = appBarInterceptStartX - roundedEdgeRadius
        val roundedEdgeEndX = appBarInterceptEndX + roundedEdgeRadius

        moveTo(roundedEdgeStartX, 0f)
        quadraticBezierTo(
            appBarInterceptStartX - controlPointOffset,
            0f,
            curveInterceptStartX,
            curveInterceptY
        )
        lineTo(curveInterceptEndX, curveInterceptY)
        quadraticBezierTo(appBarInterceptEndX + controlPointOffset, 0f, roundedEdgeEndX, 0f)
        close()
    }
}

@Immutable
internal class FabPlacement(
    val isDocked: Boolean,
    val left: Int,
    val width: Int,
    val height: Int
)
private val FabSpacing = 16.dp

private val AppBarHeight = 56.dp
// TODO: this should probably be part of the touch target of the start and end icons, clarify this
private val AppBarHorizontalPadding = 4.dp
// Start inset for the title when there is no navigation icon provided
private val TitleInsetWithoutIcon = Modifier.width(16.dp - AppBarHorizontalPadding)
// Start inset for the title when there is a navigation icon provided
private val TitleIconModifier = Modifier.fillMaxHeight()
    .width(72.dp - AppBarHorizontalPadding)

// The gap on all sides between the FAB and the cutout
private val BottomAppBarCutoutOffset = 8.dp
// How far from the notch the rounded edges start
private val BottomAppBarRoundedEdgeRadius = 4.dp