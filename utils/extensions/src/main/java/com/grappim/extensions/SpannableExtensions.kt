package com.grappim.extensions

import android.graphics.Typeface
import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.AlignmentSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan

fun SpannableString.bold(start: Int = 0, end: Int = this.length): SpannableString {
    this.setSpan(StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}

fun SpannableString.normal(start: Int = 0, end: Int = this.length): SpannableString {
    this.setSpan(StyleSpan(Typeface.NORMAL), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}

fun SpannableString.color(color: Int, start: Int = 0, end: Int = this.length): SpannableString {
    this.setSpan(ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}

fun SpannableString.size(proportion: Float, start: Int, end: Int): SpannableString {
    this.setSpan(RelativeSizeSpan(proportion), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}

fun SpannableString.absoluteSize(
    proportion: Int,
    start: Int = 0,
    end: Int = this.length
): SpannableString {
    this.setSpan(
        AbsoluteSizeSpan(proportion, true),
        start,
        end,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return this
}

fun SpannableString.strike(start: Int = 0, end: Int = this.length): SpannableString {
    this.setSpan(StrikethroughSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}

fun SpannableString.align(alignment: Layout.Alignment, start: Int, end: Int): SpannableString {
    this.setSpan(AlignmentSpan.Standard(alignment), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}

fun SpannableString.italic(start: Int, end: Int): SpannableString {
    this.setSpan(StyleSpan(Typeface.ITALIC), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}

fun SpannableString.underline(start: Int = 0, end: Int = this.length): SpannableString {
    this.setSpan(UnderlineSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}
