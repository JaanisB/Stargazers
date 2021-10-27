package com.example.stargazers.ui.mainpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation

import com.example.stargazers.R
import com.example.stargazers.database.UserCacheEntity
import com.example.stargazers.databinding.FragmentMainpageBinding
import com.example.stargazers.databinding.FragmentUsersBinding
import com.example.stargazers.model.User
import com.example.stargazers.ui.userspage.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainpageFragment : Fragment() {

    private lateinit var binding: FragmentMainpageBinding

    private val viewModel: UsersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Initialize binding
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_mainpage, container, false
        )

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        binding.btnViewAllUsers.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_userDetailsFragment_to_mainPageFragment)
        }

        // Observe ViewModel users and bind that data to dropdown menu
        viewModel.users.observe(viewLifecycleOwner, Observer {
            users -> binding.autoCompleteTextView.setAdapter(ArrayAdapter(requireContext(), R.layout.mainmenu_dropdown_item, users.map { it.login }) )
            })





        return binding.root
    }

}