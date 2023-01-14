package com.georgegipa.gym.ui

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.georgegipa.gym.R
import com.georgegipa.gym.api.ApiResponses
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_start)
        ApiResponses.initialize {
            Log.d("StartActivity", "onCreate: $it")
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            else{
                //create a dialog to show the error
                MaterialAlertDialogBuilder(this)
                    .setTitle("Error")
                    .setMessage("There was an error while trying to connect to the server. Please try again later.")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                        finish()
                    }
                    .show()
            }
        }
    }
}