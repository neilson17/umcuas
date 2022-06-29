package com.ubaya.s160419037_umc.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DoctorDao {
    @Query("SELECT * FROM doctor")
    suspend fun selectAllDoctor(): List<Doctor>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg doctor: Doctor)

    @Query("SELECT * FROM doctor WHERE uuid= :id")
    suspend fun selectDoctor(id:Int): Doctor
}
