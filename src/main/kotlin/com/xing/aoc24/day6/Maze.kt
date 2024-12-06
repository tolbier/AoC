package com.xing.aoc24.day6


typealias Maze = List<Row>

fun String.buildMaze(): Maze =
    this.trimIndent().split("\n")

fun Maze.draw(): String {
    val result = fold("") { acc, row ->
        acc + (if (acc != "") "\n" else "") + row
    }
    return result
}

fun Maze.getGuardDirection(): Direction {
    val guardCoords = getGuardCoords()
    val guardCell = this.getCell(guardCoords)
    return Direction.fromCell(guardCell)
}

fun Maze.getCell(coords: Coords): Cell =
    this.getOrElse(coords.y) { Row() }.getOrElse(coords.x) { ' ' }

fun Maze.goStep(): Maze {
    val guard = getGuard()

    return if (guard.nextIsBlock(this)) {
        setGuard(guard.turn())
    } else {
        setVisited(guard).setGuard(guard.moveNext())
    }
}

fun Maze.getGuard(): Guard {
    return Guard(getGuardDirection(), getGuardCoords())
}

fun Maze.countVisited() =
    this.sumOf { row -> row.count { cell -> cell in Direction.BREADCRUMBS } }

fun Maze.getGuardCoords(): Coords {
    forEachIndexed { y, row ->
        val x = row.indexOfFirst { "^>v<".contains(it) }
        if (x > -1) return Coords(x, y)
    }
    return notFoundCoords
}

fun Maze.width() = this[0].length
fun Maze.height() = this.size

fun Maze.guardCoordsAreOut(guard: Guard): Boolean {
    val coords = guard.coords
    return coords.x < 0 || coords.y < 0 ||
            coords.x >= this.width() || coords.y >= this.height()
}

fun Maze.setVisited(guard: Guard): Maze =
    setCell(guard.direction.getBreadcrumb(), guard.coords)


fun Maze.noGuard() =
    getGuardCoords() == notFoundCoords

fun Maze.setGuard(guard: Guard) =
    if (guardCoordsAreOut(guard)) {
        this
    } else {
        this.setCell(guard.direction.char, guard.coords)
    }

fun Maze.setOfVisited(): Set<Coords> {
    return setOf()

}

fun Maze.setCell(cell: Cell, coords: Coords): Maze =
    mapIndexed { idx, row ->
        if (idx == coords.y) {
            row.replaceAt(coords.x, cell)
        } else {
            row
        }
    }
