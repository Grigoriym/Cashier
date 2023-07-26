package com.grappim.extensions

import android.content.Context
import androidx.core.content.ContextCompat
import com.grappim.uikit.R

fun Context.getBlue(): Int =
    ContextCompat.getColor(
        this,
        R.color.cashier_blue
    )

fun Context.getWhite(): Int =
    ContextCompat.getColor(
        this,
        R.color.cashier_white
    )

fun Context.getBlack(): Int =
    ContextCompat.getColor(
        this,
        R.color.cashier_black
    )

fun Context.getGray(): Int =
    ContextCompat.getColor(
        this,
        R.color.cashier_gray
    )