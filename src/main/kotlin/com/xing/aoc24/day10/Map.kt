package com.xing.aoc24.day10

typealias Map = List<Row>

fun Map.getCell(coords: Coords): Cell? =
    this.getRow(coords.y)?.getCell(coords.x)

fun Map.getRow(y: Int): Row? =
    this.getOrNull(y)

fun Map.getCoordCells(): List<CoordCell> =
    this.flatMapIndexed { y, row ->
        row.mapIndexedNotNull { x, cell ->
            cell?.let {
                CoordCell(cell, Coords(x, y))
            }
        }
    }

fun Map.getCoordCell(coords: Coords): CoordCell? =
    getCell(coords)?.let { cell ->
        CoordCell(cell, coords)
    }

fun Map.getSumOfScores(): Int {
    val cells = getCoordCells()
    val result = cells.filter { it.cell == 0 }.sumOf { this.getHeads(it.coords).size }
    return result
}

fun Map.getSumOfRatings(): Int {
    val cells = getCoordCells()
    val result = cells.filter { it.cell == 0 }.sumOf { this.getRating(it.coords) }
    return result
}

fun Map.getRating(coords: Coords): Int {
    val coordCell = getCoordCell(coords) ?: return 0
    val cell = coordCell.cell
    return if (cell == 9) {
        1
    } else {
        steps(coordCell.coords)
            .sumOf { step ->
                getRating(step.coords)
            }
    }
}

fun Map.getHeads(coords: Coords): Set<CoordCell> {
    val coordCell = getCoordCell(coords) ?: return emptySet()
    val cell = coordCell.cell
    return if (cell == 9) {
        setOf(coordCell)
    } else {
        steps(coordCell.coords)
            .flatMap { step ->
                getHeads(step.coords)
            }.toSet()
    }
}

fun Map.steps(coords: Coords): List<CoordCell> {
    val cell = getCell(coords) ?: return emptyList()
    return coords.steps().mapNotNull { stepCoords ->
        val stepCell = this.getCell(stepCoords)
        stepCell?.let {
            CoordCell(stepCell, stepCoords).takeIf { stepCell == cell + 1 }
        }
    }
}

