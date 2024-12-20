package com.xing.aoc24.day14

enum class
data

data class Coords(val x: Int, val y: Int) {
    enum class Quadrant {
        UPPER_LEFT, UPPER_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, UNDETERMINED
    }

    fun quadrant(width: Int, height: Int): Quadrant {
        if (x == width / 2 || y == height / 2) return Quadrant.UNDETERMINED
        return if (x < width / 2) {
            if (y < height / 2) Quadrant.UPPER_LEFT else Quadrant.BOTTOM_LEFT
        } else {
            if (y < height / 2) Quadrant.UPPER_RIGHT else Quadrant.BOTTOM_RIGHT
        }
    }
}

