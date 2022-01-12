package com.example.stargazers.ui.service

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.stargazers.bindImage
import com.example.stargazers.databinding.FragmentServiceBinding

class ServiceFragment : Fragment() {

    private var _binding: FragmentServiceBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentServiceBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val intent = Intent(context, MyService::class.java)

        binding.btnStartService.setOnClickListener {
            context?.startService(intent)
        }

        binding.btnStopService.setOnClickListener {
            context?.stopService(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    private fun launchService(intent: Intent) {


    }

    private fun stopService(intent: Intent) {


    }

}



