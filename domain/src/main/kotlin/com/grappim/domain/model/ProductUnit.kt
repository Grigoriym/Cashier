package com.grappim.domain.model

enum class ProductUnit(
    val value: String,
) {
    PIECE("pc"),
    KG("kg"),
    GRAM("g"),
    LITRE("l"),
    METER("m"),
    METER_2("m2"),
    METER_3("m3");

    companion object {

        fun getProductUnitByValue(value: String?): ProductUnit =
            when (value) {
                KG.value -> KG
                PIECE.value -> PIECE
                GRAM.value -> GRAM
                LITRE.value -> LITRE
                METER.value -> METER
                METER_2.value -> METER_2
                METER_3.value -> METER_3
                else -> PIECE
            }

        fun getValuesForDemonstration(): List<String> =
            ProductUnit
                .values()
                .asList()
                .map {
                    it.value
                }
    }
}