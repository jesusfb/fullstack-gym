package com.georgegipa.gym.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.georgegipa.gym.R
import com.georgegipa.gym.api.ApiResponses
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_start)

        lifecycleScope.launch {
            if (ApiResponses.init()) {
                startActivity(Intent(this@StartActivity, MainActivity::class.java))
                finish()
            } else {
                //run on the main thread
                runOnUiThread {
                    MaterialAlertDialogBuilder(this@StartActivity)
                        .setCancelable(false)
                        .setTitle("Error")
                        .setMessage("An error occurred while fetching data from the server. Please try again later.")
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                            finish()
                        }
                        .show()
                }
            }

        }
    }


}