package com.xing.aoc24.day15

enum class CellType(val char: Char) {
    WALL('#'), EMPTY('.'), BOX('O');

    companion object {
        fun fromChar(char: Char): CellType {
            return CellType.entries.find { it.char == char }!!
        }
    }
}