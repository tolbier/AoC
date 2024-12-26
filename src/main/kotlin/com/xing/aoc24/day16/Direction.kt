package com.xing.aoc24.day16

enum class Direction(val char: Char) {

    NORTH('^') {
        override fun clockwise() = EAST
        override fun counterclockwise() = WEST
    },
    EAST('>') {
        override fun clockwise() = SOUTH
        override fun counterclockwise() = NORTH
    },
    SOUTH('v') {
        override fun clockwise() = WEST
        override fun counterclockwise() = EAST
    },
    WEST('<') {
        override fun clockwise() = NORTH
        override fun counterclockwise() = SOUTH
    };

    abstract fun clockwise(): Direction
    abstract fun counterclockwise(): Direction

    companion object {
        fun fromChar(char: Char): Direction {
            return entries.find { it.char == char }!!
        }
    }
}
