package com.senac.hidrotimer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agua")
data class Agua(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val quantidade: Int,
    val timestamp: Long = System.currentTimeMillis()
)
