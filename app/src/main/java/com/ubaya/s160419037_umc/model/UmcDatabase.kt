package com.ubaya.s160419037_umc.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.s160419037_umc.util.MIGRATION_1_2

@Database(entities = [User::class, Medicine::class, News::class, Doctor::class, Appointment::class, Transaction::class], version = 2)
abstract class UmcDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun medicineDao(): MedicineDao
    abstract fun newsDao(): NewsDao
    abstract fun doctorDao(): DoctorDao
    abstract fun appointmentDao(): AppointmentDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile private var instance: UmcDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext, UmcDatabase::class.java, "umcdb").addMigrations(MIGRATION_1_2).build()

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