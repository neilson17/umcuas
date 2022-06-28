package com.ubaya.s160419037_umc.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.databinding.FragmentCreateConsultationBinding
import com.ubaya.s160419037_umc.model.Doctor
import kotlinx.android.synthetic.main.fragment_create_consultation.*
import kotlinx.android.synthetic.main.fragment_create_consultation.view.*
import java.util.*

class CreateConsultationFragment : Fragment(), ButtonAddConsultationClickListener, ConsultationDateListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var dataBinding: FragmentCreateConsultationBinding
    var year = 0
    var month = 0
    var day = 0
    var hour = 0
    var minute = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_consultation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, arrayListOf<Doctor>())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDoctors.adapter = adapter
    }

    override fun onButtonAddTodo(v: View) {
        //
    }

    override fun onDateClick(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        activity?.let{ it1-> DatePickerDialog(it1, this, year, month,day).show() }
    }

    override fun onTimeClick(view: View) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity)).show()
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        Calendar.getInstance().let {
            it.set(year, month, day)
            dataBinding.root.textConsultationDate.setText(day.toString().padStart(2, '0') + "-" +
                    month.toString().padStart(2, '0') + "-" + year)
            this.year = year
            this.month = month
            this.day = day
        }
    }

    override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {
        dataBinding.root.textConsultationTime.setText(
            hourOfDay.toString().padStart(2, '0') + ":" + minute.toString().padStart(2, '0')
        )
        hour = hourOfDay
        this.minute = minute
    }
}