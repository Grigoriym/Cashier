package com.grappim.repository.extensions

fun String.getStringForDbQuery(): String = "%$this%"
