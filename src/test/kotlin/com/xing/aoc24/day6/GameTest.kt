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
        println(games[0].playP1().draw())
        assertEquals(
            """
                ....#.....
                ....EEEES#
                ....N...S.
                ..#.N...S.
                ..EEEES#S.
                ..N.N.S.S.
                .#NWWWSWW.
                .EEEEEES#.
                #NWWWWWS..
                ......#S..
            """.buildMaze(),
            games[0].playP1()
        )
    }


}