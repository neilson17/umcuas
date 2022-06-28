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
import com.ubaya.s160419037_umc.model.NewsDatabase
import com.ubaya.s160419037_umc.util.buildDbNews
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
            val db = buildDbNews(getApplication())

            newsLiveData.value = db.newsDao().selectAllNews()
        }

//        queue = Volley.newRequestQueue(getApplication())
//        val url = GlobalData.php_base_url + "news.php"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object : TypeToken<ArrayList<News>>() {}.type
//                val result = Gson().fromJson<ArrayList<News>>(it, sType)
//                newsLiveData.value = result
//                loadingLiveData.value = false
//                Log.d("showVolley", it)
//            },
//            {
//                loadingLiveData.value = false
//                newsLoadErrorLiveData.value = true
//                Log.d("errorVolley", it.toString())
//            }
//        ).apply {
//            tag = "TAG"
//        }
//        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
//        queue?.cancelAll(TAG)
    }

    fun addNews(list: List<News>) {
        launch {
            val db = buildDbNews(getApplication())
            db.newsDao().insertAll(*list.toTypedArray())
        }
    }
}