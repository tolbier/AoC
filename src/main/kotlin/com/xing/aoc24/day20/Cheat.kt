package com.xing.aoc24.day20

import kotlin.math.abs

data class Cheat(val from: Coords, val to: Coords) {
    fun pseconds() = abs(from.x - to.x) + abs(from.y - to.y)
}

