package com.xing.aoc24.day12

data class GardenMap(val map: Map<Coords, Plot> = emptyMap()) {
    constructor(rawMap: String) : this(
        rawMap.trimIndent().split("\n").flatMapIndexed { y, line ->
            line.toList().mapIndexed { x, c ->
                Coords(x, y) to c
            }
        }.toMap()
    )

    fun validCoords(x: Int, y: Int): Boolean = validCoords(Coords(x, y))
    private fun validCoords(coords: Coords): Boolean = map.keys.contains(coords)

    fun getSumOfPerimeterCosts(): Int {
        return getRegions().sumOf { region ->
            val perimeter = region.perimeter(this)
            val area = region.area()
            area * perimeter
        }
    }

    fun getCoords(): Set<Coords> {
        return map.keys
    }

    fun getRegions(): Set<Region> {
        val visited = mutableSetOf<Coords>()

        fun dfs(coords: Coords, plot: Plot): Region {
            if (getPlot(coords) != plot || visited.contains(coords)) {
                return Region(plot)
            }
            visited.add(coords)
            return Region(plot, setOf(coords) +
                    coords.steps().flatMap {
                        dfs(it, plot).coordsSet
                    })
        }

        return getCoords().map { coords ->
            dfs(coords, getPlot(coords)!!)
        }.filter { region -> region.isNotEmpty() }.toSet()
    }


    fun getPlot(coords: Coords) =
        map[coords]


}