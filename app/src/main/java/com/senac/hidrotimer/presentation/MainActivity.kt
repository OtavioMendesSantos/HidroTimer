package com.senac.hidrotimer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.senac.hidrotimer.presentation.theme.HidroTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HidroTimerTheme {
                HidroTimerApp()
            }
        }
    }
}

@Composable
fun HidroTimerApp() {

    // ESTADOS GLOBAIS
    var totalIngerido by remember { mutableStateOf(0) }
    var metaDiaria by remember { mutableStateOf(3000) }

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        composable("splash") {
            SplashScreen(navController)
        }

        composable("home") {
            HomeScreen(
                totalIngerido = totalIngerido,
                metaDiaria = metaDiaria,
                onNavigateAdd = { navController.navigate("addWater") },
                onNavigateMeta = { navController.navigate("alterarMeta") }
            )
        }

        composable("addWater") {
            AddWaterScreen(
                onAdd = { quantidade ->
                    totalIngerido += quantidade
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable("alterarMeta") {
            AlterarMetaScreen(
                metaAtual = metaDiaria,
                onConfirm = { novaMeta ->
                    metaDiaria = novaMeta
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
