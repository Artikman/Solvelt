package com.example.listofspecialties.util

import androidx.appcompat.widget.AppCompatImageView
import com.example.listofspecialties.util.Constants.BIRTHDAY_WITH_YEAR
import com.example.listofspecialties.util.Constants.DOUBLE_HYPHEN
import com.example.listofspecialties.util.Constants.EMPTY
import com.example.listofspecialties.util.Constants.HYPHEN
import com.example.listofspecialties.util.Constants.JSON_DATE_DD_MM_YYYY
import com.example.listofspecialties.util.Constants.JSON_DATE_YYYY_MM_DD
import com.example.listofspecialties.util.Constants.LOCAL_DATE_DD_MM_YYYY
import com.example.listofspecialties.util.Constants.POINT
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

fun AppCompatImageView.loadImageFull(url: String?) =
    Picasso.get().load(url).into(this)

fun String.toFirstUpperCase(): String {
    return if (this.isEmpty()) {
        EMPTY
    } else {
        this.substring(0, 1).toUpperCase(Locale.getDefault()) + this.substring(1)
    }
}

fun String.convertDate(): String {
    return if (this.isEmpty()) {
        DOUBLE_HYPHEN
    } else {
        val formatter = if (this.substring(4, 5) == HYPHEN) {
            SimpleDateFormat(JSON_DATE_YYYY_MM_DD, Locale.ENGLISH)
        } else {
            SimpleDateFormat(JSON_DATE_DD_MM_YYYY, Locale.ENGLISH)
        }

        val date: Date? = formatter.parse(this)
        val dateFormat = SimpleDateFormat(LOCAL_DATE_DD_MM_YYYY, Locale.ENGLISH)
        dateFormat.format(date ?: DOUBLE_HYPHEN) + BIRTHDAY_WITH_YEAR
    }
}

fun String.calculateAge(): String {
    return if (this.isEmpty() || this == DOUBLE_HYPHEN) {
        DOUBLE_HYPHEN
    } else {
        val formatter = when {
            this.substring(2, 3) == POINT -> {
                SimpleDateFormat(LOCAL_DATE_DD_MM_YYYY, Locale.ENGLISH)
            }
            this.substring(4, 5) == HYPHEN -> {
                SimpleDateFormat(JSON_DATE_YYYY_MM_DD, Locale.ENGLISH)
            }
            else -> {
                SimpleDateFormat(JSON_DATE_DD_MM_YYYY, Locale.ENGLISH)
            }
        }

        val date: Date? = formatter.parse(this)
        val birthdayCal: Calendar = Calendar.getInstance()
        birthdayCal.timeInMillis = date?.time ?: 0L
        val todayCal: Calendar = Calendar.getInstance()
        var age: Int = todayCal.get(Calendar.YEAR) - birthdayCal.get(Calendar.YEAR)
        if (todayCal.get(Calendar.DAY_OF_MONTH) < birthdayCal.get(Calendar.DAY_OF_MONTH)) {
            age--
        }
        age.toString()
    }
}