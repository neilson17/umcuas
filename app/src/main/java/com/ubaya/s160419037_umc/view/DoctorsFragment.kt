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
import com.ubaya.s160419037_umc.model.Doctor
import com.ubaya.s160419037_umc.viewmodel.DoctorListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_doctors.*
import kotlinx.android.synthetic.main.fragment_news.*

class DoctorsFragment : Fragment() {
    private lateinit var viewModel: DoctorListViewModel
    private val doctorListAdapter = DoctorListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).bottomNav.visibility = View.GONE

        viewModel = ViewModelProvider(this).get(DoctorListViewModel::class.java)

        viewModel.refresh()

        recViewDoctors.layoutManager = LinearLayoutManager(context)
        recViewDoctors.adapter = doctorListAdapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.doctorsLiveData.observe(viewLifecycleOwner, Observer {
            doctorListAdapter.updateDoctorList(it)
            if (it.isEmpty()) {
                textErrorDoctors.visibility = View.VISIBLE
                progressLoadDoctors.visibility = View.VISIBLE
            }
            else {
                textErrorDoctors.visibility = View.GONE
                progressLoadDoctors.visibility = View.GONE
            }
        })
    }
}