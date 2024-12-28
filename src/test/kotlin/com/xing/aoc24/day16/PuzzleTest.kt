package com.xing.aoc24.day16

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PuzzleTest {
    val rawPuzzles: List<String> = listOf(
        """
        ###############
        #.......#....E#
        #.#.###.#.###.#
        #.....#.#...#.#
        #.###.#####.#.#
        #.#.#.......#.#
        #.#.#####.###.#
        #...........#.#
        ###.#.#####.#.#
        #...#.....#.#.#
        #.#.#.###.#.#.#
        #.....#...#.#.#
        #.###.#.#.#.#.#
        #S..#.....#...#
        ###############
        """,
        """
        #################
        #...#...#...#..E#
        #.#.#.#.#.#.#.#.#
        #.#.#.#...#...#.#
        #.#.#.#.###.#.#.#
        #...#.#.#.....#.#
        #.#.#.#.#.#####.#
        #.#...#.#.#.....#
        #.#.#####.#.###.#
        #.#.#.......#...#
        #.#.###.#####.###
        #.#.#...#.....#.#
        #.#.#.#####.###.#
        #.#.#.........#.#
        #.#.#.#########.#
        #S#.............#
        #################
        """,
        """
        ######
        #...E#
        #.##.#
        #.##.#
        #S...#
        ######
        """,

        )

    val puzzles = rawPuzzles.map { Puzzle(it.trimIndent()) }

    @Test
    fun draw() {
        puzzles.forEachIndexed { idx, puzzle ->
            assertEquals(rawPuzzles[idx].trimIndent(), puzzle.draw())
        }
    }

    @Test
    fun lowestScore() {
        assertEquals(7036L, puzzles[0].lowestScore())
        assertEquals(11048L, puzzles[1].lowestScore())
        assertEquals(1006L, puzzles[2].lowestScore())
    }

    @Test
    fun numberOfTilesInPaths() {
        assertEquals(45, puzzles[0].numberOfTilesInPaths())
        assertEquals(64, puzzles[1].numberOfTilesInPaths())
        assertEquals(7, puzzles[2].numberOfTilesInPaths())
    }

}
