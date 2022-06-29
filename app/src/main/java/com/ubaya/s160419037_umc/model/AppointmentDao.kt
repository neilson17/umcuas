package com.ubaya.s160419037_umc.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppointmentDao {
    @Query("SELECT * FROM appointment WHERE user= :username")
    suspend fun selectAllAppointment(username:String): List<Appointment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg appointment: Appointment)

    @Query("SELECT * FROM appointment WHERE uuid= :id")
    suspend fun selectAppointment(id:Int): Appointment
}