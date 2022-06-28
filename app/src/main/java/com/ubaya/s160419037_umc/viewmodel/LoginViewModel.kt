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
import com.ubaya.s160419037_umc.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val loginLiveData = MutableLiveData<Login>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh() {
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                UserDatabase::class.java, "newuserdb").build()

//            loginLiveData.value = db.userDAO().selectAllUser()
        }
    }

    fun fetch(username: String, password: String){
        queue = Volley.newRequestQueue(getApplication())
        val url = GlobalData.php_base_url + "users.php?username=$username&password=$password"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Login>>() {}.type
                val result = Gson().fromJson<ArrayList<Login>>(it, sType)
                loginLiveData.value = result[0]
                Log.d("showVolley", it)
            },
            {
                Log.d("errorVolley", it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}