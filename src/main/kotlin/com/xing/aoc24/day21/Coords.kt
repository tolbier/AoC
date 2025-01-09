package com.xing.aoc24.day21

data class Coords(val x: Int = -1, val y: Int = -1) {
    fun west() = Coords(x - 1, y)
    fun east() = Coords(x + 1, y)
    fun north() = Coords(x, y - 1)
    fun south() = Coords(x, y + 1)
    fun next(direction: Direction): Coords =
        when (direction) {
            Direction.SOUTH -> south()
            Direction.NORTH -> north()
            Direction.EAST -> east()
            Direction.WEST -> west()
        }

    fun allNext() = Direction.entries.toList().map { next(it) }
    fun directionTo(toCoords: Coords): Direction {
        return when (toCoords) {
            west() -> Direction.WEST
            north() -> Direction.NORTH
            east() -> Direction.EAST
            south() -> Direction.SOUTH
            else -> null
        }!!
    }
}