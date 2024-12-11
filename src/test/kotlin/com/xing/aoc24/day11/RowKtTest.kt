package com.xing.aoc24.day11

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RowKtTest {
    val rawRows: List<String> = listOf(
        "0 1 10 99 999",
        "125 17"
    )

    val rows: List<Row> = rawRows.map { buildRow(it) }

    @BeforeEach
    fun setUp() {

    }

    @Test
    fun blinkTest() {
        assertEquals(1, blink(0, 1))
        assertEquals(19778, blink(0, 25))


    }

    @Test
    fun blinkTimesAndSum() {
        assertEquals(7, rows[0].blinkTimesAndSum(1))

        assertEquals(2, rows[1].blinkTimesAndSum(0))
        assertEquals(3, rows[1].blinkTimesAndSum(1))
        assertEquals(4, rows[1].blinkTimesAndSum(2))
        assertEquals(5, rows[1].blinkTimesAndSum(3))
        assertEquals(9, rows[1].blinkTimesAndSum(4))
        assertEquals(13, rows[1].blinkTimesAndSum(5))
        assertEquals(22, rows[1].blinkTimesAndSum(6))
        assertEquals(55312, rows[1].blinkTimesAndSum(25))

    }
}