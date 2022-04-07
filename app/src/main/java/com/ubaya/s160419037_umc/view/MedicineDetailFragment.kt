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
import com.ubaya.s160419037_umc.viewmodel.MedicineDetailViewModel
import kotlinx.android.synthetic.main.fragment_doctor_detail.*
import kotlinx.android.synthetic.main.fragment_medicine_detail.*

class MedicineDetailFragment : Fragment() {
    private lateinit var viewModel : MedicineDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicine_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MedicineDetailViewModel::class.java)
        arguments?.let{
            viewModel.fetch(MedicineDetailFragmentArgs.fromBundle(requireArguments()).medicineId)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.medicineLiveData.observe(viewLifecycleOwner){
            with (it){
                textMedicineNameDetail.text = name
                textMedicineVariantDetail.text = variant
                textMedicineCategoryDetail.text = medicine_category
                textMedicinePriceDetail.text = price.toString()
                textMedicineDescriptionDetail.text = description
                imageMedicineDetail.loadImage(photo_url, progressLoadMedicineDetail)

                buttonPurchase.setOnClickListener {
                    val builder = androidx.appcompat.app.AlertDialog.Builder(context!!)
                    with (builder) {
                        setMessage("Your order on $name has been made.\nChill at home ~ we are going to deliver your medicine!")
                        setTitle("All Set!")
                        setPositiveButton("OK", null)
                        create().show()
                    }
                }
            }
        }
    }
}