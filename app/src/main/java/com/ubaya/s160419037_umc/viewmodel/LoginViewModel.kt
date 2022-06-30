package com.ubaya.s160419037_umc.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.s160419037_umc.GlobalData
import com.ubaya.s160419037_umc.model.Login
import com.ubaya.s160419037_umc.model.User
import com.ubaya.s160419037_umc.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val loginLiveData = MutableLiveData<User>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh() {
        launch {
//            val db = Room.databaseBuilder(
//                getApplication(),
//                UserDatabase::class.java, "newuserdb").build()

//            loginLiveData.value = db.userDAO().selectAllUser()
        }
    }

    fun fetch(username: String, password: String){
        launch {
            val db = buildDb(getApplication())
            loginLiveData.value = db.userDao().login(username, password)
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    fun update(username: String, name: String, password: String, email: String, phone_number: String, address: String) {
        launch {
            val db = buildDb(getApplication())
            db.userDao().update(username, name, password, email, phone_number, address)
        }
    }

    fun registerUser(list: List<User>) {
        launch {
            val db = buildDb(getApplication())
            db.userDao().insertAll(*list.toTypedArray())
        }
    }
}