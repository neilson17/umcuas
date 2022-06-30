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
import android.widget.Toast
import com.ubaya.s160419037_umc.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_step_counter.*
import kotlin.math.pow
import kotlin.math.sqrt

class StepCounterFragment : Fragment(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var accelerometerReadings = FloatArray(3)
    private var accelerometerSensor:Sensor? = null

    private var previousMagnitude: Float? = null
    private var stepCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_step_counter, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).bottomNav.visibility = View.GONE

        buttonReset.setOnClickListener {
            stepCount = 0
            textStepCount.text = stepCount.toString()
            previousMagnitude = null
        }
    }

    override fun onResume() {
        super.onResume()

        if(accelerometerSensor == null){
            Toast.makeText(activity, "No accelerometer detected", Toast.LENGTH_SHORT).show()
        } else {
            //daftarkan listener
            Toast.makeText(activity, "Accelerometer enabled :)", Toast.LENGTH_SHORT).show()
            sensorManager.registerListener(
                this,
                accelerometerSensor,
                SensorManager.SENSOR_DELAY_FASTEST
            )
        }
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        p0?.let{
            when(it.sensor.type){
                Sensor.TYPE_ACCELEROMETER -> {
                    accelerometerReadings = it.values
                    var x = it.values[0]
                    var y = it.values[1]
                    var z = it.values[2]

                    textAccelX.text = String.format("%.2f", x)
                    textAccelY.text = String.format("%.2f", y)
                    textAccelZ.text = String.format("%.2f", z)

                    // Menghitung langkah
                    var magnitude = sqrt(x.pow(2) + y.pow(2) + z.pow(2))
                    if (previousMagnitude != null){
                        var diff = magnitude - previousMagnitude!!
                        if (diff > 2){
                            stepCount++
                            textStepCount.text = stepCount.toString()
                        }
                    }
                    previousMagnitude = magnitude
                }
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) { }

    override fun onPause() {
        super.onPause()

        sensorManager.unregisterListener(this)
    }
}