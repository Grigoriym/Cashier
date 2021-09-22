package com.grappim.cashier.domain.extension

/**
 * Use this type when the Use Case is should not receive
 * any arguments. This is more readable way.
 */
typealias WithoutParams = Any?

/**
 * Use this function to execute Use Case without parameters.
 */
fun withoutParams(): WithoutParams = null