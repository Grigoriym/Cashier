package com.grappim.repository.utils

import android.content.SharedPreferences
import com.grappim.logger.logD
import com.grappim.logger.logE
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StringPreference(
  private val sharedPreferences: SharedPreferences,
  private val key: String,
  private val defaultValue: String? = null
) : ReadWriteProperty<Any, String> {

    override fun getValue(thisRef: Any, property: KProperty<*>): String =
      sharedPreferences
        .getString(key, defaultValue)
        ?: throw IllegalArgumentException("no value for $key")

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) =
      sharedPreferences
        .edit()
        .putString(key, value)
        .apply()
        .also {
          logD("asdf changing $key to $value")
        }

}

fun SharedPreferences.string(
  key: String,
  defaultValue: String? = null
): ReadWriteProperty<Any, String> =
  StringPreference(this, key, defaultValue)