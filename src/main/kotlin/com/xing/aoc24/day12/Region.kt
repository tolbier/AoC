package com.xing.aoc24.day12

data class Region(val plot: Plot = ' ', val coordsSet: Set<Coords> = emptySet()) {
    fun isNotEmpty() = coordsSet.isNotEmpty()
    fun area() = coordsSet.size
    fun perimeter(): Int =
        coordsSet.sumOf { it.numberOfWalls(coordsSet) }

    fun numberOfSides(): Int =
        numberOfSides(Coords::wallUp, Coords::left, Coords::right) +
                numberOfSides(Coords::wallLeft, Coords::up, Coords::down) +
                numberOfSides(Coords::wallDown, Coords::left, Coords::right) +
                numberOfSides(Coords::wallRight, Coords::up, Coords::down)

    private fun numberOfSides(
        wallFunc: (Coords, Set<Coords>) -> Boolean,
        side1Func: (Coords) -> Coords,
        side2Func: (Coords) -> Coords
    ): Int {
        val visitedSet = mutableSetOf<Coords>()
        return coordsSet.count { coords ->
            var pointer = coords
            var isSide = false
            while (pointer !in visitedSet && pointer in coordsSet && wallFunc(pointer, coordsSet)) {
                isSide = true
                visitedSet.add(pointer)
                pointer = side1Func(pointer)
            }
            pointer = side2Func(coords)
            while (pointer !in visitedSet && pointer in coordsSet && wallFunc(pointer, coordsSet)) {
                isSide = true
                visitedSet.add(pointer)
                pointer = side2Func(pointer)
            }
            isSide
        }
    }
}


