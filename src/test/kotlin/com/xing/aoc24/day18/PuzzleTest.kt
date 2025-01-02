package com.xing.aoc24.day18

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PuzzleTest {
    val rawPuzzles = listOf(
        Pair(
            12,
            """
            5,4
            4,2
            4,5
            3,0
            2,1
            6,3
            2,4
            1,5
            0,6
            3,3
            2,6
            5,1
            1,2
            5,5
            2,5
            6,5
            1,4
            0,4
            6,4
            1,1
            6,1
            1,0
            0,5
            1,6
            2,0
        """
        )
    )
    val puzzles = rawPuzzles.map { (fallenBytes, rawPuzzle) -> Puzzle(rawPuzzle, fallenBytes) }
    val expectedPuzzles = listOf(
        """
            ...#...
            ..#..#.
            ....#..
            ...#..#
            ..#..#.
            .#..#..
            #.#....
        """
    ).map { it.trimIndent() }

    @Test
    fun buildAndDraw() {
        puzzles.forEachIndexed { idx, puzzle ->
            assertEquals(expectedPuzzles[idx], puzzle.draw())
        }
    }

    @Test
    fun dijkstra() {
        val expectedLowScores = listOf(22L)
        puzzles.forEachIndexed { idx, puzzle ->
            assertEquals(expectedLowScores[idx], puzzle.lowestDijkstraScore())
        }
    }
}