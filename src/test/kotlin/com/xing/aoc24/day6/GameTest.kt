package com.xing.aoc24.day6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameTest {
    val rawMazes: List<String> = listOf(
        """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
        """,
        """
            ....#....#
            .........#
            ..#.......
            .#..#>....
            ....#.....
            .......#..
            ..........
            ........#.
            #.........
            ......#...
        """,

        ).map { it.trimIndent() }

    val mazes: List<Maze> = rawMazes.map { it.buildMaze() }

    val games = mazes.map { Game(it) }

    @BeforeEach
    fun setUp() {

    }

    @Test
    fun playReturnsMaze() {
        println(games[0].play().draw())
        assertEquals(
            """
                ....#.....
                ....XXXXX#
                ....X...X.
                ..#.X...X.
                ..XXXXX#X.
                ..X.X.X.X.
                .#XXXXXXX.
                .XXXXXXX#.
                #XXXXXXX..
                ......#X..
            """.buildMaze(),
            games[0].play()
        )
    }


}