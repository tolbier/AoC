package com.xing.aoc24.day15

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PuzzleTest {
    val rawPuzzles: List<String> = listOf(
        """
            ########
            #..O.O.#
            ##@.O..#
            #...O..#
            #.#.O..#
            #...O..#
            #......#
            ########
        """,
        """
            ##########
            #..O..O.O#
            #......O.#
            #.OO..O.O#
            #..O@..O.#
            #O#..O...#
            #O..O..O.#
            #.OO.O.OO#
            #....O...#
            ##########
        """,
        """
            #######
            #...#.#
            #.....#
            #..OO@#
            #..O..#
            #.....#
            #######
        """,
        """
            ##############
            ##...[].##..##
            ##...@.[]...##
            ##....[]....##
            ##..........##
            ##..........##
            ##############
        """,
        """
            #######
            #...#.#
            #.....#
            #..O@O#
            #..O#.#
            #.....#
            #######
        """,
        """
            ##############
            ##.....##...##
            ##......#...##
            ##..[][]....##
            ##...[].....##
            ##[][]@[]...##
            ##...[].....##
            ##..........##
            ##############
        """
    )
    val expectedRawPuzzles: List<String> = listOf(
        """
    ########
    #....OO#
    ##.....#
    #.....O#
    #.#O@..#
    #...O..#
    #...O..#
    ########
        """, """
    ##########
    #.O.O.OOO#
    #........#
    #OO......#
    #OO@.....#
    #O#.....O#
    #O.....OO#
    #O.....OO#
    #OO....OO#
    ##########
        """, """
            #######
            #@..#.#
            #.O...#
            #..O..#
            #..O..#
            #.....#
            #######
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
    fun robotMove() {
        assertEquals(
            Puzzle(
                """
            #######
            #...#.#
            #...@.#
            #..O.O#
            #..O#.#
            #.....#
            #######
        """
            ), puzzles[4].moveRobot(Movement.NORTH)
        )
        assertEquals(puzzles[4], puzzles[4].moveRobot(Movement.EAST))
        assertEquals(puzzles[4], puzzles[4].moveRobot(Movement.SOUTH))
        assertEquals(

            """
            #######
            #...#.#
            #.....#
            #.O@.O#
            #..O#.#
            #.....#
            #######
        """.trimIndent(), puzzles[4].moveRobot(Movement.WEST).draw()
        )

        assertEquals(
            """
            ##############
            ##.....##...##
            ##..[][]#...##
            ##...[].....##
            ##....@.....##
            ##[][].[]...##
            ##...[].....##
            ##..........##
            ##############
        """.trimIndent(), puzzles[5].moveRobot(Movement.NORTH).draw()
        )
        assertEquals(
            """
            ##############
            ##.....##...##
            ##..[][]#...##
            ##...[].....##
            ##....@.....##
            ##[][].[]...##
            ##...[].....##
            ##..........##
            ##############
        """.trimIndent(), puzzles[5].moveRobot(Movement.NORTH).moveRobot(Movement.NORTH).draw()
        )
        assertEquals(
            """
                    ##############
                    ##.....##...##
                    ##......#...##
                    ##..[][]....##
                    ##...[].....##
                    ##[][].@[]..##
                    ##...[].....##
                    ##..........##
                    ##############
        """.trimIndent(), puzzles[5].moveRobot(Movement.EAST).draw()
        )
        assertEquals(
            """
                    ##############
                    ##.....##...##
                    ##......#...##
                    ##..[][]....##
                    ##...[].....##
                    ##[][].[]...##
                    ##....@.....##
                    ##...[].....##
                    ##############
        """.trimIndent(), puzzles[5].moveRobot(Movement.SOUTH).draw()
        )
        assertEquals(
            """
                    ##############
                    ##.....##...##
                    ##......#...##
                    ##..[][]....##
                    ##...[].....##
                    ##[][]@[]...##
                    ##...[].....##
                    ##..........##
                    ##############
        """.trimIndent(), puzzles[5].moveRobot(Movement.WEST).draw()
        )

    }

    @Test
    fun wider() {
        val puzzle = """
        #######
        #...#.#
        #.....#
        #..OO@#
        #..O..#
        #.....#
        #######
         """.trimIndent()
        assertEquals(
            """
             ##############
             ##......##..##
             ##..........##
             ##....[][]@.##
             ##....[]....##
             ##..........##
             ##############
        """.trimIndent(), puzzle.wider()
        )
    }

    @Test
    fun sumAllBoxesGPSCoords() {
        assertEquals(
            9021L, Puzzle(
                """
            ####################
            ##[].......[].[][]##
            ##[]...........[].##
            ##[]........[][][]##
            ##[]......[]....[]##
            ##..##......[]....##
            ##..[]............##
            ##..@......[].[][]##
            ##......[][]..[]..##
            ####################
        """
            ).sumAllBoxesGPSCoords()
        )
    }
}
