package com.xing.aoc24.day6


data class Board(val rows: List<Row>) {
    constructor(rawBoard: RawBoard) : this(rows = rawBoard.rows())

    fun draw(): String {
        val result = rows.fold("") { acc, row ->
            acc + (if (acc != "") "\n" else "") + row
        }
        return result
    }

    fun countVisited() = rows.sumOf { row -> row.count { cell -> cell in Direction.BREADCRUMBS } }
    fun width() = rows[0].length
    fun height() = rows.size

    fun getCell(coords: Coords): Cell =
        rows.getOrElse(coords.y) { Row() }.getOrElse(coords.x) { ' ' }

    fun coordsAreOut(coords: Coords): Boolean {
        return coords.x < 0 || coords.y < 0 ||
                coords.x >= this.width() || coords.y >= this.height()

    }

    fun setCell(cell: Cell, coords: Coords): Board =
        Board(rows.mapIndexed { idx, row ->
            if (idx == coords.y) {
                row.replaceAt(coords.x, cell)
            } else {
                row
            }
        })

    fun setOfVisited(): Set<Coords> {
        val result = rows.flatMapIndexed { y, row ->
            row.mapIndexedNotNull { x, it ->
                if (it in Direction.BREADCRUMBS) {
                    Coords(x, y)
                } else null
            }
        }.toSet()
        return result
    }
}
