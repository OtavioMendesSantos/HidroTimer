package com.senac.hidrotimer.presentation

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senac.hidrotimer.R
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.wear.compose.material.MaterialTheme

@Composable
fun AddWaterScreen(
    viewModel: HidroTimerViewModel,
    metaDiaria: Int,
    onBack: () -> Unit,
    onMetaAtingida: () -> Unit
) {
    var quantidade by remember { mutableStateOf(0) }
    val totalIngerido by viewModel.totalIngerido.collectAsState()
    val context = LocalContext.current
    var aguardandoVerificacao by remember { mutableStateOf(false) }

    // Verificar meta quando totalIngerido for atualizado
    LaunchedEffect(totalIngerido) {
        if (aguardandoVerificacao && totalIngerido >= metaDiaria) {
            vibrar(context)
            onMetaAtingida()
            aguardandoVerificacao = false
        }
    }

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
                text = "ADICIONAR ÁGUA:",
                color = Color(0xFF003366),
                fontSize = (18f * scale).sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.title1
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
                            if (quantidade > 0) quantidade -= 50
                        }
                )

                Spacer(modifier = Modifier.width((15f * scale).dp))

                // QUANTIDADE CENTRAL
                Text(
                    text = "${quantidade} ml",
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
                        .clickable { quantidade += 50 }
                )
            }

            Spacer(modifier = Modifier.height((30f * scale).dp))

            Image(
                painter = painterResource(id = R.drawable.botao_ok),
                contentDescription = "Adicionar",
                modifier = Modifier
                    .width((150f * scale).dp)
                    .height((55f * scale).dp)
                    .clickable {
                        if (quantidade > 0) {
                            val novoTotal = totalIngerido + quantidade
                            viewModel.adicionarAgua(quantidade)
                            
                            // Verificar se atingiu ou ultrapassou a meta
                            if (novoTotal >= metaDiaria) {
                                aguardandoVerificacao = true
                                // A verificação final será feita pelo LaunchedEffect
                            } else {
                                onBack()
                            }
                        }
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

private fun vibrar(context: Context) {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator?.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        @Suppress("DEPRECATION")
        vibrator?.vibrate(500)
    }
}
