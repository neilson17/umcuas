package com.ubaya.s160419037_umc.view

import android.view.View
import com.ubaya.s160419037_umc.model.Doctor

interface ButtonAddConsultationClickListener{
    fun onButtonAddTodo(v: View)
}

interface ConsultationDateListener{
    fun onDateClick(view: View)
    fun onTimeClick(view: View)
}

interface ButtonDetailDoctor{
    fun onButtonDetailDoctor(v: View)
}

interface ButtonMakeAppointment{
    fun onButtonMakeAppointment(v: View, obj: Doctor)
}