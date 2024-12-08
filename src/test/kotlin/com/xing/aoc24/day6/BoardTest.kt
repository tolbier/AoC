package com.xing.aoc24.day6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BoardTest {
    val rawBoards: List<String> = listOf(
        """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#........
            ........#.
            #.........
            ......#...
        """,
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
        """

    )

    val boards: List<Board> = rawBoards.map { Board(it) }
    val drawedBoards: List<String> = listOf(
        "....#.....\n.........#\n..........\n..#.......\n.......#..\n..........\n.#........\n........#.\n#.........\n......#...",
        "....#.....\n....EEEES#\n....N...S.\n..#.N...S.\n..EEEES#S.\n..N.N.S.S.\n.#NWWWSWW.\n.EEEEEES#.\n#NWWWWWS..\n......#S.."

    )

    @BeforeEach
    fun setUp() {

    }

    @Test
    fun drawTest() {
        boards.indices.forEach { idx ->
            assertEquals(drawedBoards[idx], boards[idx].draw())
        }
    }

    @Test
    fun getCell() {
        assertEquals('.', boards[0].getCell(Coords(0, 0)))
        assertEquals('#', boards[0].getCell(Coords(4, 0)))
        assertEquals('#', boards[0].getCell(Coords(1, 6)))

        assertEquals('.', boards[1].getCell(Coords(0, 0)))
        assertEquals('N', boards[1].getCell(Coords(4, 2)))
        assertEquals('E', boards[1].getCell(Coords(4, 1)))
    }

    @Test
    fun coordsAreOut() {
        assertEquals(true, boards[0].coordsAreOut(Coords(100, 100)))
        assertEquals(true, boards[0].coordsAreOut(Coords(0, 100)))
        assertEquals(true, boards[0].coordsAreOut(Coords(100, 0)))
        assertEquals(true, boards[0].coordsAreOut(Coords(-1, 0)))
        assertEquals(true, boards[0].coordsAreOut(Coords(0, -1)))
        assertEquals(false, boards[0].coordsAreOut(Coords(0, 0)))
    }
//
//    @Test
//    fun getGuardCoords() {
//        assertEquals(Coords(4, 6), mazes[0].getGuardCoords())
//        assertEquals(Coords(5, 3), mazes[1].getGuardCoords())
//    }
//
//    @Test
//    fun getCell() {
//        val maze = mazes[0]
//        assertEquals('.', maze.getCell(Coords(0, 0)))
//        assertEquals('#', maze.getCell(Coords(4, 0)))
//        assertEquals('^', maze.getCell(Coords(4, 6)))
//        assertEquals('#', maze.getCell(Coords(1, 6)))
//    }
//
//    @Test
//    fun getGuardDirection() {
//        assertEquals(Direction.NORTH, mazes[0].getGuardDirection())
//        assertEquals(Direction.EAST, mazes[1].getGuardDirection())
//        assertEquals(Direction.SOUTH, mazes[2].getGuardDirection())
//        assertEquals(Direction.WEST, mazes[3].getGuardDirection())
//    }
//
//    @Test
//    fun getGuard() {
//        assertEquals(Guard(Direction.NORTH, Coords(4, 6)), mazes[0].getGuard())
//        assertEquals(Guard(Direction.EAST, Coords(5, 3)), mazes[1].getGuard())
//        assertEquals(Guard(Direction.SOUTH, Coords(5, 3)), mazes[2].getGuard())
//        assertEquals(Guard(Direction.WEST, Coords(5, 3)), mazes[3].getGuard())
//    }
//
//    @Test
//    fun setVisited() {
//        assertEquals(
//            """
//                ....#.....
//                .........#
//                ..........
//                ..#.......
//                .......#..
//                ..........
//                .#..N.....
//                ........#.
//                #.........
//                ......#...
//            """.buildMaze(),
//            mazes[0].setVisited(Guard(Direction.NORTH, Coords(4, 6)))
//        )
//    }
//
//    @Test
//    fun setGuard() {
//        assertEquals(
//            """
//                ....#.....
//                .........#
//                ..........
//                ..#.......
//                .......#..
//                ....>.....
//                .#..X.....
//                ........#.
//                #.........
//                ......#...
//            """.buildMaze(),
//            """
//                ....#.....
//                .........#
//                ..........
//                ..#.......
//                .......#..
//                ..........
//                .#..X.....
//                ........#.
//                #.........
//                ......#...
//            """.buildMaze().setGuard(Guard(Direction.EAST, Coords(4, 5)))
//        )
//    }
//
//    @Test
//    fun setGuardWithCoordsOut() {
//        assertEquals(mazes[0], mazes[0].setGuard(Guard(Direction.EAST, Coords(-1, -1))))
//        assertEquals(mazes[0], mazes[0].setGuard(Guard(Direction.EAST, Coords(100, -1))))
//        assertEquals(mazes[0], mazes[0].setGuard(Guard(Direction.EAST, Coords(-1, 100))))
//        assertEquals(mazes[0], mazes[0].setGuard(Guard(Direction.EAST, Coords(100, 100))))
//    }
//
//    @Test
//    fun goStepWithNoBlock() {
//        assertEquals(
//            """
//                ....#.....
//                .........#
//                ..........
//                ..#.......
//                .......#..
//                ....^.....
//                .#..N.....
//                ........#.
//                #.........
//                ......#...
//            """.buildMaze(),
//            """
//                ....#.....
//                .........#
//                ..........
//                ..#.......
//                .......#..
//                ..........
//                .#..^.....
//                ........#.
//                #.........
//                ......#...
//            """.buildMaze().goStep()
//        )
//    }
//
//    @Test
//    fun goStepWithBlock() {
//        assertEquals(
//            """
//                ....#.....
//                .........#
//                ..........
//                ..#.......
//                .......#..
//                ....#.....
//                .#..>.....
//                ........#.
//                #.........
//                ......#...
//            """.buildMaze(),
//            """
//                ....#.....
//                .........#
//                ..........
//                ..#.......
//                .......#..
//                ....#.....
//                .#..^.....
//                ........#.
//                #.........
//                ......#...
//            """.buildMaze().goStep()
//        )
//    }
//
//    @Test
//    fun countVisited() {
//        assertEquals(
//            41,
//            """
//                ....#.....
//                ....EEEES#
//                ....N...S.
//                ..#.N...S.
//                ..EEEES#S.
//                ..N.N.S.S.
//                .#NWWWSWW.
//                .EEEEEES#.
//                #NWWWWWS..
//                ......#S..
//            """.buildMaze().countVisited()
//        )
//    }
//
//    @Test
//    fun setOfVisited() {
//        assertEquals(
//            setOf(
//                Coords(x = 4, y = 1),
//                Coords(x = 5, y = 1),
//                Coords(x = 6, y = 1),
//                Coords(x = 7, y = 1),
//                Coords(x = 8, y = 1),
//                Coords(x = 4, y = 2),
//                Coords(x = 8, y = 2),
//                Coords(x = 4, y = 3),
//                Coords(x = 8, y = 3),
//                Coords(x = 2, y = 4),
//                Coords(x = 3, y = 4),
//                Coords(x = 4, y = 4),
//                Coords(x = 5, y = 4),
//                Coords(x = 6, y = 4),
//                Coords(x = 8, y = 4),
//                Coords(x = 2, y = 5),
//                Coords(x = 4, y = 5),
//                Coords(x = 6, y = 5),
//                Coords(x = 8, y = 5),
//                Coords(x = 2, y = 6),
//                Coords(x = 3, y = 6),
//                Coords(x = 4, y = 6),
//                Coords(x = 5, y = 6),
//                Coords(x = 6, y = 6),
//                Coords(x = 7, y = 6),
//                Coords(x = 8, y = 6),
//                Coords(x = 1, y = 7),
//                Coords(x = 2, y = 7),
//                Coords(x = 3, y = 7),
//                Coords(x = 4, y = 7),
//                Coords(x = 5, y = 7),
//                Coords(x = 6, y = 7),
//                Coords(x = 7, y = 7),
//                Coords(x = 1, y = 8),
//                Coords(x = 2, y = 8),
//                Coords(x = 3, y = 8),
//                Coords(x = 4, y = 8),
//                Coords(x = 5, y = 8),
//                Coords(x = 6, y = 8),
//                Coords(x = 7, y = 8),
//                Coords(x = 7, y = 9)
//            ),
//            """
//                ....#.....
//                ....EEEES#
//                ....N...S.
//                ..#.N...S.
//                ..EEEES#S.
//                ..N.N.S.S.
//                .#NWWWSWW.
//                .EEEEEES#.
//                #NWWWWWS..
//                ......#S..
//            """.buildMaze().setOfVisited()
//        )
//    }
}
