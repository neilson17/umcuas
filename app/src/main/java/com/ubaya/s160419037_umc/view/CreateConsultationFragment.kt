package com.ubaya.s160419037_umc.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.s160419037_umc.GlobalData
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.databinding.FragmentCreateConsultationBinding
import com.ubaya.s160419037_umc.model.Appointment
import com.ubaya.s160419037_umc.model.Doctor
import com.ubaya.s160419037_umc.viewmodel.AppointmentListViewModel
import com.ubaya.s160419037_umc.viewmodel.DoctorDetailViewModel
import kotlinx.android.synthetic.main.fragment_create_consultation.*
import kotlinx.android.synthetic.main.fragment_create_consultation.view.*
import java.util.*
import kotlin.math.min

class CreateConsultationFragment : Fragment(), ButtonAddConsultationClickListener, ConsultationDateListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var viewModel: AppointmentListViewModel
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
//        return inflater.inflate(R.layout.fragment_create_consultation, container, false)
        dataBinding = FragmentCreateConsultationBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AppointmentListViewModel::class.java)
        val doctorId = CreateConsultationFragmentArgs.fromBundle(requireArguments()).uuid
        var doctorName = CreateConsultationFragmentArgs.fromBundle(requireArguments()).name
        val doctorCategory = CreateConsultationFragmentArgs.fromBundle(requireArguments()).doctorCategory
        val doctorPhoto = CreateConsultationFragmentArgs.fromBundle(requireArguments()).doctorPhoto

//        val adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, arrayListOf<Doctor>())
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinnerDoctors.adapter = adapter
        dataBinding.appointment = Appointment(GlobalData.activeUser.username.toString(), doctorId, doctorName, doctorCategory, doctorPhoto)
        dataBinding.dateListener = this
        dataBinding.buttonListener = this

    }

    override fun onButtonAddConsultation(v: View) {
        val c = Calendar.getInstance()
        c.set(year, month, day, hour, minute, 0)

        dataBinding.appointment?.let {
            it.time = (c.timeInMillis/1000L).toInt()
            val list = listOf(it)
            viewModel.addAppointment(list)
            Toast.makeText(v.context, "Appointment created", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(v).popBackStack()
        }
    }

    override fun onDateClick(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        activity?.let{ it1-> DatePickerDialog(it1, this, year, month, day).show() }
    }

    override fun onTimeClick(view: View) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity)).show()
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        Calendar.getInstance().let {
            this.month = month + 1
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
        this.hour = hourOfDay
        this.minute = minute
    }
}