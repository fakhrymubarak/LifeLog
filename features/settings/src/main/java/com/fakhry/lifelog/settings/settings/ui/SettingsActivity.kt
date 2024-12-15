package com.fakhry.lifelog.settings.settings.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fakhry.lifelog.R
import com.fakhry.lifelog.navigation.Router
import com.fakhry.lifelog.settings.databinding.ActivitySettingsBinding
import com.fakhry.lifelog.settings.reminders.reciever.AlarmReceiver
import com.fakhry.lifelog.settings.reminders.ui.TimePickerFragment
import com.fakhry.lifelog.settings.reminders.utils.TYPE_REPEATING
import com.fakhry.lifelog.storage.preferences.LifeLogPreferences
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.properties.Delegates
import com.fakhry.lifelog.settings.R as SR

class SettingsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var alarmReceiver: AlarmReceiver
    private lateinit var preferences: LifeLogPreferences
    private var isNotificationOn by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        preferences = LifeLogPreferences(this)
        alarmReceiver = AlarmReceiver()

        isNotificationOn = preferences.getValueBool("isNotificationOn", false)
        binding.switchNotification.isChecked = isNotificationOn
        if (isNotificationOn) {
            setNotificationText()
        } else {
            preferences.setValues("isNotificationOn", isNotificationOn)
        }

        binding.switchNotification.setOnClickListener(this)
        binding.cvSettingsChangeLang.setOnClickListener(this)
        binding.cvSettingsAbout.setOnClickListener(this)
        binding.cvImportExport.setOnClickListener(this)
        binding.btnBack.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            SR.id.btn_back -> onBackPressedDispatcher.onBackPressed()
            SR.id.switch_notification -> switchNotification(!isNotificationOn)
            SR.id.cv_settings_change_lang -> changeLanguage()
            SR.id.cv_settings_about -> Router.navigateToAbout(this)
            SR.id.cv_import_export -> {}
        }
    }

    private fun switchNotification(state: Boolean) {
        isNotificationOn = state
        binding.switchNotification.isChecked = isNotificationOn
        preferences.setValues("isNotificationOn", isNotificationOn)

        if (isNotificationOn) setNotificationTime()
        else {
            binding.tvNotificationDesc.text = getString(R.string.reminder_turned_off)
            alarmReceiver.cancelAlarm(this, TYPE_REPEATING)
        }
    }

    private fun setNotificationText() {
        val alarmTime = preferences.getValueString("timeNotification")
        binding.tvNotificationDesc.text = getString(R.string.reminder_turned_on, alarmTime)
    }

    private fun setNotificationTime() {
        val timePickerFragmentRepeat = TimePickerFragment()
        val mFragmentManager = supportFragmentManager
        timePickerFragmentRepeat.show(
            mFragmentManager,
            TimePickerFragment::class.java.simpleName
        )
    }

    internal var dialogTimeListener: TimePickerFragment.DialogTimeListener =
        object : TimePickerFragment.DialogTimeListener {
            override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {
                val calendar = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, hourOfDay)
                    set(Calendar.MINUTE, minute)
                }

                val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                val timeTaken = dateFormat.format(calendar.time)
                setRepeatAlarm(timeTaken)
            }
            override fun onCancel() {
                switchNotification(false)

            }
        }

    private fun setRepeatAlarm(repeatTime: String) {
        alarmReceiver.setRepeatingAlarm(this, repeatTime)
        preferences.setValues("timeNotification", repeatTime)
        setNotificationText()
    }

    private fun changeLanguage() {
        val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
        startActivity(intent)
    }
}