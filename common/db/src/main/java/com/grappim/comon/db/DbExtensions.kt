package com.grappim.comon.db

fun String.getStringForDbQuery(): String = "%$this%"