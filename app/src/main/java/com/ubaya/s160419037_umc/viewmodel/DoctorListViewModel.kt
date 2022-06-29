package com.ubaya.s160419037_umc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.ubaya.s160419037_umc.model.Doctor
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
            doctorsLiveData.value = db.doctorDao().selectAllDoctor()
        }
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