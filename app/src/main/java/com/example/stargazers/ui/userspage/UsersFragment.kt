package com.example.stargazers.ui.userspage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.stargazers.R
import com.example.stargazers.databinding.FragmentUsersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@ExperimentalComposeUiApi
@AndroidEntryPoint
class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
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
            R.layout.fragment_users, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.userState.collect { userState ->

                when (userState) {
                    is UsersViewModel.UserEvent.Success -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(context, "Data loaded from internet", Toast.LENGTH_LONG).show()
                    }
                    is UsersViewModel.UserEvent.Failure -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(context, "Data loaded from database", Toast.LENGTH_LONG).show()
                    }
                    is UsersViewModel.UserEvent.Loading -> binding.progressBar.isVisible = true
                }
            }
        }


        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = viewLifecycleOwner


        // Initialize viewModel
        binding.viewModel = viewModel

//        binding.photosGrid.adapter = UserGridAdapter()

        // Instantiating Usergrid adapter and passing function as parameter for OnClickListener
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

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}