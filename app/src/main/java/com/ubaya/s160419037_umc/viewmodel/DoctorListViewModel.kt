package com.ubaya.s160419037_umc.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.s160419037_umc.GlobalData
import com.ubaya.s160419037_umc.model.Doctor
import com.ubaya.s160419037_umc.model.DoctorDatabase
import com.ubaya.s160419037_umc.model.News
import com.ubaya.s160419037_umc.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DoctorListViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val doctorsLiveData = MutableLiveData<List<Doctor>>()
    val doctorsLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh(){
        doctorsLoadErrorLiveData.value = false
        loadingLiveData.value = true

        launch {
            val db = buildDb(getApplication())
//            val db = Room.databaseBuilder(getApplication(), DoctorDatabase::class.java, DB_NAME)
//                .addMigrations(MIGRATION_DOCTORS_1_2)
//                .build()
            doctorsLiveData.value = db.doctorDao().selectAllDoctor()
        }

//        queue = Volley.newRequestQueue(getApplication())
//        val url = GlobalData.php_base_url + "doctors.php"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object : TypeToken<ArrayList<Doctor>>() {}.type
//                val result = Gson().fromJson<ArrayList<Doctor>>(it, sType)
//                doctorsLiveData.value = result
//                loadingLiveData.value = false
//                Log.d("showVolley", it)
//            },
//            {
//                loadingLiveData.value = false
//                doctorsLoadErrorLiveData.value = true
//                Log.d("errorVolley", it.toString())
//            }
//        ).apply {
//            tag = "TAG"
//        }
//        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    fun addDoctor(list: List<Doctor>) {
        launch {
            val db = buildDb(getApplication())
            db.doctorDao().insertAll(*list.toTypedArray())
        }
    }
}