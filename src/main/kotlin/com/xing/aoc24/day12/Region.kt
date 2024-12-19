package com.xing.aoc24.day12

data class Region(val plot: Plot = ' ', val coordsSet: Set<Coords> = emptySet()) {
    fun isNotEmpty() = coordsSet.isNotEmpty()
    fun area() = coordsSet.size
    fun perimeter(gardenMap: GardenMap): Int =
        coordsSet.sumOf { it.numberOfVaults(gardenMap) }
}


