package com.grappim.product_category.db

import com.grappim.productcategory.db.ProductCategoryEntityMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductCategoryEntityMapperTest {

    private val mapper = ProductCategoryEntityMapper()

    @Test
    fun `map productCategory to productCategoryEntity`() {
        val expected = createProductCategory()
        val actual = mapper.map(expected)

        assertEquals(
            expected.id,
            actual.id
        )
        assertEquals(
            expected.merchantId,
            actual.merchantId
        )
        assertEquals(
            expected.name,
            actual.name
        )
        assertEquals(
            expected.stockId,
            actual.stockId
        )
    }

    @Test
    fun `map productCategory List to productCategoryEntity List`() {
        val expected = createProductCategoryList(4)
        val actual = mapper.mapList(expected)

        assertEquals(
            expected[0].id,
            actual[0].id
        )
        assertEquals(
            expected[0].merchantId,
            actual[0].merchantId
        )
        assertEquals(
            expected[0].name,
            actual[0].name
        )
        assertEquals(
            expected[0].stockId,
            actual[0].stockId
        )

        assertEquals(
            expected[1].id,
            actual[1].id
        )

        assertEquals(
            expected[2].name,
            actual[2].name
        )
    }

    @Test
    fun revert() {
        val expected = createProductCategoryEntity()
        val actual = mapper.revert(expected)

        assertEquals(
            expected.id,
            actual.id
        )
        assertEquals(
            expected.merchantId,
            actual.merchantId
        )
        assertEquals(
            expected.name,
            actual.name
        )
        assertEquals(
            expected.stockId,
            actual.stockId
        )
    }

    @Test
    fun revertList() {
        val expected = createProductCategoryEntityList(4)
        val actual = mapper.revertList(expected)

        assertEquals(
            expected[0].id,
            actual[0].id
        )
        assertEquals(
            expected[0].merchantId,
            actual[0].merchantId
        )
        assertEquals(
            expected[0].name,
            actual[0].name
        )
        assertEquals(
            expected[0].stockId,
            actual[0].stockId
        )

        assertEquals(
            expected[1].id,
            actual[1].id
        )

        assertEquals(
            expected[2].name,
            actual[2].name
        )
    }
}