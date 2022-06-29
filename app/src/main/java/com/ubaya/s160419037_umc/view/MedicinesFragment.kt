package com.ubaya.s160419037_umc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.viewmodel.MedicineListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_medicines.*
import kotlinx.android.synthetic.main.fragment_news.*

class MedicinesFragment : Fragment() {
    private lateinit var viewModel: MedicineListViewModel
    private val medicineListAdapter = MedicineListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).bottomNav.visibility = View.GONE

        viewModel = ViewModelProvider(this).get(MedicineListViewModel::class.java)
        viewModel.refresh()

        recViewMedicines.layoutManager = LinearLayoutManager(context)
        recViewMedicines.adapter = medicineListAdapter

        observeViewModel()

        medicinesRefreshLayout.setOnRefreshListener {
//            recViewMedicines.visibility = View.GONE
//            textErrorMedicines.visibility = View.GONE
//            progressLoadMedicines.visibility = View.VISIBLE
//            viewModel.refresh()
//            medicinesRefreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.medicinesLiveData.observe(viewLifecycleOwner, Observer {
            medicineListAdapter.updateMedicineList(it)
            if (it.isEmpty()) {
                textErrorMedicines.visibility = View.VISIBLE
                progressLoadMedicines.visibility = View.VISIBLE
            }
            else {
                textErrorMedicines.visibility = View.GONE
                progressLoadMedicines.visibility = View.GONE
            }
        })
//        viewModel.medicinesLiveData.observe(viewLifecycleOwner){
//            medicineListAdapter.updateMedicineList(it)
//        }
//        viewModel.medicinesLoadErrorLiveData.observe(viewLifecycleOwner){
//            textErrorMedicines.visibility = if (it) View.VISIBLE else View.GONE
//        }
//        viewModel.loadingLiveData.observe(viewLifecycleOwner){
//            if (it){
//                progressLoadMedicines.visibility = View.VISIBLE
//                recViewMedicines.visibility = View.GONE
//            }
//            else {
//                progressLoadMedicines.visibility = View.GONE
//                recViewMedicines.visibility = View.VISIBLE
//            }
//        }
    }
}