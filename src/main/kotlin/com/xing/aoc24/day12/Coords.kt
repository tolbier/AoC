package com.xing.aoc24.day12

data class Coords(val x: Int, val y: Int) {

    fun left() = Coords(x - 1, y)
    fun right() = Coords(x + 1, y)
    fun up() = Coords(x, y - 1)
    fun down() = Coords(x, y + 1)

    fun steps(): List<Coords> =
        listOf(left(), right(), up(), down())

    fun numberOfWalls(coordsSet: Set<Coords>): Int {
        return steps().filter { it !in coordsSet }.size
    }

    fun wallLeft(coordsSet: Set<Coords>) = left() !in coordsSet
    fun wallRight(coordsSet: Set<Coords>) = right() !in coordsSet
    fun wallUp(coordsSet: Set<Coords>) = up() !in coordsSet
    fun wallDown(coordsSet: Set<Coords>) = down() !in coordsSet
}