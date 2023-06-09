package com.countlesswrongs.cryptomonitor.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.countlesswrongs.cryptomonitor.data.database.dao.CoinInfoDao
import com.countlesswrongs.cryptomonitor.data.database.model.CoinInfoDbModel

@Database(entities = [CoinInfoDbModel::class], version = 3, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME: String = "main.db"
        private var db: AppDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room
                    .databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun coinPriceInfoDao(): CoinInfoDao

}
