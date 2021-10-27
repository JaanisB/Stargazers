package com.example.stargazers.ui.userspage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.stargazers.R
import com.example.stargazers.databinding.FragmentUsersBinding
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding

    private val viewModel: UsersViewModel by activityViewModels()



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

//        binding.photosGrid.adapter = UserGridAdapter()

        binding.photosGrid.adapter = UserGridAdapter(UserGridAdapter.OnClickListener {
            viewModel.displayUserDetails(it)
        })

        viewModel.navigateToSelectedUser.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(
                    UsersFragmentDirections.actionUsersFragmentToUserDetailsFragment(it))
                viewModel.displayUserDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }
}