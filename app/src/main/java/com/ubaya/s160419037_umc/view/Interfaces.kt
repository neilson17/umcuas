package com.ubaya.s160419037_umc.view

import android.view.View
import com.ubaya.s160419037_umc.model.Doctor
import com.ubaya.s160419037_umc.model.Transaction
import com.ubaya.s160419037_umc.model.User

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

interface ButtonUpdateProfile{
    fun onButtonUpdateProfile(v: View, obj:User)
}

interface ButtonAppointmentMain{
    fun onButtonAppointmentMain(v: View)
}

interface ButtonMedicineMain{
    fun onButtonMedicineMain(v: View)
}

interface ButtonStepCounter{
    fun onButtonStepCounter(v: View)
}

interface ButtonResetCount{
    fun onButtonResetCount(v: View)
}

interface ButtonRegister{
    fun onButtonRegister(v: View)
}

interface ButtonCancelRegister{
    fun onButtonCancelRegister(v: View)
}

interface ButtonGoToRegister {
    fun onButtonGoToRegister(v: View)
}

interface ButtonDeleteTransaction{
    fun onButtonDeleteTransaction(v: View)
}

interface ButtonDeleteAppointment{
    fun onButtonDeleteAppointment(v: View)
}