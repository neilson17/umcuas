package com.ubaya.s160419037_umc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.util.loadImage
import com.ubaya.s160419037_umc.viewmodel.DoctorDetailViewModel
import kotlinx.android.synthetic.main.fragment_doctor_detail.*

class DoctorDetailFragment : Fragment() {
    private lateinit var viewModel :DoctorDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DoctorDetailViewModel::class.java)
        arguments?.let{
            viewModel.fetch(DoctorDetailFragmentArgs.fromBundle(requireArguments()).doctorId)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.doctorLiveData.observe(viewLifecycleOwner){
            with (it){
                var ph = ""
                for (i in 0..practice_hours.size-1) {
                    ph += if (i != 0) "\n" + practice_hours[i] else practice_hours[i]
                }

                textDoctorPracticeHourDetail.text = ph
                textDoctorNameDetail.text = name
                textDoctorPriceDetail.text = "$price/hour"
                textDoctorGenderDetail.text = gender
                textDoctorCategoryDetail.text = doctor_category
                textDoctorCategoryDescDetail.text = doctor_category_description
                imageDoctorDetail.loadImage(photo_url, progressLoadDoctorDetail)

                buttonAppointment.setOnClickListener {
                    val builder = AlertDialog.Builder(context!!)
                    with (builder) {
                        setMessage("Your appointment with $name has been made.\nEnjoy your day :)")
                        setTitle("All Set!")
                        setPositiveButton("OK", null)
                        create().show()
                    }
                }

                buttonMessage.setOnClickListener {
                    Toast.makeText(context!!, "$name says Hi to you!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}