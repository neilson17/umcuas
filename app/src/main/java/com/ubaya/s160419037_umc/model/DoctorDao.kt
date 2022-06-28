package com.ubaya.s160419037_umc.model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface DoctorDao {
    @Query("SELECT * FROM doctor")
    suspend fun selectAllDoctor(): List<Doctor>
}
