package com.grappim.db.helper

import androidx.sqlite.db.SimpleSQLiteQuery

object RoomQueryHelper {
    fun toSQLiteQuery(
        query: StringBuilder
    ): SimpleSQLiteQuery = SimpleSQLiteQuery(query.toString())
}
