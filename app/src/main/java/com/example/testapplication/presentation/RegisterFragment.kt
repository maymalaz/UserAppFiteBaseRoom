package com.example.testapplication.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testapplication.R
import com.example.testapplication.database.UserEntity
import com.example.testapplication.databinding.FragmentRegisterBinding
import com.example.testapplication.presentation.viewModel.DataStoreViewModel
import com.example.testapplication.presentation.viewModel.UserViewModel
import com.example.testapplication.utils.passwordToggle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val dataStoreViewModel: DataStoreViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        initClickListener()
        return binding.root

    }

    private fun initClickListener() {
        binding.btnRegister.setOnClickListener {
           // onRegisterClicked()
            handleClick()
        }

        binding.ivPasswordToggle.setOnClickListener {
            passwordToggle(binding.ivPasswordToggle, binding.etUserPassword)
        }
    }

    private fun onRegisterClicked() {
        val email = binding.etUserEmail.text.toString()
        val password = binding.etUserPassword.text.toString()
        val mobile = binding.etUserMobile.text.toString()
        val name = binding.etUserName.text.toString()


        if (name.isEmpty() || mobile.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.complete_form), Toast.LENGTH_LONG)
                .show()
            return


        } else {
            auth = Firebase.auth
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("userTest", "createUserWithEmail:success")
                        val user = auth.currentUser
                        checkIfUserHasSavedDetails()


                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("userTest", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            context, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

    }

    private fun checkIfUserHasSavedDetails() {
        dataStoreViewModel.savedKey.observe(viewLifecycleOwner) {
            if (it == true) {
                //saved go to user fragment
                findNavController().navigate(R.id.action_registerFragment_to_usersFragment)

            }
            //user hasn't saved details, show the form
            else {
                handleClick()

            }
        }
    }



    /**
     * Handle click of save button
     */
    private fun handleClick() {

        //on click of button save
        binding.btnRegister.setOnClickListener {

            //get details entered
            val name = binding.etUserName.text.toString()
            val mobile = binding.etUserMobile.text.toString()
            val email = binding.etUserEmail.text.toString()
            val password = binding.etUserPassword.text.toString()


            val user =
                UserEntity(id = 1, name = name, mobile = mobile, email = email, password = password)
            Log.d("testUser",user.toString())

            //save the details to room database
            userViewModel.insertUserDetails(user)

            userViewModel.response.observe(viewLifecycleOwner) {

                Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()

                //success, save key so on next visit user goes to details screen
                dataStoreViewModel.setSavedKey(true)

                //go to next fragment
                findNavController().navigate(R.id.action_registerFragment_to_usersFragment)

            }

        }

    }
}
