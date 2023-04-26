package com.countlesswrongs.cryptomonitor.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun convertTimestampToTime(timestamp: Int?): String {
    if (timestamp == null) {
        return ""
    }
    val stamp = Timestamp((timestamp * 1_000).toLong())
    val date = Date(stamp.time)
    val pattern = "HH:mm:ss"
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    simpleDateFormat.timeZone = TimeZone.getDefault()
    return simpleDateFormat.format(date)
}