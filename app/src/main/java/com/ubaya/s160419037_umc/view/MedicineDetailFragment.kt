package com.ubaya.s160419037_umc.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.s160419037_umc.GlobalData
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.databinding.FragmentMedicineDetailBinding
import com.ubaya.s160419037_umc.model.Transaction
import com.ubaya.s160419037_umc.util.loadImage
import com.ubaya.s160419037_umc.viewmodel.MedicineDetailViewModel
import com.ubaya.s160419037_umc.viewmodel.TransactionListViewModel
import kotlinx.android.synthetic.main.fragment_doctor_detail.*
import kotlinx.android.synthetic.main.fragment_medicine_detail.*
import java.util.*

class MedicineDetailFragment : Fragment(), ButtonMakeTransaction {
    private lateinit var viewModel : MedicineDetailViewModel
    private lateinit var viewModelTrans: TransactionListViewModel
    private lateinit var dataBinding: FragmentMedicineDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentMedicineDetailBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MedicineDetailViewModel::class.java)
        arguments?.let{
            viewModel.fetch(MedicineDetailFragmentArgs.fromBundle(requireArguments()).medicineId)
        }
        viewModelTrans = ViewModelProvider(this).get(TransactionListViewModel::class.java)

        observeViewModel()
        dataBinding.buttonTransListener = this
    }

    private fun observeViewModel() {
        viewModel.medicineLiveData.observe(viewLifecycleOwner, Observer {
            dataBinding.medicine = it
            dataBinding.transactions = Transaction(GlobalData.activeUser.username.toString(), it.name, it.variant, it.photo_url, it.price, 1, 0)

        })
//        {
//            with (it){
//                textMedicineNameDetail.text = name
//                textMedicineVariantDetail.text = variant
//                textMedicineCategoryDetail.text = medicine_category
//                textMedicinePriceDetail.text = price.toString()
//                textMedicineDescriptionDetail.text = description
//                imageMedicineDetail.loadImage(photo_url, progressLoadMedicineDetail)
//
//                buttonPurchase.setOnClickListener {
//                    val builder = androidx.appcompat.app.AlertDialog.Builder(context!!)
//                    with (builder) {
//                        setMessage("Your order on $name has been made.\nChill at home ~ we are going to deliver your medicine!")
//                        setTitle("All Set!")
//                        setPositiveButton("OK", null)
//                        create().show()
//                    }
//                }
//            }
//        }
    }

    override fun onButtonMakeTransaction(v: View, obj: Transaction) {
        dataBinding.transactions?.let {
            if (it.quantity > 0) {
                val today = Calendar.getInstance()
                it.time = (today.timeInMillis / 1000L).toInt()
                it.total = it.quantity * it.medicine_price
                val list = listOf(it)
                viewModelTrans.addTransaction(list)
                Toast.makeText(v.context, "Transaction created", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(v).popBackStack()
            }
            else {
                Toast.makeText(v.context, "Quantity needs to be at least 1", Toast.LENGTH_SHORT).show()
            }
        }
    }
}