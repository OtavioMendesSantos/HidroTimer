package com.senac.hidrotimer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
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
    val viewModel: HidroTimerViewModel = viewModel()
    val totalIngerido by viewModel.totalIngerido.collectAsState()
    val metaDiaria by viewModel.metaDiaria.collectAsState()

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
                onNavigateMeta = { navController.navigate("alterarMeta") },
                onNavigateEasterEgg = { navController.navigate("easterEgg") }
            )
        }

        composable("addWater") {
            AddWaterScreen(
                viewModel = viewModel,
                metaDiaria = metaDiaria,
                onBack = { navController.popBackStack() },
                onMetaAtingida = { navController.navigate("metaAtingida") }
            )
        }

        composable("alterarMeta") {
            AlterarMetaScreen(
                metaAtual = metaDiaria,
                onConfirm = { novaMeta ->
                    viewModel.atualizarMeta(novaMeta)
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable("metaAtingida") {
            MetaAtingidaScreen(
                onBack = { navController.navigate("home") { popUpTo(0) } }
            )
        }

        composable("easterEgg") {
            EasterEggScreen(
                onDismiss = { navController.popBackStack() }
            )
        }
    }
}
