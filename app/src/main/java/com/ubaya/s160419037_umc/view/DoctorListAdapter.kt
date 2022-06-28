package com.ubaya.s160419037_umc.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.model.Doctor
import com.ubaya.s160419037_umc.util.loadImage
import kotlinx.android.synthetic.main.doctor_list_item.view.*

class DoctorListAdapter (val doctorList: ArrayList<Doctor>) : RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder>() {
    class DoctorViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.doctor_list_item, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctorList[position]
        with (holder.view){
            textDoctorNameList.text = doctor.name
            textDoctorCategoryList.text = doctor.doctor_category
            textDoctorPriceList.text = doctor.price.toString() + "/hour"
            imageDoctorList.loadImage(doctor.photo_url, progressLoadImageDoctorList)
            buttonDoctorDetailList.setOnClickListener {
                val action = DoctorsFragmentDirections.actionDoctorDetailFragment(doctor.uuid)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount() = doctorList.size

    fun updateDoctorList(newList : ArrayList<Doctor>){
        doctorList.clear()
        doctorList.addAll(newList)
        notifyDataSetChanged()
    }
}