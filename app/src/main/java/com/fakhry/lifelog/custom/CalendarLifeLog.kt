package com.fakhry.lifelog.custom

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.DatePicker
import androidx.core.content.ContextCompat

class CalendarLifeLog : DatePicker {
    constructor(context: Context) : super(context) {

    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        setBackgroundColor(ContextCompat.getColor(context,android.R.color.transparent))


    }
}