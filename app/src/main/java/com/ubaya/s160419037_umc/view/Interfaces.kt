package com.ubaya.s160419037_umc.view

import android.view.View
import com.ubaya.s160419037_umc.model.Doctor
import com.ubaya.s160419037_umc.model.Transaction

interface ButtonAddConsultationClickListener{
    fun onButtonAddConsultation(v: View)
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

interface ButtonDetailMedicine{
    fun onButtonDetailMedicine(v: View)
}

interface ButtonMakeTransaction{
    fun onButtonMakeTransaction(v: View, obj: Transaction)
}

interface ButtonLogin{
    fun onButtonLogin(v: View)
}