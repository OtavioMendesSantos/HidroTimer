package com.senac.hidrotimer.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.wear.compose.material.MaterialTheme
import com.senac.hidrotimer.R

@Composable
fun HomeScreen(
    totalIngerido: Int,
    metaDiaria: Int,
    onNavigateAdd: () -> Unit,
    onNavigateMeta: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val scaleFactor = maxWidth.value / 320f

        Image(
            painter = painterResource(id = R.drawable.background_main),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = (28f * scaleFactor).dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Image(
                painter = painterResource(id = R.drawable.copo),
                contentDescription = "Copo",
                modifier = Modifier.size((48f * scaleFactor).dp)
            )

            Spacer(modifier = Modifier.height((10f * scaleFactor).dp))

            Text(
                text = "TOTAL INGERIDO:",
                color = Color(0xFF003366),
                fontSize = (15f * scaleFactor).sp,
                style = MaterialTheme.typography.title2
            )

            Text(
                text = "$totalIngerido ml",
                color = Color(0xFFFFA500),
                fontSize = (22f * scaleFactor).sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.title1
            )

            Spacer(modifier = Modifier.height((14f * scaleFactor).dp))

            Text(
                text = "META DIÁRIA:",
                color = Color(0xFF003366),
                fontSize = (15f * scaleFactor).sp,
                style = MaterialTheme.typography.title2
            )

            Text(
                text = "$metaDiaria ml",
                color = Color(0xFFFFA500),
                fontSize = (22f * scaleFactor).sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.title1
            )

            Spacer(modifier = Modifier.height((30f * scaleFactor).dp))

            Image(
                painter = painterResource(id = R.drawable.botao_addwater),
                contentDescription = "Adicionar Água",
                modifier = Modifier
                    .width((200f * scaleFactor).dp)
                    .height((60f * scaleFactor).dp)
                    .clickable { onNavigateAdd() }
            )

            Spacer(modifier = Modifier.height((8f * scaleFactor).dp))

            Image(
                painter = painterResource(id = R.drawable.botao_alterar_meta),
                contentDescription = "Alterar Meta",
                modifier = Modifier
                    .width((200f * scaleFactor).dp)
                    .height((60f * scaleFactor).dp)
                    .clickable { onNavigateMeta() }
            )
        }
    }
}
