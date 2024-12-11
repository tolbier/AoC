package com.xing.aoc24.day11

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Day11Test {
    val rawRows: List<String> = listOf(
        "0 1 10 99 999",
        "125 17"
    )

    val rows: List<Row> = rawRows.map { buildRow(it) }
    lateinit var day11: Day11

    @BeforeEach
    fun setUp() {
        day11 = Day11()
    }


    @Test
    fun p1() {
        assertEquals(125681, day11.p1(rows[0]))
        assertEquals(55312, day11.p1(rows[1]))
    }

    @Test
    fun p2() {
        assertEquals(149161030616311, day11.p2(rows[0]))
        assertEquals(65601038650482, day11.p2(rows[1]))
    }


}