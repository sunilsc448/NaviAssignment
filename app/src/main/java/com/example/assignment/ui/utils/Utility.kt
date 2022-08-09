package com.example.assignment.ui.utils

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class Utility {
    companion object{
        fun getReadableDate(input:String):String{
            val inputDateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
            val outputDateFormat = "yyyy-MM-dd hh:mm:ss a"
            val inputTimeZone = "GMT"
            val outputTimeZone = "Asia/Kolkata"
            return try {
                val inputSimpleDateFormat = SimpleDateFormat(inputDateFormat, Locale.ENGLISH)
                inputSimpleDateFormat.timeZone = TimeZone.getTimeZone(inputTimeZone)
                val parsedDate = inputSimpleDateFormat.parse(input)
                val outputSimpleDateFormat = SimpleDateFormat(outputDateFormat,Locale.ENGLISH)
                outputSimpleDateFormat.timeZone = TimeZone.getTimeZone(outputTimeZone)
                outputSimpleDateFormat.format(parsedDate!!)
            } catch (e: Exception) {
                input
            }
        }
    }
}