package com.xing.aoc24.day14

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RobotTest {

    val robots = listOf(
        Robot(coords = Coords(x = 0, y = 4), speed = Speed(vX = 3, vY = -3)),
        Robot(coords = Coords(x = 6, y = 3), speed = Speed(vX = -1, vY = -3)),
        Robot(coords = Coords(x = 10, y = 3), speed = Speed(vX = -1, vY = 2))
    )

    @Test
    fun predict() {
        assertEquals(Robot(Coords(3, 1), Speed(3, -3)), robots[0].predict(1, 11, 5))
        assertEquals(Robot(Coords(6, 3), Speed(3, -3)), robots[0].predict(2, 11, 5))
        assertEquals(Robot(Coords(9, 0), Speed(3, -3)), robots[0].predict(3, 11, 5))
        assertEquals(Robot(Coords(1, 2), Speed(3, -3)), robots[0].predict(4, 11, 5))
    }
}