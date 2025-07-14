package com.fakhry.lifelog.utils

import android.os.SystemClock
import android.view.View
import androidx.core.view.isVisible

fun <T : View> T?.clickWithDebounce(debounceTime: Long = 500L, action: T.() -> Unit) = this?.run {
    setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action(this@run)
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun View?.goneIf(isGone: Boolean) = this?.run {
    this.isVisible = !isGone
}

fun <T : View> T?.visibleIf(isVisible: Boolean, action: (T.() -> Unit)? = null) = this?.run {
    this.isVisible = isVisible
    action?.invoke(this@visibleIf)
}