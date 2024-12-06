package com.xing.aoc24.day6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DirectionTest {
    @BeforeEach
    fun setUp() {
    }

    @Test
    fun goStepNorth() {
        val coords = Direction.NORTH.goStep(Coords(1, 2))
        assertEquals(Coords(1, 1), coords)
    }

    @Test
    fun goStepEast() {
        val coords = Direction.EAST.goStep(Coords(1, 2))
        assertEquals(Coords(2, 2), coords)
    }

    @Test
    fun goStepSouth() {
        val coords = Direction.SOUTH.goStep(Coords(1, 2))
        assertEquals(Coords(1, 3), coords)
    }

    @Test
    fun goStepWest() {
        val coords = Direction.WEST.goStep(Coords(1, 2))
        assertEquals(Coords(0, 2), coords)
    }

    @Test
    fun turn() {
        assertEquals(Direction.EAST, Direction.NORTH.turn())
        assertEquals(Direction.SOUTH, Direction.EAST.turn())
        assertEquals(Direction.WEST, Direction.SOUTH.turn())
        assertEquals(Direction.NORTH, Direction.WEST.turn())
    }
}