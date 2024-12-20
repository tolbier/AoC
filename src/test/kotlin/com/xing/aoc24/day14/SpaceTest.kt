package com.xing.aoc24.day14

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SpaceTest {
    val rawSpaces: List<String> = listOf(
        """
p=0,4 v=3,-3
p=6,3 v=-1,-3
p=10,3 v=-1,2
p=2,0 v=2,-1
p=0,0 v=1,3
p=3,0 v=-2,-2
p=7,6 v=-1,-3
p=3,0 v=-1,-2
p=9,3 v=2,3
p=7,3 v=-1,2
p=2,4 v=2,-3
p=9,5 v=-3,-3
        """,
        """
p=0,4 v=3,-3
p=6,3 v=-1,-3
p=10,3 v=-1,2
        """
    )

    val spaces = rawSpaces.map { buildSpace(it) }

    @Test
    fun buildSpace() {
        assertEquals(
            Space(
                11, 5, listOf(
                    Robot(coords = Coords(x = 0, y = 4), speed = Speed(vX = 3, vY = -3)),
                    Robot(coords = Coords(x = 6, y = 3), speed = Speed(vX = -1, vY = -3)),
                    Robot(coords = Coords(x = 10, y = 3), speed = Speed(vX = -1, vY = 2))
                )
            ), spaces[1]
        )
    }

    @Test
    fun predict() {
        assertEquals(
            Space(
                11, 5, listOf(
                    Robot(coords = Coords(x = 3, y = 1), speed = Speed(vX = 3, vY = -3)),
                    Robot(coords = Coords(x = 5, y = 0), speed = Speed(vX = -1, vY = -3)),
                    Robot(coords = Coords(x = 9, y = 0), speed = Speed(vX = -1, vY = 2))
                )
            ), spaces[1].predict(1)
        )

    }

    @Test
    fun safetyFactor() {
        assertEquals(
            12, spaces[0].predict(100).safetyFactor()
        )

    }
}