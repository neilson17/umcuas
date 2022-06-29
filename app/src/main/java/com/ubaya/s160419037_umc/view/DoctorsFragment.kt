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

        var list = listOf(Doctor(1,"Dr Fillmore", "Male", 15000, "https://i.pravatar.cc/500?img=60", "Allergists", "An allergist or immunologist focuses on preventing and treating allergic diseases and conditions. These usually include various types of allergies and asthma."), Doctor(2,"Dr Drewel", "Male", 10000, "https://i.pravatar.cc/500?img=59", "Allergists", "An allergist or immunologist focuses on preventing and treating allergic diseases and conditions. These usually include various types of allergies and asthma."), Doctor(3, "Dr Chu", "Female", 8000, "https://i.pravatar.cc/500?img=47","Dermatologists","Dermatologists focus on diseases and conditions of the skin, nails, and hair. They treat conditions such as eczema, skin cancer, acne, and psoriasis."), Doctor(4, "Dr Hurter", "Male", 20000, "https://i.pravatar.cc/500?img=12", "Ophthalmologists", "Ophthalmologists specialize in eye and vision care. They treat diseases and conditions of the eyes and can perform eye surgery."), Doctor(5,"Dr Pepper", "Female", 19000, "https://i.pravatar.cc/500?img=32", "Ophthalmologists", "Ophthalmologists specialize in eye and vision care. They treat diseases and conditions of the eyes and can perform eye surgery."), Doctor(6, "Dr Beavers", "Male", 15000, "https://i.pravatar.cc/500?img=33", "Ophthalmologists", "Ophthalmologists specialize in eye and vision care. They treat diseases and conditions of the eyes and can perform eye surgery."), Doctor(7, "Dr Luke Whitesell", "Male", 16000, "https://i.pravatar.cc/500?img=51", "Obstetrician", "Obstetrician/gynecologists (OB/GYNs) provide preventive care and disease management for female health conditions."), Doctor(8, "Dr Elfman", "Male", 10000, "https://i.pravatar.cc/500?img=52", "Obstetrician", "Obstetrician/gynecologists (OB/GYNs) provide preventive care and disease management for female health conditions."), Doctor(9, "Dr Hopper", "Female", 11000, "https://i.pravatar.cc/500?img=36", "Nephrologists", "A nephrologist focuses on kidney care and conditions that affect the kidneys."), Doctor(10, "Dr Kaufman", "Male", 18000, "https://i.pravatar.cc/500?img=53", "Urologists", "Urologists treat conditions of the urinary tract in both males and females. They also focus on male reproductive health."))

//        (11, 'Dr Albright', 'Male', 18500, 'https://i.pravatar.cc/500?img=54', 6),
//        (12, 'Dr Stone', 'Female', 13000, 'https://i.pravatar.cc/500?img=26', 7),
//        (13, 'Dr White', 'Male', 10000, 'https://i.pravatar.cc/500?img=8', 7),
//        (14, 'Dr Luke', 'Male', 11000, 'https://i.pravatar.cc/500?img=57', 7))

        viewModel.addDoctor(list)

        viewModel.refresh()

        recViewDoctors.layoutManager = LinearLayoutManager(context)
        recViewDoctors.adapter = doctorListAdapter

        observeViewModel()

//        doctorsRefreshLayout.setOnRefreshListener {
//            recViewDoctors.visibility = View.GONE
//            textErrorDoctors.visibility = View.GONE
//            progressLoadDoctors.visibility = View.VISIBLE
//            viewModel.refresh()
//            doctorsRefreshLayout.isRefreshing = false
//        }
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
//        viewModel.doctorsLiveData.observe(viewLifecycleOwner){
//            doctorListAdapter.updateDoctorList(it)
//        }
//        viewModel.doctorsLoadErrorLiveData.observe(viewLifecycleOwner){
//            textErrorDoctors.visibility = if (it) View.VISIBLE else View.GONE
//        }
//        viewModel.loadingLiveData.observe(viewLifecycleOwner){
//            if (it){
//                progressLoadDoctors.visibility = View.VISIBLE
//                recViewDoctors.visibility = View.GONE
//            }
//            else {
//                progressLoadDoctors.visibility = View.GONE
//                recViewDoctors.visibility = View.VISIBLE
//            }
//        }
    }
}