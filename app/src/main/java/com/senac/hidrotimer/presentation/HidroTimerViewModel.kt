package com.senac.hidrotimer.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.senac.hidrotimer.data.HidroTimerDatabase
import com.senac.hidrotimer.data.HidroTimerRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class HidroTimerViewModel(application: Application) : AndroidViewModel(application) {
    private val database = HidroTimerDatabase.getDatabase(application)
    private val repository = HidroTimerRepository(
        database.aguaDao(),
        database.metaDao()
    )

    private val _totalIngerido = MutableStateFlow(0)
    val totalIngerido: StateFlow<Int> = _totalIngerido.asStateFlow()

    private val _metaDiaria = MutableStateFlow(3000)
    val metaDiaria: StateFlow<Int> = _metaDiaria.asStateFlow()

    private var ultimoDiaVerificado = getDiaAtual()

    private fun getDiaAtual(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.DAY_OF_YEAR) + calendar.get(Calendar.YEAR) * 1000
    }

    init {
        // Inicializar meta se não existir
        viewModelScope.launch {
            repository.inicializarMeta()
        }

        // Observar mudanças no banco
        viewModelScope.launch {
            repository.getTotalIngerido().collect { total ->
                _totalIngerido.value = total
                ultimoDiaVerificado = getDiaAtual()
            }
        }

        viewModelScope.launch {
            repository.getMeta().collect { meta ->
                _metaDiaria.value = meta
            }
        }

        // Verificar mudança de dia periodicamente
        viewModelScope.launch {
            while (true) {
                delay(60000) // Verifica a cada minuto
                val diaAtual = getDiaAtual()
                if (diaAtual != ultimoDiaVerificado) {
                    // Força atualização do total quando o dia muda
                    ultimoDiaVerificado = diaAtual
                    val novoTotal = repository.getTotalIngeridoSync()
                    _totalIngerido.value = novoTotal
                }
            }
        }
    }

    fun adicionarAgua(quantidade: Int) {
        viewModelScope.launch {
            repository.adicionarAgua(quantidade)
        }
    }

    fun atualizarMeta(valor: Int) {
        viewModelScope.launch {
            repository.atualizarMeta(valor)
        }
    }
}


