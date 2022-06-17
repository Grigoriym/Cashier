package com.grappim.db.converter

import androidx.room.TypeConverter
import com.grappim.domain.model.ProductUnit

class ProductUnitConverter {

    @TypeConverter
    fun unitToString(unit: ProductUnit): String =
        unit.value

    @TypeConverter
    fun stringToUnit(value: String): ProductUnit =
        ProductUnit.getProductUnitByValue(value)
}