package com.senac.hidrotimer.presentation.theme

import androidx.wear.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.senac.hidrotimer.R

val RetroFont = FontFamily(
    Font(R.font.retrobit)
)

val AppTypography = Typography(
    body1 = TextStyle(
        fontFamily = RetroFont,
        fontWeight = FontWeight.Normal
    ),
    body2 = TextStyle(
        fontFamily = RetroFont,
        fontWeight = FontWeight.Normal
    ),
    button = TextStyle(
        fontFamily = RetroFont,
        fontWeight = FontWeight.Bold
    ),
    title1 = TextStyle(
        fontFamily = RetroFont,
        fontWeight = FontWeight.Bold
    ),
    title2 = TextStyle(
        fontFamily = RetroFont,
        fontWeight = FontWeight.Bold
    )
)
