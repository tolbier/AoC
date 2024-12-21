package com.xing.aoc24.day15

enum class Movement(val char: Char) {

    NORTH('^') {
    },
    EAST('>') {
    },
    SOUTH('v') {
    },
    WEST('<') {
    };

    companion object {
        fun fromChar(char: Char): Movement {
            return entries.find { it.char == char }!!
        }
    }
}

fun String.buildMovements(): List<Movement> =
    this.split("\n").flatMap { it.toList() }.map { c -> Movement.fromChar(c) }
