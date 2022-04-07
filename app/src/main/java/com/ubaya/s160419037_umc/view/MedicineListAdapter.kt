package com.ubaya.s160419037_umc.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.model.Medicine
import com.ubaya.s160419037_umc.util.loadImage
import kotlinx.android.synthetic.main.medicine_list_item.view.*

class MedicineListAdapter (val medicineList: ArrayList<Medicine>) : RecyclerView.Adapter<MedicineListAdapter.MedicineViewHolder>() {
    class MedicineViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.medicine_list_item, parent, false)
        return MedicineListAdapter.MedicineViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val medicine = medicineList[position]
        with (holder.view){
            textMedicineNameList.text = medicine.name
            textMedicineVariantList.text = medicine.variant
            textMedicinePriceList.text = medicine.price.toString()
            imageMedicineList.loadImage(medicine.photo_url, progressLoadImageMedicineList)
            buttonMedicineDetailList.setOnClickListener {
                val action = MedicinesFragmentDirections.actionMedicineDetailFragment(medicine.id)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount() = medicineList.size

    fun updateMedicineList(newList : ArrayList<Medicine>){
        medicineList.clear()
        medicineList.addAll(newList)
        notifyDataSetChanged()
    }
}