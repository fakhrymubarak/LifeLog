package com.fakhry.lifelog.settings.reminders.reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.fakhry.lifelog.settings.reminders.utils.showReminderNotification

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            showReminderNotification(context)
        }
    }
}