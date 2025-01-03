package com.xing.aoc24.day20

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@Suppress("UNCHECKED_CAST")
class PuzzleTest {
    val rawPuzzles: List<String> = listOf(
        """
            ###############
            #...#...#.....#
            #.#.#.#.#.###.#
            #S#...#.#.#...#
            #######.#.#.###
            #######.#.#...#
            #######.#.###.#
            ###..E#...#...#
            ###.#######.###
            #...###...#...#
            #.#####.#.###.#
            #.#...#.#.#...#
            #.#.#.#.#.#.###
            #...#...#...###
            ###############
        """
    )

    val puzzles = rawPuzzles.map { Puzzle(it.trimIndent()) }

    @Test
    fun draw() {
        puzzles.forEachIndexed { idx, puzzle ->
            assertEquals(rawPuzzles[idx].trimIndent(), puzzle.draw())
        }
    }

    @Test
    fun lowestPsecond() {
        assertEquals(
            84,
            puzzles[0].lowestPsecond()
        )
    }
//
//    @Test
//    fun calculateNumberofCheatsToPseconds() {
//        assertEquals(
//            mapOf<Psecond, Int>(
//                2 to 14,
//                4 to 14,
//                6 to 2,
//                8 to 4,
//                10 to 2,
//                12 to 3,
//                20 to 1,
//                36 to 1,
//                38 to 1,
//                40 to 1,
//                64 to 1
//            ), puzzles[0].calculateNumberofCheatsToSavePseconds()
//        )
//    }
}