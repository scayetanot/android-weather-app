package com.example.weather.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.format.DateFormat
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import java.util.*

fun convertToReadableDate(timestamp: Long): String {
    val cal = Calendar.getInstance(Locale.ENGLISH)
    cal.timeInMillis = timestamp * 1000
    return DateFormat.format("dd-MM-yyyy  HH:mm:ss", cal).toString()
}

fun convertToReadableHour(timestamp: Long): String {
    val cal = Calendar.getInstance(Locale.ENGLISH)
    cal.timeInMillis = timestamp * 1000
    return DateFormat.format("HH", cal).toString()
}

fun formatTemperature(temperature: Float?): String {
    return temperature?.toInt().toString() + "\u2109"
}

fun findDrawable(context: Context, name: String): Drawable {
    val drawableId = context.resources.getIdentifier(name.replace("-",""), "drawable", context.packageName)
    return context.resources.getDrawable(drawableId)
};

fun AppCompatActivity.replaceFragment(fragment: Fragment, container: Int) {
    val className = fragment.javaClass.name
    supportFragmentManager
            .beginTransaction()
            .replace(container, fragment, className)
            .commit()
}
