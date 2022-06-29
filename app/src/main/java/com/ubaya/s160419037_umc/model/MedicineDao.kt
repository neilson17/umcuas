package com.ubaya.s160419037_umc.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MedicineDao {
    @Query("SELECT * FROM medicine")
    suspend fun selectAllMedicine(): List<Medicine>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg medicine: Medicine)

    @Query("SELECT * FROM medicine WHERE uuid= :id")
    suspend fun selectMedicine(id:Int): Medicine
}