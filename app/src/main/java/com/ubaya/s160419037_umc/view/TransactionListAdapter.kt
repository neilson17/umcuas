package com.ubaya.s160419037_umc.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.databinding.TransactionListItemBinding
import com.ubaya.s160419037_umc.model.Transaction
import com.ubaya.s160419037_umc.util.loadImage
import kotlinx.android.synthetic.main.transaction_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TransactionListAdapter (val transactionList: ArrayList<Transaction>, val adapterOnClick : (Transaction) -> Unit) : RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder>(), ButtonDeleteTransaction {
    class TransactionViewHolder(var view: TransactionListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<TransactionListItemBinding>(inflater, R.layout.transaction_list_item, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactionList[position]
        holder.view.transactions = transaction
        holder.view.buttonDeleteTransactionListener = this
    }

    override fun getItemCount() = transactionList.size

    fun updateTransactionList(newList : List<Transaction>){
        transactionList.clear()
        transactionList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onButtonDeleteTransaction(v: View, obj: Transaction) {
        adapterOnClick(obj)
    }
}