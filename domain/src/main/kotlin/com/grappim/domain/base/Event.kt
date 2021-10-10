package com.grappim.domain.base

data class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = false
            content
        }
    }

    fun peekContent(): T = content
}