package com.geeks.rickandmortyapp.extensions

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun Modifier.customCardWithDoubleBordersAndPadding(
    outerBorderColor: Color = Color.Blue,
    outerBorderWidth: Dp = 6.dp,
    innerBorderColor: Color = Color.DarkGray,
    innerBorderWidth: Dp = 6.dp,
    padding: Dp = 8.dp
): Modifier = this
    .border(outerBorderWidth, outerBorderColor, RoundedCornerShape(8.dp))
    .padding(padding)
    .border(innerBorderWidth, innerBorderColor, RoundedCornerShape(8.dp))

fun Modifier.customCardContentPadding(
    padding: Dp = 16.dp
): Modifier = this.padding(padding)
