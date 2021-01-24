package com.fakhry.lifelog.ui.activities.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.fakhry.lifelog.R
import com.fakhry.lifelog.base.BaseFunction
import com.fakhry.lifelog.databinding.ActivityMainBinding
import com.fakhry.lifelog.ui.activities.edit.AddUpdateActivity
import com.fakhry.lifelog.utils.Preferences

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var preferences: Preferences
    private lateinit var baseFunction: BaseFunction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        baseFunction = BaseFunction(this)

        navigationTransaction()
        actionFab()
        setPreferenceValue()
    }

    private fun setPreferenceValue() {
        preferences = Preferences(this)
        preferences.setValues("openFirstTime", false)
    }

    private fun actionFab() {
        binding.fabAddNote.setOnClickListener {
            val intent = Intent(this, AddUpdateActivity::class.java)
            startActivity(intent)
        }
    }

    private fun navigationTransaction() {
        binding.bottomNavView.background = null
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_dashboard, R.id.navigation_calendar, R.id.navigation_calendar
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_dashboard -> {
                    binding.tvTitleScreen.text = baseFunction.getFormalDate(withHours = false)
                }
                R.id.navigation_calendar -> {
                    binding.tvTitleScreen.text = "Calendar"
                }
                R.id.navigation_favorite -> {
                    binding.tvTitleScreen.text = "Favorite"
                }
            }
        }
    }
}