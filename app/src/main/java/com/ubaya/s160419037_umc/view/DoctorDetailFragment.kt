package com.ubaya.s160419037_umc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.databinding.FragmentDoctorDetailBinding
import com.ubaya.s160419037_umc.model.Doctor
import com.ubaya.s160419037_umc.util.loadImage
import com.ubaya.s160419037_umc.viewmodel.DoctorDetailViewModel
import kotlinx.android.synthetic.main.fragment_doctor_detail.*

class DoctorDetailFragment : Fragment(), ButtonMakeAppointment {
    private lateinit var viewModel :DoctorDetailViewModel
    private lateinit var dataBinding: FragmentDoctorDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentDoctorDetailBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DoctorDetailViewModel::class.java)
        arguments?.let{
            viewModel.fetch(DoctorDetailFragmentArgs.fromBundle(requireArguments()).doctorId)
        }

        observeViewModel()
        dataBinding.buttonMakeAppointment = this
    }

    private fun observeViewModel() {
        viewModel.doctorLiveData.observe(viewLifecycleOwner, Observer {
            dataBinding.doctor = it
        })
    }

    override fun onButtonMakeAppointment(v: View, obj: Doctor) {
        val uuid = obj.uuid
        val name = obj.name
        val category = obj.doctor_category
        val photo = obj.photo_url
        val action = DoctorDetailFragmentDirections.actionDoctorDetailFragmentToCreateConsultationFragment(uuid, name, category, photo)
        Navigation.findNavController(v).navigate(action)
    }
}