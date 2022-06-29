package com.ubaya.s160419037_umc.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(News::class, Doctor::class, Appointment::class), version = 1)
abstract class UmcDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun doctorDao(): DoctorDao
    abstract fun appointmentDao(): AppointmentDao

    companion object {
        @Volatile private var instance: UmcDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext, UmcDatabase::class.java, "umcdb").build()

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