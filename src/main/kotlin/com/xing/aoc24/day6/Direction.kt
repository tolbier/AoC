package com.xing.aoc24.day6


enum class Direction(val char: Char) {
    NORTH('^') {
        override fun goStep(coords: Coords) =
            coords.copy(y = coords.y - 1)

        override fun turn() = EAST
    },
    EAST('>') {
        override fun goStep(coords: Coords) =
            coords.copy(x = coords.x + 1)

        override fun turn() = SOUTH
    },
    SOUTH('v') {
        override fun goStep(coords: Coords) =
            coords.copy(y = coords.y + 1)

        override fun turn() = WEST
    },
    WEST('<') {
        override fun goStep(coords: Coords) =
            coords.copy(x = coords.x - 1)

        override fun turn() = NORTH
    };

    abstract fun goStep(coords: Coords): Coords
    abstract fun turn(): Direction
    fun getBreadcrumb(): Cell {
        return when (this) {
            WEST -> 'W'
            NORTH -> 'N'
            SOUTH -> 'S'
            EAST -> 'E'
        }
    }

    companion object {
        const val BREADCRUMBS: String = "NWSE"
        fun fromCell(char: Char): Direction {
            return values().find { it.char == char } ?: Direction.NORTH
        }
    }
}
