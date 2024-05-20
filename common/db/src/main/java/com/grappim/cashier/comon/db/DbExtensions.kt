package com.grappim.cashier.comon.db

fun String.getStringForDbQuery(): String = "%$this%"
