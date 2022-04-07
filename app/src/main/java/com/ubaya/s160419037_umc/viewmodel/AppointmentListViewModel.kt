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
import com.ubaya.s160419037_umc.model.Appointment

class AppointmentListViewModel(application: Application): AndroidViewModel(application) {
    val appointmentsLiveData = MutableLiveData<ArrayList<Appointment>>()
    val appointmentsLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(username: String){
        appointmentsLoadErrorLiveData.value = false
        loadingLiveData.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = GlobalData.php_base_url + "appointments.php?username=$username"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Appointment>>() {}.type
                val result = Gson().fromJson<ArrayList<Appointment>>(it, sType)
                appointmentsLiveData.value = result
                loadingLiveData.value = false
                Log.d("showVolley", it)
            },
            {
                loadingLiveData.value = false
                appointmentsLoadErrorLiveData.value = true
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