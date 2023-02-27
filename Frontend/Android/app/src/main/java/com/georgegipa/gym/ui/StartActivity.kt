package com.georgegipa.gym.ui

import android.content.Context
import android.content.Intent
import android.text.Editable.Factory.getInstance
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.georgegipa.gym.api.ApiResponses
import com.georgegipa.gym.databinding.ActivityStartBinding
import com.georgegipa.gym.models.UserBody
import com.georgegipa.gym.utils.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class StartActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityStartBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        _binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            if (binding.editTextTextEmailAddress.text.toString()
                    .isEmpty() || binding.editTextTextPassword.text.toString().isEmpty()
            ) {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Error")
                    .setMessage("Please fill in all the fields.")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            } else {
                val imm =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
                imm.hideSoftInputFromWindow(binding.editTextTextEmailAddress.windowToken, 0)
                imm.hideSoftInputFromWindow(binding.editTextTextPassword.windowToken, 0)
                initNow(
                    UserBody(
                        binding.editTextTextEmailAddress.text.toString(),
                        binding.editTextTextPassword.text.toString()
                    )
                )
            }
        }
        if (isUserLoggedIn()) {
            binding.editTextTextEmailAddress.text = getInstance().newEditable(getUserEmail())
            binding.editTextTextPassword.text = getInstance().newEditable(getUserCode())
            initNow(
                UserBody(
                    binding.editTextTextEmailAddress.text.toString(),
                    binding.editTextTextPassword.text.toString()
                )
            )
        }

    }

    private fun initNow(userBody: UserBody) {
        //make the editText not editable
        binding.editTextTextEmailAddress.isEnabled = false
        binding.editTextTextPassword.isEnabled = false
        binding.progressBar.visibility = android.view.View.VISIBLE
        binding.button.isEnabled = false
        binding.button.text = "Signing in..."
        lifecycleScope.launch {
            val code = ApiResponses.init(userBody)
            if (code != 200) {
                //run on the main thread
                runOnUiThread {
                    val error = if(code == -1) {
                        if(this@StartActivity.isNetworkAvailable()){
                            "Invalid email or password."
                        } else {
                            "No internet connection."
                        }
                    }else
                        "An error occurred. Please try again later."

                    MaterialAlertDialogBuilder(this@StartActivity)
                        .setCancelable(false)
                        .setTitle("Error")
                        .setMessage(error)
                        .setPositiveButton("Retry") { dialog, _ ->
                            binding.editTextTextEmailAddress.isEnabled = true
                            binding.editTextTextPassword.isEnabled = true
                            binding.progressBar.visibility = android.view.View.INVISIBLE
                            binding.button.isEnabled = true
                            binding.button.text = "SIGN IN"
                            dialog.dismiss()
                        }
                        .setNegativeButton("Exit") { dialog, _ ->
                            dialog.dismiss()
                            finish()
                        }
                        .show()
                }
            } else {
                saveUserCredentials(userBody.email, userBody.password)
                val intent = Intent(this@StartActivity, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }

}