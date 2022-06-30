package com.ubaya.s160419037_umc.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.databinding.AppointmentListItemBinding
import com.ubaya.s160419037_umc.model.Appointment
import com.ubaya.s160419037_umc.util.loadImage
import kotlinx.android.synthetic.main.appointment_list_item.view.*

class AppointmentListAdapter(val appointmentList: ArrayList<Appointment>, val adapterOnClick : (Appointment) -> Unit) : RecyclerView.Adapter<AppointmentListAdapter.AppointmentViewHolder>(), ButtonDeleteAppointment {
    class AppointmentViewHolder(var view: AppointmentListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<AppointmentListItemBinding>(inflater, R.layout.appointment_list_item, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = appointmentList[position]
        holder.view.appointment = appointment
        holder.view.buttonDeleteAppointmentListener = this
    }

    override fun getItemCount() = appointmentList.size

    fun updateAppointmentList(newList : List<Appointment>){
        appointmentList.clear()
        appointmentList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onButtonDeleteAppointment(v: View, obj: Appointment) {
        adapterOnClick(obj)
    }
}