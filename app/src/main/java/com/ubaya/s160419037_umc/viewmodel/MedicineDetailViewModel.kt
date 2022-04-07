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
import com.ubaya.s160419037_umc.model.Medicine

class MedicineDetailViewModel(application: Application) : AndroidViewModel(application) {
    val medicineLiveData = MutableLiveData<Medicine>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(medicine_id: Int){
        queue = Volley.newRequestQueue(getApplication())
        val url = GlobalData.php_base_url + "medicines.php?medicine_id=$medicine_id"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Medicine>>() {}.type
                val result = Gson().fromJson<ArrayList<Medicine>>(it, sType)
                medicineLiveData.value = result[0]
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