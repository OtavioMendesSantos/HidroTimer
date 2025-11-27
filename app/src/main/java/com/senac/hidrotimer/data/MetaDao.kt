package com.senac.hidrotimer.data

import androidx.room.*
import com.senac.hidrotimer.model.Meta
import kotlinx.coroutines.flow.Flow

@Dao
interface MetaDao {
    @Query("SELECT * FROM meta WHERE id = 1")
    fun getMeta(): Flow<Meta?>

    @Query("SELECT * FROM meta WHERE id = 1")
    suspend fun getMetaSync(): Meta?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meta: Meta)

    @Update
    suspend fun update(meta: Meta)
}
