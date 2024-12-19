package com.xing.aoc24.day12

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GardenMapTest {
    val rawMazes: List<String> = listOf(
        """
        AAA
        BBC
        BBC
        """,
        """
        AAAA
        BBCD
        BBCC
        EEEC
        """,
        """
        RRRRIICCFF
        RRRRIICCCF
        VVRRRCCFFF
        VVRCCCJFFF
        VVVVCJJCFE
        VVIVCCJJEE
        VVIIICJJEE
        MIIIIIJJEE
        MIIISIJEEE
        MMMISSJEEE
        """
    )

    @BeforeEach
    fun setUp() {

    }

    @Test
    fun buildFromRawMap() {
        val gardenMap = GardenMap(rawMazes[0])
        assertEquals(
            GardenMap(
                mapOf(
                    Coords(x = 0, y = 0) to 'A',
                    Coords(x = 1, y = 0) to 'A',
                    Coords(x = 2, y = 0) to 'A',
                    Coords(x = 0, y = 1) to 'B',
                    Coords(x = 1, y = 1) to 'B',
                    Coords(x = 2, y = 1) to 'C',
                    Coords(x = 0, y = 2) to 'B',
                    Coords(x = 1, y = 2) to 'B',
                    Coords(x = 2, y = 2) to 'C',
                )
            ), gardenMap
        )
    }

    @Test
    fun validCoords() {
        val gardenMap = GardenMap(rawMazes[1])
        assertTrue(gardenMap.validCoords(0, 0))
        assertTrue(gardenMap.validCoords(3, 3))
        assertTrue(gardenMap.validCoords(0, 3))
        assertTrue(gardenMap.validCoords(3, 0))

        assertFalse(gardenMap.validCoords(-1, 0))
        assertFalse(gardenMap.validCoords(3, -3))
        assertFalse(gardenMap.validCoords(0, 4))
        assertFalse(gardenMap.validCoords(4, 0))
    }

    @Test
    fun getCoords() {
        val gardenMap = GardenMap(rawMazes[0])
        assertEquals(
            listOf(
                0 to 0,
                0 to 1,
                0 to 2,
                1 to 0,
                1 to 1,
                1 to 2,
                2 to 0,
                2 to 1,
                2 to 2
            ).map { Coords(it.first, it.second) }.toSet(), gardenMap.getCoords()
        )
    }

    @Test
    fun getRegions() {
        val gardenMap = GardenMap(rawMazes[0])

        assertEquals(
            listOf(
                Pair('A', listOf(0 to 0, 1 to 0, 2 to 0)),
                Pair('B', listOf(0 to 1, 1 to 1, 1 to 2, 0 to 2)),
                Pair('C', listOf(2 to 1, 2 to 2)),
            ).map {
                Region(it.first, it.second.map { Coords(it.first, it.second) }.toSet())
            }.toSet(), gardenMap.getRegions()
        )


    }

    @Test
    fun getSumOfPerimeterCosts() {
        val gardenMap1 = GardenMap(rawMazes[1])
        val gardenMap2 = GardenMap(rawMazes[2])

//        assertEquals(140, gardenMap1.getSumOfPerimeterCosts())
        assertEquals(1930, gardenMap2.getSumOfPerimeterCosts())
    }
}