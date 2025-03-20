package com.fetch.hiring.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ItemMapperTest {

    private lateinit var instance: ItemMapper

    @BeforeEach
    fun setUp() {
        instance = ItemMapper()
    }

    @Test
    fun mapWithNoItems() {
        val items = emptyList<Item>()
        val actual = instance.map(items)
        val expected = emptyMap<Int, List<Item>>()
        assertEquals(expected, actual)
    }

    @Test
    fun map() {
        val item1 = Item(1, 1, "Item 1")
        val item2 = Item(2, 1, "Item 2")
        val item3 = Item(3, 2, "Item 3")
        val item4 = Item(4, 2, "Item 4")
        val item5 = Item(5, 1, "")
        val item6 = Item(6, 2, null)
        val item7 = Item(7, 1, " ")

        val items = listOf(item1, item2, item4, item3, item5, item6, item7)
        val actual = instance.map(items)
        val expected = mapOf<Int, List<Item>>(1 to listOf(item1, item2), 2 to listOf(item3, item4))
        assertEquals(expected, actual)
    }

}