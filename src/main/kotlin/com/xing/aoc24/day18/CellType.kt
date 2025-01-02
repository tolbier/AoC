package com.xing.aoc24.day18


enum class CellType(val char: Char) {
    CORRUPTED('#'), EMPTY('.');

    companion object {
        fun fromChar(char: Char): CellType? {
            return entries.firstOrNull { it.char == char }
        }
    }
}