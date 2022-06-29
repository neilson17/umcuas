package com.ubaya.s160419037_umc.model

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey


data class User (
    val username: String?,
    var name: String?,
    var password: String?,
    var email: String?,
    var phone: String?,
    var address: String?,
    var photo_url: String?
)

@Entity
data class Doctor (
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "gender")
    var gender: String,
    @ColumnInfo(name = "price")
    var price: Int,
    @ColumnInfo(name = "photo_url")
    var photo_url: String,
    @ColumnInfo(name = "doctor_category")
    var doctor_category: String,
    @ColumnInfo(name = "doctor_category_description")
    var doctor_category_description: String,
)
//{
//    @PrimaryKey(autoGenerate = true)
//    var uuid:Int = 0
//}

data class Medicine (
    val id: Int,
    var name: String,
    var variant: String,
    var price: Int,
    var description: String?,
    var photo_url: String,
    var medicine_category: String
)

@Entity
data class Appointment (
    @ColumnInfo(name = "user")
    val user: String,
    @ColumnInfo(name = "doctor")
    val doctor: String,
    @ColumnInfo(name = "time")
    var time: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}

data class Transaction (
    val user: User,
    val medicine: Medicine,
    var quantity: Int,
    var total: Int,
    var time: String
)

@Entity
data class News (
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "photo_url")
    var photo_url: String,
    @ColumnInfo(name = "date")
    var date: String
)
//{
//    @PrimaryKey(autoGenerate = true)
//    var uuid:Int = 0
//}

data class Login (
    var user: User?,
    var status: String
)