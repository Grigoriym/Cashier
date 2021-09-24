package com.grappim.cashier.data.db.converter

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class BaseListsConverter {

    @TypeConverter
    fun listToString(list: List<String>): String = Json.encodeToString(list)

    @TypeConverter
    fun stringToList(value: String): List<String> = Json.decodeFromString<List<String>>(value)

}