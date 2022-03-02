package com.example.testapplication.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentLoginBinding
import com.example.testapplication.utils.passwordToggle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        initClickListener()
        return binding.root
    }

    private fun initClickListener() {
        binding.btnLogin.setOnClickListener {
            onLoginClicked()

        }

        binding.ivPasswordToggle.setOnClickListener {
            passwordToggle(binding.ivPasswordToggle,binding.etUserPassword)
        }
        binding.tvNewUser.setOnClickListener {
            // if new user go to register fragment
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
    private fun onLoginClicked() {
        val email = binding.etUsername.text.toString()
        val password = binding.etUserPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.complete_form), Toast.LENGTH_LONG).show()
            return
        }
        else{
            auth = Firebase.auth
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {  task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("userTest", "signInWithEmail:success")
                        val user = auth.currentUser

                        // if user signIn successfully go to users fragment
                        findNavController().navigate(R.id.action_loginFragment_to_usersFragment)

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("userTest", "signInWithEmail:failure", task.exception)
                        Toast.makeText(context, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}