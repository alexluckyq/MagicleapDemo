package com.magicleap.assignment.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.magicleap.assignment.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private val DATE_TIME_FROM: SimpleDateFormat =
    SimpleDateFormat("yyyy-MM-dd HH.mm.ss", Locale.ENGLISH)

private val DATE_TIME_TO: SimpleDateFormat =
    SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)

fun String.formatTime(): CharSequence? {
    val updatedAt = this.toLongValue()
    val date = Date(updatedAt);
    if (updatedAt != -1L) {
        return DATE_TIME_TO.format(date)
    }
    return ""
}

fun String.toLongValue(): Long {
    if (this == null) return -1L
    return try {
        DATE_TIME_FROM.parse(this).time
    } catch (e: ParseException) {
        -1
    }
}

fun ImageView.loadImageUrl(url: String?, @DrawableRes placeholder: Int = R.drawable.default_thumbnail) {
    Glide.with(this).load(url).placeholder(placeholder).into(this)
}