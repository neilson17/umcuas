package com.ubaya.s160419037_umc.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.s160419037_umc.util.MIGRATION_DOCTORS_1_2

@Database(entities = arrayOf(Doctor::class), version = 2)
abstract class DoctorDatabase:RoomDatabase() {
    abstract fun doctorDao(): DoctorDao

    companion object {
        @Volatile private var instance: DoctorDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext, DoctorDatabase::class.java, "newdb").build()

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