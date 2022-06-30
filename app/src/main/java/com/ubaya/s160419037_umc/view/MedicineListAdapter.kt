package com.ubaya.s160419037_umc.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.databinding.MedicineListItemBinding
import com.ubaya.s160419037_umc.databinding.NewsListItemBinding
import com.ubaya.s160419037_umc.model.Medicine
import com.ubaya.s160419037_umc.util.loadImage
import kotlinx.android.synthetic.main.medicine_list_item.view.*

class MedicineListAdapter (val medicineList: ArrayList<Medicine>) : RecyclerView.Adapter<MedicineListAdapter.MedicineViewHolder>(), ButtonDetailMedicine {
    class MedicineViewHolder(var view: MedicineListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<MedicineListItemBinding>(inflater, R.layout.medicine_list_item, parent, false)
        return MedicineViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val medicine = medicineList[position]
        holder.view.medicine = medicine
        holder.view.buttonDetailMedListener = this
    }

    override fun getItemCount() = medicineList.size

    fun updateMedicineList(newList : List<Medicine>){
        medicineList.clear()
        medicineList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailMedicine(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = MedicinesFragmentDirections.actionMedicineDetailFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}