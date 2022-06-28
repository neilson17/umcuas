package com.ubaya.s160419037_umc.model

import androidx.room.ColumnInfo
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
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}

data class Medicine (
    val id: Int,
    var name: String,
    var variant: String,
    var price: Int,
    var description: String?,
    var photo_url: String,
    var medicine_category: String
)

data class Appointment (
    val user: User,
    val doctor: Doctor,
    var time: String
)

data class Transaction (
    val user: User,
    val medicine: Medicine,
    var quantity: Int,
    var total: Int,
    var time: String
)

@Entity
data class News (
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "photo_url")
    var photo_url: String,
    @ColumnInfo(name = "date")
    var date: String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}

data class Login (
    var user: User?,
    var status: String
)