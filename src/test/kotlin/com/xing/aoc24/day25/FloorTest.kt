package com.xing.aoc24.day25

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FloorTest {
    val rawFloors: List<String> = listOf(
        """
            #####
            .####
            .####
            .####
            .#.#.
            .#...
            .....
            
            #####
            ##.##
            .#.##
            ...##
            ...#.
            ...#.
            .....
            
            .....
            #....
            #....
            #...#
            #.#.#
            #.###
            #####
            
            .....
            .....
            #.#..
            ###..
            ###.#
            ###.#
            #####
            
            .....
            .....
            .....
            #....
            #.#..
            #.#.#
            #####
        """
    )
    val floors = rawFloors.map { buildFloor(it.trimIndent()) }
    val expectedFloors: List<Floor> =
        listOf(
            listOf(
                Schematic.Lock(listOf(0, 5, 3, 4, 3)),
                Schematic.Lock(listOf(1, 2, 0, 5, 3)),
                Schematic.Key(listOf(5, 0, 2, 1, 3)),
                Schematic.Key(listOf(4, 3, 4, 0, 2)),
                Schematic.Key(listOf(3, 0, 2, 0, 1))
            )
        )
    val expectedFitsNumbers = listOf(3)

    @Test
    fun buildFloor() {
        floors.forEachIndexed { idx, floor ->
            assertEquals(expectedFloors[idx], floor)
        }
    }

    @Test
    fun getFitsNumber() {
        val fitsnumber = floors.map { floor -> floor.getFitsNumber() }
        assertEquals(expectedFitsNumbers, fitsnumber)
    }

}
