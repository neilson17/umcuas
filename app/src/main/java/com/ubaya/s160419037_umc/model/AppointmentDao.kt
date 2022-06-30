package com.ubaya.s160419037_umc.model

import androidx.room.*

@Dao
interface AppointmentDao {
    @Query("SELECT * FROM appointment WHERE user= :username")
    suspend fun selectAllAppointment(username:String): List<Appointment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg appointment: Appointment)

    @Delete
    suspend fun deleteAppointment(appointment: Appointment)
}