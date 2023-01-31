package com.georgegipa.gym.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.georgegipa.gym.R
import com.georgegipa.gym.api.ApiResponses
import com.georgegipa.gym.databinding.ActivityMainBinding
import com.georgegipa.gym.utils.getGreeting
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val bottomNav: BottomNavigationView by lazy { binding.bottomNavigation }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.coursesFragment, R.id.myProfileFragment)
        )

        //set bottom navigation
        bottomNav.setupWithNavController(navController)
    }

    fun changeGreetingTitleBar(title : String) {
        binding.greetingTv.text = title
    }

}