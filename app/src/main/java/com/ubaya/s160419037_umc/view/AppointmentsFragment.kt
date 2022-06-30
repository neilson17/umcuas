package com.ubaya.s160419037_umc.view

import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.s160419037_umc.GlobalData
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.viewmodel.AppointmentListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_appointments.*

class AppointmentsFragment : Fragment() {
    private lateinit var viewModel: AppointmentListViewModel
    private val appointmentListAdapter = AppointmentListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appointments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).bottomNav.visibility = View.GONE
        imageEmptyAppointments.visibility = View.GONE
        textEmptyAppointments.visibility = View.GONE

        viewModel = ViewModelProvider(this).get(AppointmentListViewModel::class.java)
        viewModel.refresh(GlobalData.activeUser.username!!)

        recViewAppointments.layoutManager = LinearLayoutManager(context)
        recViewAppointments.adapter = appointmentListAdapter

        observeViewModel()

//        appointmentsRefreshLayout.setOnRefreshListener {
//            recViewAppointments.visibility = View.GONE
//            textErrorAppointments.visibility = View.GONE
//            progressLoadAppointments.visibility = View.VISIBLE
//            viewModel.refresh(GlobalData.activeUser.username!!)
//            appointmentsRefreshLayout.isRefreshing = false
//        }
    }

    private fun observeViewModel() {
        viewModel.appointmentsLiveData.observe(viewLifecycleOwner, Observer {
            appointmentListAdapter.updateAppointmentList(it)
            if (it.size == 0) {
                imageEmptyAppointments.visibility = View.VISIBLE
                textEmptyAppointments.visibility = View.VISIBLE
            }
            else if (it.isEmpty()) {
                textErrorAppointments.visibility = View.VISIBLE
                progressLoadAppointments.visibility = View.VISIBLE
            }
            else {
                textErrorAppointments.visibility = View.GONE
                progressLoadAppointments.visibility = View.GONE
                imageEmptyAppointments.visibility = View.GONE
                textEmptyAppointments.visibility = View.GONE
            }
        })
    }
}