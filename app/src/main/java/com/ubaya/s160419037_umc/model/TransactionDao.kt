package com.ubaya.s160419037_umc.model

import androidx.room.*

@Dao
interface TransactionDao {
    @Query("SELECT * FROM `transaction` WHERE user= :username")
        suspend fun selectAllTransaction(username: String): List<Transaction>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)
}