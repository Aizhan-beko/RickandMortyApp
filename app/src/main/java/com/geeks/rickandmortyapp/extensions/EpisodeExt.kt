package com.geeks.rickandmortyapp.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset

fun Modifier.customCardWithDoubleBorders(
    outerBorderColor: Color = Color.Yellow,
    outerBorderWidth: Dp = 6.dp,
    outerCornerRadius: Dp = 12.dp,
    innerBackgroundColor: Color = Color.DarkGray,
    innerCornerRadius: Dp = 8.dp,
    innerPadding: Dp = 8.dp
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
        }
}