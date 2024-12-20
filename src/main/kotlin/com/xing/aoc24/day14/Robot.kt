package com.xing.aoc24.day14

import com.xing.aoc24.day14.Coords.Quadrant

data class Robot(val coords: Coords, val speed: Speed) {
    fun predict(seconds: Int, width: Int, height: Int) =
        this.copy(
            coords = Coords(
                predictCoord(coords.x, speed.vX, seconds, width),
                predictCoord(coords.y, speed.vY, seconds, height)
            )
        )

    fun quadrant(width: Int, height: Int): Quadrant = coords.quadrant(width, height)

    private fun predictCoord(initCoord: Int, speed: Int, seconds: Int, size: Int) =
        (size + (initCoord + speed * seconds) % size) % size
}
