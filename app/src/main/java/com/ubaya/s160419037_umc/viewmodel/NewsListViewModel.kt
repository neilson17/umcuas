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
import com.ubaya.s160419037_umc.model.News
import com.ubaya.s160419037_umc.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NewsListViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val newsLiveData = MutableLiveData<List<News>>()
    val newsLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh(){
        newsLoadErrorLiveData.value = false
        loadingLiveData.value = true

        launch {
//            val db = Room.databaseBuilder(getApplication(), NewsDatabase::class.java, "newnewsdb").addMigrations(MIGRATION_1_2).build()
            val db = buildDb(getApplication())

            newsLiveData.value = db.newsDao().selectAllNews()
        }
    }

    override fun onCleared() {
        super.onCleared()
//        queue?.cancelAll(TAG)
    }

    fun addNews(list: List<News>) {
        launch {
            val db = buildDb(getApplication())
            db.newsDao().insertAll(*list.toTypedArray())
        }
    }
}