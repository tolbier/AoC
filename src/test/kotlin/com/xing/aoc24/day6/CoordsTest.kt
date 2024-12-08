package com.xing.aoc24.day6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CoordsTest {


    @Test
    fun goStepNorth() {
        val coords = Coords(1, 2).goStep(Direction.NORTH)
        assertEquals(Coords(1, 1), coords)
    }

    @Test
    fun goStepEast() {
        val coords = Coords(1, 2).goStep(Direction.EAST)
        assertEquals(Coords(2, 2), coords)
    }

    @Test
    fun goStepSouth() {
        val coords = Coords(1, 2).goStep(Direction.SOUTH)
        assertEquals(Coords(1, 3), coords)
    }

    @Test
    fun goStepWest() {
        val coords = Coords(1, 2).goStep(Direction.WEST)
        assertEquals(Coords(0, 2), coords)
    }
}