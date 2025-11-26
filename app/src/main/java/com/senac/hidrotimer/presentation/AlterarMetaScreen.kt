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
import com.senac.hidrotimer.R
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.wear.compose.material.MaterialTheme

@Composable
fun AlterarMetaScreen(
    metaAtual: Int = 3000,
    onConfirm: (Int) -> Unit,
    onBack: () -> Unit
) {

    var meta by remember { mutableStateOf(metaAtual) }

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
                .padding(top = (35f * scale).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // TÍTULO
            Text(
                text = "DEFINIR META:",
                color = Color(0xFF003366),
                fontSize = (18f * scale).sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.title1   // ← FONTE APLICADA
            )

            Spacer(modifier = Modifier.height((20f * scale).dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.seta_esquerda),
                    contentDescription = "Diminuir",
                    modifier = Modifier
                        .size((45f * scale).dp)
                        .clickable {
                            if (meta > 0) meta -= 50
                        }
                )

                Spacer(modifier = Modifier.width((15f * scale).dp))

                // VALOR CENTRAL
                Text(
                    text = "${meta} ml",
                    color = Color(0xFFFFA500),
                    fontSize = (32f * scale).sp,
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.title1   // ← FONTE APLICADA
                )

                Spacer(modifier = Modifier.width((15f * scale).dp))

                Image(
                    painter = painterResource(id = R.drawable.seta_direita),
                    contentDescription = "Aumentar",
                    modifier = Modifier
                        .size((45f * scale).dp)
                        .clickable { meta += 50 }
                )
            }

            Spacer(modifier = Modifier.height((30f * scale).dp))

            Image(
                painter = painterResource(id = R.drawable.botao_ok),
                contentDescription = "Confirmar Meta",
                modifier = Modifier
                    .width((150f * scale).dp)
                    .height((55f * scale).dp)
                    .clickable {
                        onConfirm(meta)
                        onBack()
                    }
            )

            Spacer(modifier = Modifier.height((35f * scale).dp))

            Image(
                painter = painterResource(id = R.drawable.copo),
                contentDescription = null,
                modifier = Modifier.size((60f * scale).dp)
            )
        }
    }
}
