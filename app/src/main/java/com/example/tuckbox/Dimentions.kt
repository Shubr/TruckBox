package com.example.tuckbox

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dim(
    val extraSmall: Dp = 4.dp,
    val small: Dp = 0.dp,
    val small1: Dp = 0.dp,
    val small2: Dp = 0.dp,
    val small3: Dp = 0.dp,
    val medium: Dp = 0.dp,
    val medium1: Dp = 0.dp,
    val medium2: Dp = 0.dp,
    val medium3: Dp = 0.dp,
    val large: Dp = 0.dp,
    val largeTopPadding: Dp = 0.dp,
    val mediumTopPadding: Dp = 0.dp,
    val smallTopPadding: Dp = 0.dp,
    val largeGapPadding: Dp = 0.dp,
    val mediumGapPadding: Dp = 0.dp,
    val smallGapPadding: Dp = 0.dp,
    val extraLarge: Dp = 0.dp
    )

val CompactSmallDim = Dim(
    extraSmall = 1.dp,
    small1 = 3.dp,
    small2 = 4.dp,
    small3 = 5.dp,
    medium = 6.dp,
    medium1 = 7.dp,
    medium2 = 8.dp,
    medium3 = 9.dp,
    large = 10.dp,
    largeTopPadding = 11.dp,
    mediumTopPadding = 12.dp,
    smallTopPadding = 13.dp,
    largeGapPadding = 14.dp,
    mediumGapPadding = 15.dp,
    smallGapPadding = 16.dp
)

val CompactMediumDim = Dim(

)

val CompactLargeDim = Dim(

)