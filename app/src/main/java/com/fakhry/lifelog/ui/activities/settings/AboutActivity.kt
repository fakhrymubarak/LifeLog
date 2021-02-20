package com.fakhry.lifelog.ui.activities.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fakhry.lifelog.BuildConfig
import com.fakhry.lifelog.R
import com.fakhry.lifelog.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.tvAppVersion.text = getString(R.string.text_app_version, BuildConfig.VERSION_NAME)
        binding.btnBack.setOnClickListener { onBackPressed() }
    }
}