package com.xing.aoc24.day10

typealias CoordMap = Map<Coords, Cell>


fun CoordMap.getCoordCells(): List<CoordCell> =
    keys.mapNotNull { coord ->
        getCoordCell(coord)
    }

fun CoordMap.getCoordCell(coords: Coords): CoordCell? =
    get(coords)?.let {
        CoordCell(it, coords)
    }

fun CoordMap.getSumOf(f: (Coords) -> Int) =
    getCoordCells()
        .filter { it.cell == 0 }
        .map(CoordCell::coords)
        .sumOf(f)

fun CoordMap.getSumOfScores(): Int {
    return getSumOf(::scores)
}

fun CoordMap.getSumOfRatings(): Int {
    return getSumOf(::rating)
}

fun CoordMap.scores(coords: Coords): Int =
    getHeads(coords).size

fun CoordMap.rating(coords: Coords): Int =
    getRating(coords)


fun CoordMap.getRating(coords: Coords): Int {
    val coordCell = getCoordCell(coords) ?: return 0
    return if (coordCell.cell == 9) {
        1
    } else {
        steps(coordCell.coords)
            .sumOf { step ->
                getRating(step.coords)
            }
    }
}

fun CoordMap.getHeads(coords: Coords): Set<CoordCell> {
    val coordCell = getCoordCell(coords) ?: return emptySet()
    return if (coordCell.cell == 9) {
        setOf(coordCell)
    } else {
        steps(coordCell.coords)
            .flatMap { step ->
                getHeads(step.coords)
            }.toSet()
    }
}

fun CoordMap.steps(coords: Coords): List<CoordCell> {
    val cell = get(coords) ?: return emptyList()
    return coords.steps().mapNotNull { stepCoords ->
        val stepCell = get(stepCoords)
        stepCell?.let {
            CoordCell(stepCell, stepCoords).takeIf { stepCell == cell + 1 }
        }
    }
}

fun buildCoordMap(rawMap: String): CoordMap {
    return rawMap.trimIndent().split("\n").flatMapIndexed { y, line ->
        line.toList().mapIndexedNotNull { x, c ->
            try {
                Coords(x, y) to c.toString().toInt()
            } catch (e: Exception) {
                null
            }
        }
    }.toMap()
}