package com.example.stargazers.ui.contacts

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.stargazers.R
import com.example.stargazers.databinding.FragmentContactsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class ContactsFragment : Fragment() {

    private val CONTACTS_READ_REQ_CODE = 100

    // Initialize binding
    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ContactsViewModel by viewModels()

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        )
        { isGranted: Boolean ->
            if (isGranted) {
                viewModel.permissionStateGranted()
            } else {
                Toast.makeText(requireContext(), "Denied", Toast.LENGTH_SHORT).show()
            }

        }


    private fun requestPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED -> {
                // Permission granted
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.READ_CONTACTS
            ) -> {
                // Additional rationale, show snackbar

            }

            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.READ_CONTACTS
                )

            }


        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Initialize binding

        requestPermission()

        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_contacts, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launchWhenCreated {
            viewModel.permissionState.collect { permissionState ->
                when (permissionState) {
                    is ContactsViewModel.PermissionState.Granted -> {

                        viewModel.fetchContacts()

                        val myAdapter = ContactsAdapter(ContactsAdapter.OnClickListener {
                        }
                        )

                        viewModel.contactsLiveData.observe(viewLifecycleOwner, Observer {
                            myAdapter.submitList(it)
                        })

                        binding.rcwContacts.adapter = myAdapter

                    }

                    is ContactsViewModel.PermissionState.Rejected -> {

                    }

                    }
                }
            }
        }




}




