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
import com.ubaya.s160419037_umc.model.Transaction
import com.ubaya.s160419037_umc.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TransactionListViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val transactionsLiveData = MutableLiveData<List<Transaction>>()
    val transactionsLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh(username: String){
        transactionsLoadErrorLiveData.value = false
        loadingLiveData.value = true

        launch {
            val db = buildDb(getApplication())
            transactionsLiveData.value = db.transactionDao().selectAllTransaction(username)
        }
    }

    fun addTransaction(list: List<Transaction>) {
        launch {
            val db = buildDb(getApplication())
            db.transactionDao().insertAll(*list.toTypedArray())
        }
    }

    fun deleteTransaction(transaction: Transaction) {
        launch {
            val db = buildDb(getApplication())
            db.transactionDao().deleteTransaction(transaction)
            transactionsLiveData.value = db.transactionDao().selectAllTransaction(GlobalData.activeUser.username)
        }
    }
}