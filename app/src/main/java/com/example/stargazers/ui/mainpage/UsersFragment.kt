package com.example.stargazers.ui.mainpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.stargazers.R
import com.example.stargazers.databinding.FragmentUsersBinding


class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding

    private val viewModel: UsersViewModel by lazy {
        ViewModelProvider(this).get(UsersViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Initialize binding
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_users, container, false
        )


        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Initialize viewModel
        binding.viewModel = viewModel

        binding.photosGrid.adapter = UserGridAdapter()

        setHasOptionsMenu(true)
        return binding.root
    }
}