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
import kotlinx.android.synthetic.main.fragment_step_counter.*

class StepCounterFragment : Fragment(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var accelerometerReadings = FloatArray(3)
    private var accelerometerSensor:Sensor? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_step_counter, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        sensorManager = activity!!.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        if(accelerometerSensor == null){
            Toast.makeText(activity!!, "No accelerometer detected", Toast.LENGTH_SHORT).show()
            return
        }
        //daftarkan listener
        Toast.makeText(activity!!, "Accelerometer detected", Toast.LENGTH_SHORT).show()
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_FASTEST)
        textAccelerometer.text = "test"
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        p0?.let{
            when(it.sensor.type){
                Sensor.TYPE_ACCELEROMETER -> {
                    accelerometerReadings = it.values
                    var x = it.values[0]
                    var y = it.values[1]
                    var z = it.values[2]

                    textAccelerometer.text = "X: $x, Y: $y, Z: $z"
                }
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        //
    }
}