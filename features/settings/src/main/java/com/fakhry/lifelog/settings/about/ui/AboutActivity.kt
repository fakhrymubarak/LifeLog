package com.fakhry.lifelog.settings.about.ui

import android.os.Bundle
import com.fakhry.lifelog.components.base.BaseActivity
import com.fakhry.lifelog.resources.R
import com.fakhry.lifelog.settings.BuildConfig
import com.fakhry.lifelog.settings.databinding.ActivityAboutBinding
import java.util.Calendar

class AboutActivity : BaseActivity<ActivityAboutBinding>() {
    override fun getViewBinding() = ActivityAboutBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initListener()
    }

    private fun initView() = with(binding) {
        tvAppVersion.text = getString(R.string.text_app_version, BuildConfig.VERSION_NAME)

        val currentYear = Calendar.getInstance().get(Calendar.YEAR).toString()
        tvMadeWithLove.text = getString(R.string.about_developers, currentYear)
    }

    private fun initListener() {
        binding.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }
}
