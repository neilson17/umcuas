package com.ubaya.s160419037_umc.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.databinding.DoctorListItemBinding
import com.ubaya.s160419037_umc.databinding.NewsListItemBinding
import com.ubaya.s160419037_umc.model.Doctor
import com.ubaya.s160419037_umc.util.loadImage
import kotlinx.android.synthetic.main.doctor_list_item.view.*

class DoctorListAdapter (val doctorList: ArrayList<Doctor>) : RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder>(), ButtonDetailDoctor {
    class DoctorViewHolder(var view: DoctorListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<DoctorListItemBinding>(inflater, R.layout.doctor_list_item, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctorList[position]
        holder.view.doctor = doctor
        holder.view.buttonDetailListener = this
//        with (holder.view){
//            textDoctorNameList.text = doctor.name
//            textDoctorCategoryList.text = doctor.doctor_category
//            textDoctorPriceList.text = doctor.price.toString() + "/hour"
//            imageDoctorList.loadImage(doctor.photo_url, progressLoadImageDoctorList)
////            buttonDoctorDetailList.setOnClickListener {
////                val action = DoctorsFragmentDirections.actionDoctorDetailFragment(doctor.uuid)
////                Navigation.findNavController(it).navigate(action)
////            }
//        }
    }

    override fun getItemCount() = doctorList.size

    fun updateDoctorList(newList : List<Doctor>){
        doctorList.clear()
        doctorList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailDoctor(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = DoctorsFragmentDirections.actionDoctorDetailFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}