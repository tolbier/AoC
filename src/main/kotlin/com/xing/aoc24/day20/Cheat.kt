package com.xing.aoc24.day20


data class Cheat(val from: Coords, val to: Coords) {
    fun pseconds() = from.psecondsFrom(to)
}

