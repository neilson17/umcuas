package com.ubaya.s160419037_umc.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.s160419037_umc.GlobalData
import com.ubaya.s160419037_umc.model.Doctor
import com.ubaya.s160419037_umc.util.buildDbDoctor
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
            val db = buildDbDoctor(getApplication())
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
}