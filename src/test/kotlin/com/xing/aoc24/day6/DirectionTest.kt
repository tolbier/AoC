package com.xing.aoc24.day6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DirectionTest {
    @BeforeEach
    fun setUp() {
    }


    @Test
    fun turn() {
        assertEquals(Direction.EAST, Direction.NORTH.turn())
        assertEquals(Direction.SOUTH, Direction.EAST.turn())
        assertEquals(Direction.WEST, Direction.SOUTH.turn())
        assertEquals(Direction.NORTH, Direction.WEST.turn())
    }
}