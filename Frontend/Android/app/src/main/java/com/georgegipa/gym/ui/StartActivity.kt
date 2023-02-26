package com.georgegipa.gym.ui

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.ColorMatrixColorFilter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.georgegipa.gym.R
import com.georgegipa.gym.TEMP_CODE
import com.georgegipa.gym.TEMP_EMAIL
import com.georgegipa.gym.TEMP_USER_ID
import com.georgegipa.gym.api.ApiResponses
import com.georgegipa.gym.databinding.ActivityStartBinding
import com.georgegipa.gym.utils.isUserLoggedIn
import com.georgegipa.gym.utils.saveUserCredentials
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.text.Editable.Factory.getInstance
import androidx.appcompat.app.AppCompatDelegate
import com.georgegipa.gym.models.UserBody
import com.georgegipa.gym.utils.getUserCode
import com.georgegipa.gym.utils.getUserEmail
import kotlinx.coroutines.launch

class StartActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityStartBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) //disable dark mode
        _binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (!isUserLoggedIn()) {
            val button = findViewById<android.widget.Button>(R.id.button)
            button.setOnClickListener {
                if (binding.editTextTextEmailAddress.text.toString() == TEMP_EMAIL
                    && binding.editTextTextPassword.text.toString() == TEMP_CODE
                ) {
                    //close the keyboard if it's open
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
                    imm.hideSoftInputFromWindow(binding.editTextTextEmailAddress.windowToken, 0)
                    imm.hideSoftInputFromWindow(binding.editTextTextPassword.windowToken, 0)
                    saveUserCredentials(binding.editTextTextEmailAddress.text.toString(), binding.editTextTextPassword.text.toString())
                    initNow()
                } else{
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Error")
                        .setMessage("Invalid email or password.")
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }
        } else {
            binding.editTextTextEmailAddress.text = getInstance().newEditable(getUserEmail())
            binding.editTextTextPassword.text = getInstance().newEditable(getUserCode())
            initNow()
        }

    }

    private fun initNow() {
        //make the editText not editable
        binding.editTextTextEmailAddress.isEnabled = false
        binding.editTextTextPassword.isEnabled = false
        binding.progressBar.visibility = android.view.View.VISIBLE
        binding.button.isEnabled = false
        binding.button.text = "Signing in..."
        lifecycleScope.launch {
            if (!ApiResponses.init(UserBody(TEMP_EMAIL, "1234"))) {
                //run on the main thread
                runOnUiThread {
                    MaterialAlertDialogBuilder(this@StartActivity)
                        .setCancelable(false)
                        .setTitle("Error")
                        .setMessage("An error occurred while fetching data from the server. Please try again later.")
                        .setPositiveButton("Retry") { dialog, _ ->
                            dialog.dismiss()
                            initNow()
                        }
                        .setNegativeButton("Exit") { dialog, _ ->
                            dialog.dismiss()
                            finish()
                        }
                        .show()
                }
            } else {
                val intent = Intent(this@StartActivity, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }

}