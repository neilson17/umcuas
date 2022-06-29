package com.ubaya.s160419037_umc

import com.ubaya.s160419037_umc.model.User

object GlobalData {
    var loggedIn = false
    val php_base_url = "http://192.168.0.14/umc/"

    lateinit var activeUser : User
}