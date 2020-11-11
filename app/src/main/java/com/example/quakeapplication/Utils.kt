package com.example.quakeapplication

import android.content.res.Resources
import androidx.core.content.res.ResourcesCompat
import java.text.DateFormat
import java.util.*

fun formatDate(date: Date): String {
    return DateFormat.getInstance().format(date)
}

fun convertMMItoColor(magnitude: Int, res: Resources): Int =
    when(magnitude) {
        in 0..3 -> ResourcesCompat.getColor(res, R.color.colorGreen, null)
        in 4..6 -> ResourcesCompat.getColor(res, R.color.colorAmber, null)
        else -> ResourcesCompat.getColor(res, R.color.colorError, null)
    }
