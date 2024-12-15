package com.fakhry.lifelog.ui.activities.splashscreen

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.fakhry.lifelog.databinding.ActivitySplashScreenBinding
import com.fakhry.lifelog.storage.preferences.LifeLogPreferences
import com.fakhry.lifelog.ui.activities.main.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var preferences: LifeLogPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        preferences = LifeLogPreferences(this)

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.WHITE
        GlobalScope.launch {
            delay(2000L)
            if (preferences.getValueBool("openFirstTime", true)) {
                val intent = Intent(
                    this@SplashScreenActivity,
                    OnBoardingActivity::class.java
                )
                startActivity(intent)
            } else {
                val intent = Intent(
                    this@SplashScreenActivity,
                    MainActivity::class.java
                )
                startActivity(intent)
            }
            finishAffinity()
        }
    }
}