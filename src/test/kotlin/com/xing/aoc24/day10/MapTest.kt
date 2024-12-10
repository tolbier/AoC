package com.xing.aoc24.day10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MapTest {


    fun buildMap(rawMap: String): Map =
        rawMap.trimIndent().split("\n").map { line ->
            line.toList().map {
                try {
                    it.toString().toInt()
                } catch (e: Exception) {
                    null
                }
            }
        }

    val rawMaps1: List<String> = listOf(
        """
0123
1234
8765
9876
        """,
        """
...0...
...1...
...2...
6543456
7.....7
8.....8
9.....9
        """,
        """
..90..9
...1.98
...2..7
6543456
765.987
876....
987....
        """,
        """
10..9..
2...8..
3...7..
4567654
...8..3
...9..2
.....01
        """,
        """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
        """

    )
    val rawMaps2: List<String> = listOf(
        """
.....0.
..4321.
..5..2.
..6543.
..7..4.
..8765.
..9....
        """,
        """
            ..90..9
            ...1.98
            ...2..7
            6543456
            765.987
            876....
            987....

        """, """
            012345
            123456
            234567
            345678
            4.6789
            56789.

        """,
        """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
        """

    )

    val maps1 = rawMaps1.map { rawMap1 ->
        buildMap(rawMap1)
    }
    val maps2 = rawMaps2.map { rawMap1 ->
        buildMap(rawMap1)
    }

    @BeforeEach
    fun setUp() {

    }

    @Test
    fun getScore() {
        assertEquals(1, maps1[0].getSumOfScores())
        assertEquals(2, maps1[1].getSumOfScores())
        assertEquals(4, maps1[2].getSumOfScores())
        assertEquals(3, maps1[3].getSumOfScores())
        assertEquals(36, maps1[4].getSumOfScores())
    }

    @Test
    fun getHeads() {
        assertEquals(1, maps1[2].getHeads(Coords(0, 3)).size)
        assertEquals(1, maps1[2].getHeads(Coords(2, 3)).size)
        assertEquals(2, maps1[2].getHeads(Coords(6, 1)).size)
        assertEquals(2, maps1[2].getHeads(Coords(6, 2)).size)
        assertEquals(1, maps1[2].getHeads(Coords(6, 4)).size)
        assertEquals(3, maps1[2].getHeads(Coords(6, 3)).size)
        assertEquals(4, maps1[2].getHeads(Coords(3, 3)).size)
    }

    @Test
    fun getRating() {
        assertEquals(3, maps2[0].getRating(Coords(5, 0)))
        assertEquals(13, maps2[1].getRating(Coords(3, 0)))
        assertEquals(227, maps2[2].getRating(Coords(0, 0)))


    }

    @Test
    fun getSumOfRatings() {
        assertEquals(81, maps2[3].getSumOfRatings())


    }
}
