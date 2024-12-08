package com.xing.aoc24.day6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GuardTest {
    val guards: List<Guard> = listOf(
        Guard(Direction.NORTH, Coords(4, 6)),
        Guard(Direction.EAST, Coords(5, 3)),
        Guard(Direction.SOUTH, Coords(5, 3)),
        Guard(Direction.WEST, Coords(5, 3))
    )

    @BeforeEach
    fun setUp() {

    }


    @Test
    fun nextStep() {
        assertEquals(Coords(4, 5), guards[0].nextCoords())
        assertEquals(Coords(6, 3), guards[1].nextCoords())
        assertEquals(Coords(5, 4), guards[2].nextCoords())
        assertEquals(Coords(4, 3), guards[3].nextCoords())
    }

    @Test
    fun turn() {
        assertEquals(Guard(Direction.EAST, Coords(4, 6)), guards[0].turn())
        assertEquals(Guard(Direction.SOUTH, Coords(5, 3)), guards[1].turn())
        assertEquals(Guard(Direction.WEST, Coords(5, 3)), guards[2].turn())
        assertEquals(Guard(Direction.NORTH, Coords(5, 3)), guards[3].turn())
    }

}