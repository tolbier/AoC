package com.xing.aoc24.day21

enum class Direction(val char: Char) {
    NORTH('^'),
    EAST('>'),
    SOUTH('v'),
    WEST('<');

    fun inverse(): Direction =
        when (this) {
            NORTH -> SOUTH
            SOUTH -> NORTH
            EAST -> WEST
            WEST -> EAST
        }
}
