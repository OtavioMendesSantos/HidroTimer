package com.senac.hidrotimer.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.senac.hidrotimer.R

@Composable
fun EasterEggScreen(
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onDismiss() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.hidratese),
            contentDescription = "Hidrate-se",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}

