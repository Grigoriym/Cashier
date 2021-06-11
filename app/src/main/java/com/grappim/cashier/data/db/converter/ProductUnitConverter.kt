package com.grappim.cashier.data.db.converter

import androidx.room.TypeConverter
import com.grappim.cashier.core.utils.ProductUnit

class ProductUnitConverter {

    @TypeConverter
    fun unitToString(unit: ProductUnit): String =
        unit.value

    @TypeConverter
    fun stringToUnit(value: String): ProductUnit =
        ProductUnit.getProductUnitByValue(value)
}