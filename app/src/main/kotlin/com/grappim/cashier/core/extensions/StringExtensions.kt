package com.grappim.cashier.core.extensions

fun String.containsSmallLatinCharacters() =
    Regex("[a-z]+").containsMatchIn(this)

fun String.containsBigLatinCharacters() =
    Regex("[A-Z]+").containsMatchIn(this)

fun String.isValidPasswordSize(): Boolean =
    this.length in 6..20

fun String.isPasswordValid(): Boolean =
    this.isValidPasswordSize() &&
        this.containsSmallLatinCharacters() &&
        this.containsBigLatinCharacters() &&
        this.containsNumbers()

fun String.containsNumbers() = Regex(".*\\d.*").containsMatchIn(this)

