package com.susheelkaram.matrimonyapp.data.local.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.susheelkaram.matrimonyapp.data.model.MatchUser

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
@Database(entities = [MatchUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun matchesDao(): MatchesDao

    companion object {
        const val FILE_APP_DATABASE = "matches_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(app: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    app.applicationContext,
                    AppDatabase::class.java,
                    FILE_APP_DATABASE
                ).build()
                INSTANCE = instance

                return instance
            }
        }
    }
}