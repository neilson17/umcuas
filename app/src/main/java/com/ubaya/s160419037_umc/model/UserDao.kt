package com.ubaya.s160419037_umc.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun selectAllUser(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg user: User)

    @Query("SELECT * FROM user WHERE username= :username")
    suspend fun selectUser(username:String): User

    @Query("SELECT * FROM user WHERE username= :username AND password= :password")
    suspend fun login(username:String, password:String): User

    @Query("UPDATE user SET name=:name, password=:password, email=:email, phone=:phone_number, address=:address WHERE username=:username")
    suspend fun update(username:String, name: String, password: String, email: String, phone_number: String, address: String)
}