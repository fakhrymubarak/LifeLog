package com.fakhry.lifelog.base

import android.content.Context
import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class BaseFunction(context: Context) {

    fun epochToDate(dateInMilliseconds: String): String {
        val dateFormat = "dd/MM/yyyy"
        return DateFormat.format("dd/MM/yyyy", dateInMilliseconds.toLong()).toString()
    }

    fun dateToFormalString(completeDate: String): String {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(completeDate)!!
        //Sun Jan 31 00:00:00

        val finalDay = SimpleDateFormat("EEEE", Locale.getDefault()).format(format)
        val finalDate = SimpleDateFormat("dd", Locale.getDefault()).format(format)
        val finalMonth = SimpleDateFormat("MMMM", Locale.getDefault()).format(format)
        val finalYear = SimpleDateFormat("yyyy", Locale.getDefault()).format(format)

        return "$finalDay, $finalDate $finalMonth $finalYear"
    }
}