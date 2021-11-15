package com.example.stargazers.ui.sensors

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemServiceName
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.stargazers.R
import com.example.stargazers.databinding.FragmentSensorsBinding
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class SensorsFragment : Fragment(), SensorEventListener {

    private lateinit var binding: FragmentSensorsBinding

    private val viewmodel: SensorsViewModel by activityViewModels()

    private lateinit var sensorManager: SensorManager
    private var brightnessSensor: Sensor? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // Initialize binding
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sensors, container, false
        )

        binding.viewModel = viewmodel

        setUpSensor()



        return binding.root
    }

    // Initialize brightness sensor
    private fun setUpSensor() {
        sensorManager = requireActivity().getSystemService(SENSOR_SERVICE) as SensorManager
        brightnessSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    // Set brightness levels described in words
    private fun brightness(brightness: Float): String {

        return when (brightness.toInt()) {
            0 -> "Very dark"
            in 1..10 -> "Dark"
            in 11..50 -> "Grey"
            in 51..5000 -> "Normal"
            in 5001..25000 -> "Very bright"
            else -> "This is too bright for your eyes"
        }
    }


    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            val light = event.values[0]
            binding.textLightsensor.text = "Sensor $light\n${brightness(light)}"
            binding.progressCircular.setProgressWithAnimation(light)
        }
    }

    override fun onAccuracyChanged(event: Sensor?, p1: Int) {
        return
    }


    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, brightnessSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }


}




