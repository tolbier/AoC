package com.xing.aoc24.day12

data class Coords(val x: Int, val y: Int) {

    fun steps(): List<Coords> =
        listOf(
            Coords(x - 1, y),
            Coords(x + 1, y),
            Coords(x, y - 1),
            Coords(x, y + 1),
        )

    fun numberOfVaults(gardenMap: GardenMap): Int {
        val plot = gardenMap.getPlot(this)
        return steps().filter { gardenMap.getPlot(it) != plot }.size
    }
}