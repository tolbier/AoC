package com.xing.aoc23.day12

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class RecordTest {

    @Test
    fun getArrangements1() {
        val record = Record("???.###",listOf(1,1,3))
        assertEquals(1, record.getArrangements())
    }
    @Test
    fun getArrangements2() {
        val record = Record("?#?#?#?#?#?#?#?",listOf(1,3,1,6))
        assertEquals(1, record.getArrangements())
    }
    @Test
    fun getArrangements3() {
        val record = Record(".??..??...?##.",listOf(1,1,3))
        assertEquals(4, record.getArrangements())
    }
    @Test
    fun getArrangements4() {
        val record = Record("????.#...#...",listOf(4,1,1))
        assertEquals(1, record.getArrangements())
    }
    @Test
    fun getArrangements5() {
        val record = Record("????.######..#####.",listOf(1,6,5))
        assertEquals(4, record.getArrangements())
    }
    @Test
    fun getArrangements6() {
        val record = Record("?###????????",listOf(3,2,1))
        assertEquals(10, record.getArrangements())
    }
}