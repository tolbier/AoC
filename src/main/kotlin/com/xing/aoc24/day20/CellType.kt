package com.xing.aoc24.day20


enum class CellType(val char: Char) {
    BLOCK('#'), EMPTY('.'), EXIT('E'), START('S');

    companion object {
        fun fromChar(char: Char): CellType? {
            return entries.firstOrNull { it.char == char }
        }
    }
}