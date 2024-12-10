package com.xing.aoc24.day10

data class Coords(val x: Int, val y: Int)

fun Coords.steps(): List<Coords> =
    listOf(
        Coords(x - 1, y),
        Coords(x + 1, y),
        Coords(x, y - 1),
        Coords(x, y + 1),
    )
