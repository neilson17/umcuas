package com.ubaya.s160419037_umc.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.s160419037_umc.GlobalData
import com.ubaya.s160419037_umc.model.Doctor
import com.ubaya.s160419037_umc.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DoctorDetailViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val doctorLiveData = MutableLiveData<Doctor>()
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(doctor_id: Int){
        launch {
            val db = buildDb(getApplication())
            doctorLiveData.value = db.doctorDao().selectDoctor(doctor_id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}