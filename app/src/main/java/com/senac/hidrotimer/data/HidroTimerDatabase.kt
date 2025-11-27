package com.senac.hidrotimer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.senac.hidrotimer.model.Agua
import com.senac.hidrotimer.model.Meta

@Database(
    entities = [Agua::class, Meta::class],
    version = 1,
    exportSchema = false
)
abstract class HidroTimerDatabase : RoomDatabase() {
    abstract fun aguaDao(): AguaDao
    abstract fun metaDao(): MetaDao

    companion object {
        @Volatile
        private var INSTANCE: HidroTimerDatabase? = null

        fun getDatabase(context: Context): HidroTimerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HidroTimerDatabase::class.java,
                    "hidrotimer_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
