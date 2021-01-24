package com.fakhry.lifelog.base

import android.content.Context
import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class BaseFunction(context: Context) {

    /**
     * @param timeMillis format should : epoch format(example : 1611475065784)
     * @return will be : "dd/MM/yyyy,HH:mm"
     * */
    private fun epochToDate(timeMillis: Long): String =
        DateFormat.format("dd/MM/yyyy,HH:mm", timeMillis).toString()

    /**
     * this function is wrapper from 'epochToDate' -> 'dateFormatString'
     * @param timeMillis format should : epoch format(example : 1611475065784)
     * @return format will be : "Sunday, 24 January 2021 or Sunday, 24 January 2021,16:50"
     * */
    fun getFormalDate(timeMillis: Long = System.currentTimeMillis(), withHours: Boolean): String =
        dateToFormalString(epochToDate(timeMillis), withHours)

    /**
     * @param completeDate format should : dd/MM/yyyy,HH:mm
     * @return will be : "Sunday, 24 January 2021 || Sunday, 24 January 2021,16:49"
     * */
    fun dateToFormalString(completeDate: String, withHours: Boolean): String {
        lateinit var formalStringDate: String
        val format = SimpleDateFormat("dd/MM/yyyy,HH:mm", Locale.getDefault()).parse(completeDate)
        formalStringDate = if (format != null) {
            val finalDay = SimpleDateFormat("EEEE", Locale.getDefault()).format(format)
            val finalDate = SimpleDateFormat("dd", Locale.getDefault()).format(format)
            val finalMonth = SimpleDateFormat("MMMM", Locale.getDefault()).format(format)
            val finalYear = SimpleDateFormat("yyyy", Locale.getDefault()).format(format)
            val finalHour = SimpleDateFormat("HH", Locale.getDefault()).format(format)
            val finalMinute = SimpleDateFormat("mm", Locale.getDefault()).format(format)
            if (withHours) {
                "$finalDay, $finalDate $finalMonth $finalYear, $finalHour:$finalMinute"

            } else {
                "$finalDay, $finalDate $finalMonth $finalYear"

            }
        } else {
            ""
        }
        return formalStringDate
    }
}