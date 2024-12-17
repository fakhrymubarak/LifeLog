package com.fakhry.lifelog.storage.preferences

import android.content.Context
import android.content.SharedPreferences

class LifeLogPreferences(val context: Context) {
    companion object {
        const val MEETING_PREF = "USER_PREF"
    }

    private val sharedPref = context.getSharedPreferences(MEETING_PREF, 0)

    fun setValues(key: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

//       fun setValues(key: String, value: Int) {
//        val editor: SharedPreferences.Editor = sharedPref.edit()
//        editor.putInt(key, value)
//        editor.apply()
//    }

    fun setValues(key: String, value: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getValueBool(key: String, defaultValue: Boolean): Boolean {
        return sharedPref.getBoolean(key, defaultValue)
    }

//    fun getValueInt(key: String): Int {
//        return sharedPref.getInt(key, 0)
//    }
//
    fun getValueString(key: String): String {
        return sharedPref.getString(key, "") ?: ""
    }
//
//    fun clearSharePref() {
//        val editor: SharedPreferences.Editor = sharedPref.edit()
//        editor.clear()
//        editor.apply()
//    }
}