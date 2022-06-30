package com.ubaya.s160419037_umc.view

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
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
import com.ubaya.s160419037_umc.GlobalData
import com.ubaya.s160419037_umc.databinding.FragmentMainBinding
import com.ubaya.s160419037_umc.util.loadImage
import com.ubaya.s160419037_umc.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_header.view.*
import kotlinx.android.synthetic.main.drawer_layout.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_step_counter.*
import kotlin.math.pow
import kotlin.math.sqrt

class MainFragment : Fragment(), SensorEventListener, ButtonAppointmentMain, ButtonMedicineMain, ButtonStepCounter {
    private lateinit var viewModelLogin : LoginViewModel
    private lateinit var dataBinding: FragmentMainBinding

    private lateinit var sensorManager: SensorManager

    private var lightSensor: Sensor? = null
    private var lightReading = 0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentMainBinding.inflate(inflater, container, false)
        return dataBinding.root
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

        dataBinding.buttonAppointmentMain = this
        dataBinding.buttonMedicineMain = this
        dataBinding.buttonStepCounter = this
    }

    override fun onResume() {
        super.onResume()
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

                    if(GlobalData.firstLightNotification && lightReading < 20) {
                        Toast.makeText(context, "You are in a dark room. Please consider lowering your phone screen brightness.", Toast.LENGTH_LONG).show()
                        GlobalData.firstLightNotification = false
                    }
                }
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) { }

    override fun onButtonAppointmentMain(v: View) {
        val action = MainFragmentDirections.actionDoctorsFragment()
        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonMedicineMain(v: View) {
        val action = MainFragmentDirections.actionMedicineFragment()
        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonStepCounter(v: View) {
        val action = MainFragmentDirections.actionItemHomeToStepCounterFragment()
        Navigation.findNavController(v).navigate(action)
    }
}