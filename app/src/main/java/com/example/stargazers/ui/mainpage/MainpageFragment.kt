package com.example.stargazers.ui.mainpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.stargazers.R
import com.example.stargazers.databinding.FragmentMainpageBinding
import com.example.stargazers.ui.userspage.UsersFragmentDirections
import com.example.stargazers.ui.userspage.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainpageFragment : Fragment() {

    private var _binding: FragmentMainpageBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UsersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Initialize binding
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_mainpage, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnViewAllUsers.setOnClickListener {
            viewModel.getUserList()
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_userDetailsFragment_to_mainPageFragment)
        }

        binding.btnSensorData.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_mainpageFragment_to_sensorsFragment)
        }

        // Observe ViewModel users and bind that data to dropdown menu
        viewModel.users.observe(viewLifecycleOwner, { users ->
            binding.autoCompleteTextView.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    R.layout.mainmenu_dropdown_item,
                    users.map {
                        it.login
                    })
            )
        }
        )

        binding.btnViewSelectedUser.setOnClickListener {
            viewModel.getUserByLogin(binding.autoCompleteTextView.onItemClickListener.toString())
        }

        // binding.autoCompleteTextView.setOnItemClickListener { adapterView, view, i, l ->  }

        viewModel.navigateToSelectedUser.observe(viewLifecycleOwner, {
            if (null != it) {
                this.findNavController().navigate(
                    UsersFragmentDirections.actionUsersFragmentToUserDetailsFragment(it)
                )
                viewModel.displayUserDetailsComplete()
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}