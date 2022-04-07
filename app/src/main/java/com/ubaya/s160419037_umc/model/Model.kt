package com.ubaya.s160419037_umc.model

data class User (
    val username: String?,
    var name: String?,
    var password: String?,
    var email: String?,
    var phone: String?,
    var address: String?,
    var photo_url: String?
)

data class Doctor (
    val id: Int,
    var name: String,
    var gender: String,
    var price: Int,
    var photo_url: String,
    var doctor_category: String,
    var doctor_category_description: String,
    var practice_hours: ArrayList<String>
)

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

data class News (
    val id: Int,
    var title: String,
    var description: String,
    var photo_url: String,
    var date: String
)

data class Login (
    var user: User?,
    var status: String
)