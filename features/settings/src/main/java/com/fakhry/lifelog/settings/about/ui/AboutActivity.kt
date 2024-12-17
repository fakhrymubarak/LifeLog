package com.fakhry.lifelog.settings.about.ui

import android.os.Bundle
import com.fakhry.lifelog.R
import com.fakhry.lifelog.components.base.BaseActivity
import com.fakhry.lifelog.settings.BuildConfig
import com.fakhry.lifelog.settings.databinding.ActivityAboutBinding
import com.fakhry.lifelog.settings.utils.SettingsConst
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

        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val years = "${SettingsConst.APPS_CREATED_YEARS}$currentYear"
        tvMadeWithLove.text = getString(R.string.about_developers, years)
    }

    private fun initListener() {
        binding.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }
}
