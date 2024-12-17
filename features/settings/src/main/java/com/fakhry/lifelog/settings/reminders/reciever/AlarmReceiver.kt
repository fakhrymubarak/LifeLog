package com.fakhry.lifelog.settings.reminders.reciever

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.fakhry.lifelog.R
import com.fakhry.lifelog.settings.reminders.utils.ID_REPEATING
import com.fakhry.lifelog.settings.reminders.utils.TYPE_ONE_TIME
import com.fakhry.lifelog.settings.reminders.utils.showReminderNotification
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        showReminderNotification(context)
    }

    fun setRepeatingAlarm(context: Context, time: String) {
        if (isDateInvalid(time)) return

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val message = "Open Favorite Activity"
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        val timeArray = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, timeArray[0].toInt())
            set(Calendar.MINUTE, timeArray[1].toInt())
            set(Calendar.SECOND, 0)
        }

        val pendingIntentBroadcast = PendingIntent.getBroadcast(
            context,
            ID_REPEATING,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE // Fixed flags
        )

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntentBroadcast
        )

        Toast.makeText(
            context, context.getString(R.string.reminder_turned_on, time), Toast.LENGTH_SHORT
        ).show()
    }

    fun cancelAlarm(context: Context, type: String) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val requestCode =
            if (type.equals(TYPE_ONE_TIME, ignoreCase = true)) ID_ONETIME else ID_REPEATING
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE // Fixed flags
        )

        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)

        Toast.makeText(context, context.getString(R.string.reminder_turned_off), Toast.LENGTH_SHORT)
            .show()
    }

    private fun isDateInvalid(date: String): Boolean {
        return try {
            val df = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
            df.isLenient = false
            df.parse(date)
            false
        } catch (e: ParseException) {
            true
        }
    }

    companion object {
        const val EXTRA_MESSAGE = "message"

        private const val ID_ONETIME = 100

        private const val TIME_FORMAT = "HH:mm"
    }
}