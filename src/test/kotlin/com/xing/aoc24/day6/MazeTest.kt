package com.xing.aoc24.day6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MazeTest {
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
        """
            ....#....#
            .........#
            ..#.......
            .#..#v....
            ....#.....
            .......#..
            ..........
            ........#.
            #.........
            ......#...
        """,
        """
            ....#....#
            .........#
            ..#.......
            .#..#<....
            ....#.....
            .......#..
            ..........
            ........#.
            #.........
            ......#...
        """,
        """
            ....#....#
            .........#
            ..#.......
            .#..#.....
            ....#.....
            .......#..
            ..........
            ........#.
            #.........
            ......#...
        """,
    )

    val mazes: List<Maze> = rawMazes.map { Maze(it) }
    val drawedMazes: List<String> = listOf(
        "....#.....\n.........#\n..........\n..#.......\n.......#..\n..........\n.#..^.....\n........#.\n#.........\n......#...",
        "....#....#\n.........#\n..#.......\n.#..#>....\n....#.....\n.......#..\n..........\n........#.\n#.........\n......#...",
        "....#....#\n.........#\n..#.......\n.#..#v....\n....#.....\n.......#..\n..........\n........#.\n#.........\n......#...",
        "....#....#\n.........#\n..#.......\n.#..#<....\n....#.....\n.......#..\n..........\n........#.\n#.........\n......#...",
        "....#....#\n.........#\n..#.......\n.#..#.....\n....#.....\n.......#..\n..........\n........#.\n#.........\n......#...",
    )

    @BeforeEach
    fun setUp() {

    }

    @Test
    fun drawTest() {
        mazes.indices.forEach { idx ->
            assertEquals(drawedMazes[idx], mazes[idx].draw())
        }
    }

    @Test
    fun boardTest() {
        assertEquals(
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
        """.trimIndent(), mazes[0].board.draw()
        )
    }

    @Test
    fun guardTest() {
        val guards =
            listOf(
                Guard(Direction.NORTH, Coords(4, 6)),
                Guard(Direction.EAST, Coords(5, 3)),
                Guard(Direction.SOUTH, Coords(5, 3)),
                Guard(Direction.WEST, Coords(5, 3)),
                null
            )
        mazes.indices.forEach { idx ->
            assertEquals(guards[idx], mazes[idx].guard)
        }
    }

    @Test
    fun guardNextIsBlock() {
        assertEquals(listOf(false, false, false, true, false), mazes.map { it.guardNextIsBlock() })
    }

    @Test
    fun noGuard() {
        assertEquals(listOf(false, false, false, false, true), mazes.map { it.noGuard() })
    }

    @Test
    fun getCell() {
        assertEquals('.', mazes[0].getCell(Coords(0, 0)))
        assertEquals('#', mazes[0].getCell(Coords(4, 0)))
        assertEquals('#', mazes[0].getCell(Coords(1, 6)))
    }

    @Test
    fun setGuard() {
        assertEquals(
            Maze(
                Board(
                    """
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ..........
                .#..X.....
                ........#.
                #.........
                ......#...
                """
                ), Guard(direction = Direction.EAST, coords = Coords(x = 4, y = 5))
            ),
            Maze(
                """
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ..........
                .#..X.....
                ........#.
                #.........
                ......#...
            """
            ).setGuard(Guard(Direction.EAST, Coords(4, 5)))
        )
    }

    @Test
    fun setGuardWithCoordsOut() {
        val noGuardMaze = mazes[4]
        assertEquals(noGuardMaze, noGuardMaze.setGuard(Guard(Direction.EAST, Coords(-1, -1))))
        assertEquals(noGuardMaze, noGuardMaze.setGuard(Guard(Direction.EAST, Coords(100, -1))))
        assertEquals(noGuardMaze, noGuardMaze.setGuard(Guard(Direction.EAST, Coords(-1, 100))))
        assertEquals(noGuardMaze, noGuardMaze.setGuard(Guard(Direction.EAST, Coords(100, 100))))
    }

    @Test
    fun goStepWithBlock() {
        assertEquals(
            Maze(
                """
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ....#.....
                .#..>.....
                ........#.
                #.........
                ......#...
            """
            ),
            Maze(
                """
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ....#.....
                .#..^.....
                ........#.
                #.........
                ......#...
            """
            ).goStep()
        )
    }

    @Test
    fun goStepWithNoBlock() {
        assertEquals(
            Maze(
                """
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ....^.....
                .#..N.....
                ........#.
                #.........
                ......#...
            """
            ),
            Maze(
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
            """
            ).goStep()
        )
    }

    @Test
    fun goStepWithNoBlockOverInverseBreadCrumb() {
        assertEquals(
            Maze(
                """
                ....#.....
                .........#
                ..........
                ..#.......
                ....^..#..
                ....V.....
                .#..N.....
                ........#.
                #.........
                ......#...
            """
            ),
            Maze(
                """
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ....S.....
                .#..^.....
                ........#.
                #.........
                ......#...
            """
            ).goStep().goStep()
        )
    }

    @Test
    fun goSteps() {
        assertEquals(
            Maze(
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
            ),
            mazes[0].goSteps()
        )
    }

    @Test
    fun goStepsBouncing() {
        assertEquals(
            Maze(
                """
                ...#......
                ...S#.....
                ...V......
                WWWW......
                ...#......
            """
            ),
            Maze(
                """
                ...#......
                ....#.....
                ..........
                ...^......
                ...#......
            """
            ).goSteps()
        )
    }


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


//


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
//}
}