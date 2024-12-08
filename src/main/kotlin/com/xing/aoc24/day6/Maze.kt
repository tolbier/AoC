package com.xing.aoc24.day6


data class Maze(val board: Board, val guard: Guard?) {

    constructor(rawBoard: RawBoard) :
            this(Board(rawBoard.removeGuard()), Guard.fromRawBoard(rawBoard))


    fun draw(): String = board.draw().injectDrawGuard(guard)

    fun noGuard(): Boolean = guard == null

    fun getCell(coords: Coords): Cell = board.getCell(coords)
    fun guardNextIsBlock() = guard?.let { getCell(guard.nextCoords()) == '#' } ?: false
    fun countVisited() = board.countVisited()

    fun setGuard(guard: Guard): Maze {
        if (guard.direction.repeatedCell(getCell(guard.coords))) {
            throw CycleException()
        }
        return if (board.coordsAreOut(guard.coords)) {
            copy(guard = null)
        } else {
            copy(guard = guard)
        }
    }

    fun setCell(cell: Cell, coords: Coords) =
        Maze(board.setCell(cell, coords), guard)

    fun goStep(): Maze {
        if (guard == null) return this
        return if (guardNextIsBlock()) {
            setGuard(guard.turn())
        } else {
//            // before move, if nextCoords visited is the
//            // same letter of the guard direction
//            // then end search , and add a cycle
//            // launch exception? maybek

            setVisited(guard).setGuard(guard.moveNext())
        }
    }

    fun goSteps(debug: Boolean = false): Maze {
        if (noGuard()) return this
        if (debug) {
            println()
            println()
            println()
            println(draw())
        }
        return goStep().goSteps()
    }

    fun setVisited(guard: Guard): Maze {
        val guardCoords = guard.coords
        val oldCell = getCell(guardCoords)

        return setCell(guard.direction.getBreadcrumb(oldCell), guardCoords)
    }

    fun setOfVisited(): Set<Coords> = board.setOfVisited()
}

private fun String.injectDrawGuard(guard: Guard?): String {
    if (guard == null) return this
    return rows()
        .mapIndexed { y, row ->
            row.mapIndexed { x, cell ->
                if (Coords(x, y) == guard.coords) {
                    guard.direction.char
                } else {
                    cell
                }
            }.joinToString("")
        }.joinToString(separator = "\n")
}

private fun String.removeGuard(): String {
    val result = this.replace("[\\^v><]".toRegex(), ".")
    return result
}



