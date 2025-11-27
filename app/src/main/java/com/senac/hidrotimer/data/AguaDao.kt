package com.senac.hidrotimer.data

import androidx.room.*
import com.senac.hidrotimer.model.Agua
import kotlinx.coroutines.flow.Flow

@Dao
interface AguaDao {
    @Query("SELECT * FROM agua ORDER BY timestamp DESC")
    fun getAll(): Flow<List<Agua>>

    @Query("SELECT * FROM agua ORDER BY timestamp DESC")
    suspend fun getAllSync(): List<Agua>

    @Query("SELECT SUM(quantidade) FROM agua WHERE timestamp >= :inicioDoDia AND timestamp < :fimDoDia")
    fun getTotalIngerido(inicioDoDia: Long, fimDoDia: Long): Flow<Int?>

    @Insert
    suspend fun insert(agua: Agua)

    @Delete
    suspend fun delete(agua: Agua)

    @Query("DELETE FROM agua")
    suspend fun deleteAll()
}
