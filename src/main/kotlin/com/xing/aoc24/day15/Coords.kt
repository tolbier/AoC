package com.xing.aoc24.day15


data class Coords(val x: Int = -1, val y: Int = -1) {
    fun left() = Coords(x - 1, y)
    fun right() = Coords(x + 1, y)
    fun up() = Coords(x, y - 1)
    fun down() = Coords(x, y + 1)
    fun next(movement: Movement): Coords =
        when (movement) {
            Movement.SOUTH -> down()
            Movement.NORTH -> up()
            Movement.EAST -> right()
            Movement.WEST -> left()
        }


    fun gpsCoords(): Long =
        100L * y + x
}

