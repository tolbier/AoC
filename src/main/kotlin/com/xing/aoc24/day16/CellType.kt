package com.xing.aoc24.day16


enum class CellType(val char: Char) {
    BLOCK('#'), EMPTY('.'), START('S'), EXIT('E'), ;

    companion object {
        fun fromChar(char: Char): CellType? {
            return entries.firstOrNull { it.char == char }
        }
    }
}