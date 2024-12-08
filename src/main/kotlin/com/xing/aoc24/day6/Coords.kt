package com.xing.aoc24.day6

data class Coords(val x: Int, val y: Int) {
    fun goStep(direction: Direction) =
        when (direction) {
            Direction.NORTH -> copy(y = y - 1)
            Direction.EAST -> copy(x = x + 1)
            Direction.SOUTH -> copy(y = y + 1)
            Direction.WEST -> copy(x = x - 1)
        }
}

val notFoundCoords = Coords(-1, -1)