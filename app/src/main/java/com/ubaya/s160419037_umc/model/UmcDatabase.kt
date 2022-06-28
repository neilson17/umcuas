package com.ubaya.s160419037_umc.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.s160419037_umc.util.MIGRATION_1_2

@Database(entities = arrayOf(News::class, Doctor::class), version = 2)
abstract class UmcDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun doctorDao(): DoctorDao

    companion object {
        @Volatile private var instance: NewsDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext, NewsDatabase::class.java, "newdb").addMigrations(
                MIGRATION_1_2
            ).build()

        operator fun invoke(context: Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}