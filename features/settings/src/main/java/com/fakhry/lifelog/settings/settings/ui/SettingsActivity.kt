package com.fakhry.lifelog.settings.settings.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import com.fakhry.lifelog.R
import com.fakhry.lifelog.components.base.BaseActivity
import com.fakhry.lifelog.navigation.Router
import com.fakhry.lifelog.settings.databinding.ActivitySettingsBinding
import com.fakhry.lifelog.settings.reminders.reciever.AlarmReceiver
import com.fakhry.lifelog.settings.reminders.ui.TimePickerFragment
import com.fakhry.lifelog.settings.reminders.utils.TYPE_REPEATING
import com.fakhry.lifelog.settings.utils.SettingsConst
import com.fakhry.lifelog.storage.preferences.LifeLogPreferences
import com.fakhry.lifelog.utils.clickWithDebounce
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.properties.Delegates

class SettingsActivity : BaseActivity<ActivitySettingsBinding>() {
    private lateinit var alarmReceiver: AlarmReceiver
    private lateinit var preferences: LifeLogPreferences
    private var isNotificationOn by Delegates.notNull<Boolean>()

    override fun getViewBinding() = ActivitySettingsBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = LifeLogPreferences(this)
        alarmReceiver = AlarmReceiver()

        initView()
        initListener()
    }

    private fun initListener() {
        binding.switchNotification.clickWithDebounce {
            switchNotification(!isNotificationOn)
        }
        binding.cvSettingsChangeLang.clickWithDebounce {
            changeLanguage()
        }
        binding.cvSettingsAbout.clickWithDebounce {
            Router.navigateToAbout(this)
        }
        binding.btnBack.clickWithDebounce {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initView() {
        isNotificationOn = preferences.getValueBool(SettingsConst.PREF_IS_NOTIFICATION_ON, false)
        binding.switchNotification.isChecked = isNotificationOn
        if (isNotificationOn) {
            setNotificationText()
        } else {
            preferences.setValues(SettingsConst.PREF_IS_NOTIFICATION_ON, isNotificationOn)
        }
    }

    private fun switchNotification(state: Boolean) {
        isNotificationOn = state
        binding.switchNotification.isChecked = isNotificationOn
        preferences.setValues(SettingsConst.PREF_IS_NOTIFICATION_ON, isNotificationOn)

        if (isNotificationOn) setNotificationTime()
        else {
            binding.tvNotificationDesc.text = getString(R.string.reminder_turned_off)
            alarmReceiver.cancelAlarm(this, TYPE_REPEATING)
        }
    }

    private fun setNotificationText() {
        val alarmTime = preferences.getValueString(SettingsConst.PREF_TIME_NOTIFICATION)
        binding.tvNotificationDesc.text = getString(R.string.reminder_turned_on, alarmTime)
    }

    private fun setNotificationTime() {
        val timePickerFragmentRepeat = TimePickerFragment()
        val mFragmentManager = supportFragmentManager
        timePickerFragmentRepeat.show(
            mFragmentManager, SettingsConst.TAG_TIME_PICKER
        )
    }

    internal var dialogTimeListener: TimePickerFragment.DialogTimeListener =
        object : TimePickerFragment.DialogTimeListener {
            override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {
                val calendar = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, hourOfDay)
                    set(Calendar.MINUTE, minute)
                }

                val dateFormat = SimpleDateFormat(SettingsConst.TIME_FORMAT, Locale.getDefault())
                val timeTaken = dateFormat.format(calendar.time)
                setRepeatAlarm(timeTaken)
            }

            override fun onCancel() {
                switchNotification(false)
            }
        }

    private fun setRepeatAlarm(repeatTime: String) {
        alarmReceiver.setRepeatingAlarm(this, repeatTime)
        preferences.setValues(SettingsConst.PREF_TIME_NOTIFICATION, repeatTime)
        setNotificationText()
    }

    private fun changeLanguage() {
        val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
        startActivity(intent)
    }
}
