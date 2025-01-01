package com.xing.aoc24.day25

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SchematicTest {
    val rawSchematics: List<String> = listOf(
        """
            #####
            .####
            .####
            .####
            .#.#.
            .#...
            .....
        """, """  
            .....
            #....
            #....
            #...#
            #.#.#
            #.###
            #####
        """
    )
    val schematicsList = rawSchematics.map { Schematic.build(it.trimIndent()) }
    val expectedSchematics =
        listOf(
            Schematic.Lock(listOf(0, 5, 3, 4, 3)),
            Schematic.Key(listOf(5, 0, 2, 1, 3)),
        )

    @Test
    fun build() {
        schematicsList.forEachIndexed { idx, schematic ->
            assertEquals(expectedSchematics[idx], schematic)
        }
    }

    @Test
    fun draw() {
        assertEquals("L:1,2,3,4,5", Schematic.Lock(listOf(1, 2, 3, 4, 5)).draw())
        assertEquals("K:1,2,3,4,5", Schematic.Key(listOf(1, 2, 3, 4, 5)).draw())
    }

}
