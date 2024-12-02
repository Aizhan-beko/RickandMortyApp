package com.geeks.rickandmortyapp.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.customCardWithBottomRightPlus(
    outerBorderColor: Color = Color.Gray,
    outerBorderWidth: Dp = 10.dp,
    outerCornerRadius: Dp = 12.dp,
    innerBackgroundColor: Color = Color.DarkGray,
    innerCornerRadius: Dp = 8.dp,
    innerPadding: Dp = 8.dp,
    plusColor: Color = Color.Red,
    plusSize: Float = 80f
): Modifier {
    return this
        .drawBehind {

            drawRoundRect(
                color = outerBorderColor,
                size = size,
                style = androidx.compose.ui.graphics.drawscope.Stroke(outerBorderWidth.toPx()),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(outerCornerRadius.toPx())
            )
            val innerOffset = outerBorderWidth.toPx() + innerPadding.toPx()
            drawRoundRect(
                color = innerBackgroundColor,
                size = size.copy(
                    width = size.width - innerOffset * 2,
                    height = size.height - innerOffset * 2
                ),
                topLeft = Offset(innerOffset, innerOffset),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(innerCornerRadius.toPx())
            )
            val plusCenterX = size.width - plusSize - innerOffset - 8.dp.toPx()
            val plusCenterY = size.height - plusSize - innerOffset - 8.dp.toPx()
            drawPlus(Offset(plusCenterX, plusCenterY), plusColor, plusSize)
        }
}
private fun DrawScope.drawPlus(center: Offset, color: Color, size: Float) {
    val halfSize = size / 2
    val strokeWidth = size / 6

    drawRect(
        color = color,
        topLeft = Offset(center.x - halfSize, center.y - strokeWidth / 2),
        size = androidx.compose.ui.geometry.Size(width = size, height = strokeWidth)
    )
    drawRect(
        color = color,
        topLeft = Offset(center.x - strokeWidth / 2, center.y - halfSize),
        size = androidx.compose.ui.geometry.Size(width = strokeWidth, height = size)
    )
}
