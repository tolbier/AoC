package com.xing.aoc24.day6


enum class Direction(val char: Char) {

    NORTH('^') {
        override fun repeatedCell(cellOver: Cell) = cellOver in "NV"
        override fun turn() = EAST
    },
    EAST('>') {
        override fun repeatedCell(cellOver: Cell) = cellOver in "EH"
        override fun turn() = SOUTH
    },
    SOUTH('v') {
        override fun repeatedCell(cellOver: Cell) = cellOver in "SV"
        override fun turn() = WEST
    },
    WEST('<') {
        override fun repeatedCell(cellOver: Cell) = cellOver in "WH"
        override fun turn() = NORTH
    };

    abstract fun repeatedCell(cellOver: Cell): Boolean

    abstract fun turn(): Direction
    fun getBreadcrumb(oldCell: Cell): Cell {
        return when (this) {
            WEST -> if (oldCell == 'E') 'H' else 'W'
            NORTH -> if (oldCell == 'S') 'V' else 'N'
            SOUTH -> if (oldCell == 'N') 'V' else 'S'
            EAST -> if (oldCell == 'W') 'H' else 'E'
        }
    }

    companion object {
        const val BREADCRUMBS: String = "NWSEVH"
        fun fromCell(char: Char): Direction? {
            return entries.find { it.char == char }
        }
    }
}
