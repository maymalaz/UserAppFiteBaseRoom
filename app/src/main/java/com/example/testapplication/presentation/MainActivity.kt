package com.example.testapplication.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.testapplication.R
import com.example.testapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateStatusBarColor(ContextCompat.getColor(this, R.color.white))
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavGraph()

    }

    private fun setupNavGraph() {
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }



    fun updateStatusBarColor(colorResource: Int) {
        showStatusBar()
        window.statusBarColor = colorResource
    }

    private fun showStatusBar() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

    }
}



/*
 auth = Firebase.auth

auth.createUserWithEmailAndPassword("test@gmail.com", "1234567")
      .addOnCompleteListener(this) { task ->
          if (task.isSuccessful) {
              // Sign in success, update UI with the signed-in user's information
              Log.d("userTest", "createUserWithEmail:success")
              val user = auth.currentUser
          } else {
              // If sign in fails, display a message to the user.
              Log.w("userTest", "createUserWithEmail:failure", task.exception)
              Toast.makeText(baseContext, "Authentication failed.",
                  Toast.LENGTH_SHORT).show()
          }
      }

auth.signInWithEmailAndPassword("test@gmail.com", "1234567")
.addOnCompleteListener(this) { task ->
    if (task.isSuccessful) {
        // Sign in success, update UI with the signed-in user's information
        Log.d("userTest", "signInWithEmail:success")
        val user = auth.currentUser
    } else {
        // If sign in fails, display a message to the user.
        Log.w("userTest", "signInWithEmail:failure", task.exception)
        Toast.makeText(baseContext, "Authentication failed.",
            Toast.LENGTH_SHORT).show()
    }
}

 */