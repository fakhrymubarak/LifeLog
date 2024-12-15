package com.fakhry.lifelog.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.fakhry.lifelog.R
import com.fakhry.lifelog.ui.activities.main.MainActivity


const val ID_REPEATING = 101
const val TYPE_ONE_TIME = "OneTimeAlarm"
const val TYPE_REPEATING = "RepeatingAlarm"
private const val CHANNEL_ID = "Channel_1"
private const val CHANNEL_NAME = "AlarmManager channel"

fun showReminderNotification(context: Context) {
    val title = context.getString(R.string.notification_title)
    val message = context.getString(R.string.notification_message)

    val notificationManagerCompat =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

    val intent = Intent(context, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(
        context, 0, intent, PendingIntent.FLAG_IMMUTABLE
    )

    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setContentIntent(pendingIntent)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle(title)
        .setContentText(message)
        .setColor(ContextCompat.getColor(context, android.R.color.transparent))
        .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
        .setSound(alarmSound)
        .setAutoCancel(true) // This ensures the notification is cleared when clicked

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH
        )

        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)

        builder.setChannelId(CHANNEL_ID)

        notificationManagerCompat.createNotificationChannel(channel)
    }

    val notification = builder.build()
    notificationManagerCompat.notify(ID_REPEATING, notification)
}
