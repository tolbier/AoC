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
    fun pSecondsMap() {
        assertEquals(85, puzzles[0].pSecondsMap.size)
        assertEquals(0, puzzles[0].pSecondsMap[Coords(1, 3)])
        assertEquals(84, puzzles[0].pSecondsMap[Coords(5, 7)])
        assertEquals(18, puzzles[0].pSecondsMap[Coords(7, 7)])
        assertEquals(null, puzzles[0].pSecondsMap[Coords(0, 0)])

    }

    @Test
    fun getPsecondsFromStartToExit() {
        assertEquals(84, puzzles[0].getPsecondsFromStartToExit())
    }

    @Test
    fun getPseconds2ExitMap() {
        val pSeconds2ExitMap = puzzles[0].getPseconds2ExitMap()
        assertEquals(85, pSeconds2ExitMap.size)
        assertEquals(84, pSeconds2ExitMap[Coords(1, 3)])
        assertEquals(0, pSeconds2ExitMap[Coords(5, 7)])
        assertEquals(66, pSeconds2ExitMap[Coords(7, 7)])
        assertEquals(null, pSeconds2ExitMap[Coords(0, 0)])
    }

    @Test
    fun getCheats() {
        val puzzle = puzzles[0]
        assertEquals(643, puzzle.getCheats().size)
    }

    @Test
    fun getCheatSavings() {
        val puzzle = puzzles[0]
        assertEquals(44, puzzle.getCheatSavings().size)
    }

    @Test
    fun getSavings() {
        val puzzle = puzzles[0]
        assertEquals(44, puzzle.getSavings().size)
    }

    @Test
    fun getSavingsCountWith2Steps() {
        val puzzle = puzzles[0]
        assertEquals(
            mapOf<Psecond, Int>(
                2 to 14,
                4 to 14,
                6 to 2,
                8 to 4,
                10 to 2,
                12 to 3,
                20 to 1,
                36 to 1,
                38 to 1,
                40 to 1,
                64 to 1
            ), puzzle.setNextStepsF(Puzzle::getNext2StepsFrom).getSavingsCount()
        )
        assertEquals(
            mapOf<Psecond, Int>(
                10 to 2,
                12 to 3,
                20 to 1,
                36 to 1,
                38 to 1,
                40 to 1,
                64 to 1
            ), puzzle.setNextStepsF(Puzzle::getNext2StepsFrom).getSavingsCount(geThanPseconds = 10)
        )
    }

    @Test
    fun getSavingsCountWith20Steps() {
        val puzzle = puzzles[0]
        assertEquals(
            mapOf<Psecond, Int>(
                50 to 32,
                52 to 31,
                54 to 29,
                56 to 39,
                58 to 25,
                60 to 23,
                62 to 20,
                64 to 19,
                66 to 12,
                68 to 14,
                70 to 12,
                72 to 22,
                74 to 4,
                76 to 3
            ), puzzle.setNextStepsF(Puzzle::getNext20StepsFrom).getSavingsCount(geThanPseconds = 50)
        )
    }
}