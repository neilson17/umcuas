package com.ubaya.s160419037_umc

import com.ubaya.s160419037_umc.model.User

object GlobalData {
    var loggedIn = false

    lateinit var activeUser : User
    var firstLightNotification = true
}