package com.ubaya.s160419037_umc.model

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey
    val username: String?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "password")
    var password: String?,
    @ColumnInfo(name = "email")
    var email: String?,
    @ColumnInfo(name = "phone")
    var phone: String?,
    @ColumnInfo(name = "address")
    var address: String?,
    @ColumnInfo(name = "photo_url")
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

@Entity
data class Medicine (
    @PrimaryKey(autoGenerate = true)
    val uuid: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "variant")
    var variant: String,
    @ColumnInfo(name = "price")
    var price: Int,
    @ColumnInfo(name = "description")
    var description: String?,
    @ColumnInfo(name = "photo_url")
    var photo_url: String,
    @ColumnInfo(name = "medicine_category")
    var medicine_category: String
)

@Entity
data class Appointment (
    @ColumnInfo(name = "user")
    val user: String,
    @ColumnInfo(name = "doctor_id")
    val doctor_id: Int,
    @ColumnInfo(name = "doctor_name")
    val doctor_name: String,
    @ColumnInfo(name = "doctor_category")
    val doctor_category: String,
    @ColumnInfo(name = "doctor_photo")
    val doctor_photo: String,
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