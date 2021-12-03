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
import com.example.stargazers.databinding.FragmentUsersBinding
import com.example.stargazers.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Initialize binding
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_details, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(activity).application
        binding.lifecycleOwner = viewLifecycleOwner
        val user = UserDetailsFragmentArgs.fromBundle(requireArguments()).selectedUser
        val viewModelFactory = UserDetailViewModelFactory(user, application)
        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(UserDetailViewModel::class.java)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}