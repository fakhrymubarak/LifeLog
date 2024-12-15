package com.fakhry.lifelog.ui.activities.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.fakhry.lifelog.R
import com.fakhry.lifelog.utils.getFormalDate
import com.fakhry.lifelog.databinding.ActivityMainBinding
import com.fakhry.lifelog.storage.preferences.LifeLogPreferences
import com.fakhry.lifelog.ui.activities.edit.AddUpdateActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var preferences: LifeLogPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()


        navigationTransaction()
        actionButton()
        setPreferenceValue()
    }

    private fun setPreferenceValue() {
        preferences = LifeLogPreferences(this)
        preferences.setValues("openFirstTime", false)
    }

    private fun actionButton() {
        binding.fabAddNote.setOnClickListener {
            val intent = Intent(this, AddUpdateActivity::class.java)
            startActivity(intent)
        }
        binding.btnSettings.setOnClickListener {
            val intent = Intent(this, com.fakhry.lifelog.settings.settings.ui.SettingsActivity::class.java)
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
                    binding.tvTitleScreen.text =
                        com.fakhry.lifelog.utils.getFormalDate(withHours = false)
                    binding.btnSettings.visibility = View.GONE
                }
                R.id.navigation_calendar -> {
                    binding.tvTitleScreen.text = getString(R.string.title_calendar)
                    binding.btnSettings.visibility = View.GONE
                }
                R.id.navigation_favorite -> {
                    binding.tvTitleScreen.text = getString(R.string.title_favorite)
                    binding.btnSettings.visibility = View.VISIBLE
                }
            }
        }
    }
}