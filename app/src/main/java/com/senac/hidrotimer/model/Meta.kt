package com.senac.hidrotimer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meta")
data class Meta(
    @PrimaryKey
    val id: Int = 1, // Sempre terá apenas uma meta
    val valor: Int
)
