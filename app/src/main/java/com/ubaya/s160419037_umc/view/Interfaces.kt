package com.ubaya.s160419037_umc.view

import android.view.View

interface ButtonAddConsultationClickListener{
    fun onButtonAddTodo(v: View)
}

interface ConsultationDateListener{
    fun onDateClick(view: View)
    fun onTimeClick(view: View)
}