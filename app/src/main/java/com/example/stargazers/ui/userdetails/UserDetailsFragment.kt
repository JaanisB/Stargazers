package com.example.stargazers.ui.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stargazers.R
import com.example.stargazers.databinding.FragmentUserDetailsBinding
import com.example.stargazers.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Initialize binding
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_details, container, false
        )

        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        binding.lifecycleOwner = this
        val user = UserDetailsFragmentArgs.fromBundle(requireArguments()).selectedUser
        val viewModelFactory = UserDetailViewModelFactory(user, application)
        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(UserDetailViewModel::class.java)

        return binding.root
    }
}