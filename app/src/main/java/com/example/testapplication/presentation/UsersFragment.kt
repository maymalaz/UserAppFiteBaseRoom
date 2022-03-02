package com.example.testapplication.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.databinding.FragmentUsersBinding
import com.example.testapplication.presentation.adapters.UserListAdapter
import com.example.testapplication.presentation.viewModel.DataStoreViewModel
import com.example.testapplication.presentation.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : Fragment() {
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    private val userListAdapter by lazy { UserListAdapter() }
    private val userViewModel: UserViewModel by viewModels()
    private val dataStoreViewModel: DataStoreViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        getUserDetails()
    }


    private fun initRV() {
        binding.recyclerUsers.adapter = userListAdapter
        binding.recyclerUsers.layoutManager = LinearLayoutManager(requireContext())


/*
        userListAdapter.onTapListener = object : UserListAdapter.OnUserItemTap {
            override fun onProductTap(position: Int) {


            }

        }*/
    }


    private fun getUserDetails(){

        this.lifecycleScope.launch {

                userViewModel.doGetUserDetails()
                userViewModel.userDetails.collect {
                    for(user in it)
                    {
                        //set data into view
                            userListAdapter.submitList(it)


                    }
                }

    }

}
    private fun handleClicks(){

        binding.recyclerUsers.setOnClickListener {

            //clear record from room database
            userViewModel.doDeleteSingleUserRecord()

            //remove the datastorage key
            dataStoreViewModel.setSavedKey(false)



        }

    }




}