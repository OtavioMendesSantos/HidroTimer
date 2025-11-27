package com.senac.hidrotimer.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
fun MetaAtingidaScreen(
    onBack: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val scale = maxWidth.value / 320f

        Image(
            painter = painterResource(id = R.drawable.background_main),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = (20f * scale).dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Título de Parabéns
            Text(
                text = "PARABÉNS!",
                color = Color(0xFFFFA500),
                fontSize = (24f * scale).sp,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.title1
            )

            Spacer(modifier = Modifier.height((10f * scale).dp))

            Text(
                text = "META ATINGIDA!",
                color = Color(0xFF003366),
                fontSize = (18f * scale).sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.title2
            )

            Spacer(modifier = Modifier.height((20f * scale).dp))

            // Placeholder para a arte 8-bit do professor
            // Quando você tiver a imagem, substitua por:
            // Image(
            //     painter = painterResource(id = R.drawable.professor_pixel_art),
            //     contentDescription = "Professor com garrafinha",
            //     modifier = Modifier.size((120f * scale).dp)
            // )
            
            // Por enquanto, vamos usar o copo como placeholder
            Image(
                painter = painterResource(id = R.drawable.copo),
                contentDescription = "Professor com garrafinha",
                modifier = Modifier.size((100f * scale).dp)
            )

            Spacer(modifier = Modifier.height((30f * scale).dp))

            // Botão voltar
            Image(
                painter = painterResource(id = R.drawable.botao_ok),
                contentDescription = "Voltar para Home",
                modifier = Modifier
                    .width((180f * scale).dp)
                    .height((55f * scale).dp)
                    .clickable { onBack() }
            )
        }
    }
}


