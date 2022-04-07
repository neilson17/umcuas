package com.ubaya.s160419037_umc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.s160419037_umc.GlobalData
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.viewmodel.TransactionListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_transactions.*

class TransactionsFragment : Fragment() {
    private lateinit var viewModel: TransactionListViewModel
    private val transactionListAdapter = TransactionListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transactions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).bottomNav.visibility = View.GONE
        imageEmptyTransactionList.visibility = View.GONE
        textEmptyTransactionList.visibility = View.GONE

        viewModel = ViewModelProvider(this).get(TransactionListViewModel::class.java)
        viewModel.refresh(GlobalData.activeUser.username!!)

        recViewTransactions.layoutManager = LinearLayoutManager(context)
        recViewTransactions.adapter = transactionListAdapter

        observeViewModel()

        transactionsRefreshLayout.setOnRefreshListener {
            recViewTransactions.visibility = View.GONE
            textErrorTransactions.visibility = View.GONE
            progressLoadTransactions.visibility = View.VISIBLE
            viewModel.refresh(GlobalData.activeUser.username!!)
            transactionsRefreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.transactionsLiveData.observe(viewLifecycleOwner){
            transactionListAdapter.updateTransactionList(it)

            if(it.size == 0){
                imageEmptyTransactionList.visibility = View.VISIBLE
                textEmptyTransactionList.visibility = View.VISIBLE
            }
        }
        viewModel.transactionsLoadErrorLiveData.observe(viewLifecycleOwner){
            textErrorTransactions.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if (it){
                progressLoadTransactions.visibility = View.VISIBLE
                recViewTransactions.visibility = View.GONE
            }
            else {
                progressLoadTransactions.visibility = View.GONE
                recViewTransactions.visibility = View.VISIBLE
            }
        }
    }
}