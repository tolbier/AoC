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
        """, """
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
        """
    )

    val rawMovements: List<String> = listOf(
        "<^^>>>vv<v>>v<<",
        """
            <vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^
            vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v
            ><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<
            <<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^
            ^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><
            ^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^
            >^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^
            <><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>
            ^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>
            v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^
        """
    )
    val puzzles = rawPuzzles.map { it.trimIndent().buildPuzzle() }
    val movements = rawMovements.map { it.trimIndent().replace("\n", "").buildMovements() }

    @Test
    fun draw() {
        assertEquals(rawPuzzles[0].trimIndent(), puzzles[0].draw())
    }

    @Test
    fun orderMovements() {
        puzzles.forEachIndexed { idx, puzzle ->
            assertEquals(
                expectedRawPuzzles[idx].trimIndent(), puzzle.order(movements[idx]).draw()
            )
        }
    }

    @Test
    fun orderMovement() {
        assertEquals(
            """
            ########
            #..O.O.#
            ##..O..#
            #.@.O..#
            #.#.O..#
            #...O..#
            #......#
            ########
        """.trimIndent(), puzzles[0].order(Movement.SOUTH).draw()
        )
        assertEquals(
            rawPuzzles[0].trimIndent(), puzzles[0].order(Movement.WEST).draw()
        )
        assertEquals(
            """
            ########
            #.@O.O.#
            ##..O..#
            #...O..#
            #.#.O..#
            #...O..#
            #......#
            ########
        """.trimIndent(), puzzles[0].order(Movement.NORTH).draw()
        )
        assertEquals(
            """
            ########
            #..@OO.#
            ##..O..#
            #...O..#
            #.#.O..#
            #...O..#
            #......#
            ########
        """.trimIndent(),
            puzzles[0]
                .order(Movement.NORTH)
                .order(Movement.EAST)
                .draw()
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
}
