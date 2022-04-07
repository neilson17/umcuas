package com.ubaya.s160419037_umc.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.model.Transaction
import com.ubaya.s160419037_umc.util.loadImage
import kotlinx.android.synthetic.main.transaction_list_item.view.*

class TransactionListAdapter (val transactionList: ArrayList<Transaction>) : RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder>() {
    class TransactionViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.transaction_list_item, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactionList[position]
        with (holder.view){
            textDateTransactionList.text = transaction.time
            textMedicineNameTransactionList.text = transaction.medicine.name
            textMedicineVariantTransactionList.text = transaction.medicine.variant
            textPriceQuantityTransactionList.text = transaction.medicine.price.toString() + " x" + transaction.quantity.toString()
            textTotalTransactionList.text = "Total ${transaction.total}"
            imageTransactionList.loadImage(transaction.medicine.photo_url, progressLoadTransactionList)
        }
    }

    override fun getItemCount() = transactionList.size

    fun updateTransactionList(newList : ArrayList<Transaction>){
        transactionList.clear()
        transactionList.addAll(newList)
        notifyDataSetChanged()
    }
}