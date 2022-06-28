package com.ubaya.s160419037_umc.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.s160419037_umc.util.MIGRATION_NEWS_1_2

@Database(entities = arrayOf(News::class), version = 1)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile private var instance: NewsDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext, NewsDatabase::class.java, "newdb").addMigrations(MIGRATION_NEWS_1_2).build()

        operator fun invoke(context:Context) {
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