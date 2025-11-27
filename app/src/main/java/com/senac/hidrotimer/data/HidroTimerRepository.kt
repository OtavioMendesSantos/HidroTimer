package com.senac.hidrotimer.data

import com.senac.hidrotimer.model.Agua
import com.senac.hidrotimer.model.Meta
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar

class HidroTimerRepository(
    private val aguaDao: AguaDao,
    private val metaDao: MetaDao
) {
    private fun getInicioDoDia(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    private fun getFimDoDia(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar.timeInMillis
    }

    fun getTotalIngerido(): Flow<Int> {
        return aguaDao.getAll().map { listaAgua ->
            val inicioDoDia = getInicioDoDia()
            val fimDoDia = getFimDoDia()
            listaAgua
                .filter { it.timestamp >= inicioDoDia && it.timestamp < fimDoDia }
                .sumOf { it.quantidade }
        }
    }

    fun getMeta(): Flow<Int> {
        return metaDao.getMeta().map { it?.valor ?: 3000 }
    }

    suspend fun adicionarAgua(quantidade: Int) {
        aguaDao.insert(Agua(quantidade = quantidade))
    }

    suspend fun atualizarMeta(valor: Int) {
        val meta = Meta(id = 1, valor = valor)
        metaDao.insert(meta)
    }

    suspend fun inicializarMeta() {
        val metaAtual = metaDao.getMetaSync()
        // Se não existe meta, cria uma padrão
        if (metaAtual == null) {
            metaDao.insert(Meta(id = 1, valor = 3000))
        }
    }

    suspend fun getTotalIngeridoSync(): Int {
        val inicioDoDia = getInicioDoDia()
        val fimDoDia = getFimDoDia()
        val listaAgua = aguaDao.getAllSync()
        return listaAgua
            .filter { it.timestamp >= inicioDoDia && it.timestamp < fimDoDia }
            .sumOf { it.quantidade }
    }
}
