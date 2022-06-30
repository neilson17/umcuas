package com.ubaya.s160419037_umc.view

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.s160419037_umc.GlobalData
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.model.Login
import com.ubaya.s160419037_umc.util.loadImage
import com.ubaya.s160419037_umc.viewmodel.AppointmentListViewModel
import com.ubaya.s160419037_umc.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_header.view.*
import kotlinx.android.synthetic.main.drawer_layout.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_step_counter.*
import kotlin.math.pow
import kotlin.math.sqrt

class MainFragment : Fragment(), SensorEventListener {
    private lateinit var viewModel: AppointmentListViewModel
    private lateinit var viewModelLogin : LoginViewModel

    private lateinit var sensorManager: SensorManager

    private var lightSensor: Sensor? = null
    private var lightReading = 0f
    private var firstLightNotification = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).bottomNav.visibility = View.VISIBLE

        var shared = (activity as MainActivity).getSharedPreferences(
            (activity as MainActivity).packageName,
            Context.MODE_PRIVATE
        )
        var logged_in_username = shared.getString("USERNAME", null)
        var logged_in_password = shared.getString("PASSWORD", null)

        if (!GlobalData.loggedIn && logged_in_username != null) {
            viewModelLogin = ViewModelProvider(this).get(LoginViewModel::class.java)
            viewModelLogin.fetch(logged_in_username, logged_in_password!!)
            observeViewModelLogin()
        }
        else if (!GlobalData.loggedIn && logged_in_username == null){
            (activity as MainActivity).supportActionBar?.hide()
            (activity as MainActivity).bottomNav.visibility = View.GONE

            val action = MainFragmentDirections.actionLoginFragment()
            Navigation.findNavController(view).navigate(action)
        }
        else {
            updateHome()
        }

        buttonAppointmentHome.setOnClickListener {
            val action = MainFragmentDirections.actionDoctorsFragment()
            Navigation.findNavController(view).navigate(action)
        }

        buttonMedicineHome.setOnClickListener {
            val action = MainFragmentDirections.actionMedicineFragment()
            Navigation.findNavController(view).navigate(action)
        }

        buttonStepCounter.setOnClickListener {
            val action = MainFragmentDirections.actionItemHomeToStepCounterFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        firstLightNotification = true
        if(lightSensor == null){
            Toast.makeText(activity, "No light sensor detected", Toast.LENGTH_SHORT).show()
        }
        else {
            //daftarkan listener
            Toast.makeText(activity, "Light sensor enabled :)", Toast.LENGTH_SHORT).show()
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_FASTEST)
        }
    }

    override fun onPause() {
        super.onPause()

        sensorManager.unregisterListener(this)
    }

    private fun updateHome(){
        textNameHome.text = GlobalData.activeUser.name
        var header = (activity as MainActivity).navView.getHeaderView(0)
        (header.textUsernameDrawer as TextView).text = GlobalData.activeUser.username
        (header.imageDrawer as ImageView).loadImage(GlobalData.activeUser.photo_url, (header.progressLoadDrawer as ProgressBar))

//        viewModel = ViewModelProvider(this).get(AppointmentListViewModel::class.java)
//        viewModel.refresh(GlobalData.activeUser.username!!)
//        viewModel.appointmentsLiveData.observe(viewLifecycleOwner){
//            if (it.size > 0) {
//                val appointment = it[it.size - 1]
//                imageDoctorHome.loadImage(appointment.doctor.photo_url, progressLoadDoctorHome)
//                textDoctorNameHome.text = appointment.doctor.name
//                textDoctorDateHome.text = appointment.time
//                buttonCallHome.setOnClickListener {
//                    val builder = AlertDialog.Builder(context!!)
//                    with (builder) {
//                        setMessage("${appointment.doctor.name} will call you back in a minute!\nSorry for the inconvenience :(")
//                        setTitle("Call Failed")
//                        setPositiveButton("OK", null)
//                        create().show()
//                    }
//                }

//                textNoAppHome.visibility = View.GONE
//            }
//            else {
//                cardAppointmentsHome.visibility = View.GONE
//            }
//        }
    }

    private fun observeViewModelLogin(){
        viewModelLogin.loginLiveData.observe(viewLifecycleOwner) {
            GlobalData.activeUser = it
            GlobalData.loggedIn = true

            updateHome()
        }
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        p0?.let{
            when(it.sensor.type){
                Sensor.TYPE_LIGHT -> {
                    lightReading = it.values[0]

                    if(firstLightNotification && lightReading < 20) {
                        Toast.makeText(context, "You are in a dark room. Please consider lowering your phone screen brightness.", Toast.LENGTH_LONG).show()
                        firstLightNotification = false
                    }
                }
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) { }
}