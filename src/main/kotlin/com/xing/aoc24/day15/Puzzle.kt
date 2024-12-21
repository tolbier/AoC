package com.xing.aoc24.day15

import com.xing.aoc24.day15.CellType.BOX
import com.xing.aoc24.day15.CellType.EMPTY

data class Puzzle(val map: Map<Coords, CellType> = emptyMap(), val robot: Robot = Robot()) {
    val height = map.maxOf { it.key.y } + 1
    val width = map.maxOf { it.key.x } + 1

    fun sumAllBoxesGPSCoords(): Long =
        map.filterValues { it == BOX }.keys.sumOf { (x, y) ->
            y * 100L + x
        }

    fun order(movements: List<Movement>): Puzzle {
        return movements.fold(this)
        { lastPuzzle: Puzzle, movement ->
            lastPuzzle.order(movement)
        }
    }

    fun order(movement: Movement): Puzzle {
        //get last Cell(wall/null/empty) in the row moving in that direction
        // if lastCell is
        //   WALL/null -> do nothing
        //   EMPTY -> {
        //     put Box in lastCell
        //     put Empty in first nextCell
        //     move robotCoordinates to nextCell
        //    }
        val lastRowCell: Cell? = lastRowCell(movement)
        if (lastRowCell?.type != EMPTY) return this
        return this
            .setCell(lastRowCell.coords, BOX)
            .setCell(robot.coords.next(movement), EMPTY)
            .moveRobot(movement)

    }

    private fun lastRowCell(movement: Movement): Cell? {
        //starting from robot.coords
        var pointer = robot.coords
        var lastCell: Cell?
        do {
            pointer = pointer.next(movement)
            lastCell = getCell(pointer)
        } while (lastCell?.type == BOX)

        return lastCell
    }

    private fun moveRobot(movement: Movement) =
        copy(robot = robot.moveNext(movement))

    private fun setCell(coords: Coords, cellType: CellType): Puzzle =
        copy(map = map.plus(coords to cellType))


    private fun getCell(coords: Coords): Cell? = map.get(coords)?.let { Cell(coords, it) }

    fun draw(): String {
        var result = ""
        (0..<height).forEach { y ->
            (0..<width).forEach { x ->
                val coords = Coords(x, y)
                if (coords == robot.coords)
                    result += "@"
                else
                    result += getCell(coords)!!.type.char
            }
            result += "\n"
        }
        return result.trimIndent()
    }

    constructor(puzzle: Puzzle) : this(puzzle.map, puzzle.robot)
    constructor(rawPuzzle: String) : this(rawPuzzle.buildPuzzle())
}

fun String.buildPuzzle(): Puzzle {
    val puzzleMap = mutableMapOf<Coords, CellType>()
    var robotCoords: Coords? = null
    this.trimIndent().split("\n")
        .forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                val coords = Coords(x, y)
                if (c == '@') {
                    robotCoords = coords
                    puzzleMap.put(coords, EMPTY)
                } else {
                    puzzleMap.put(coords, CellType.fromChar(c))
                }
            }
        }
    return Puzzle(puzzleMap, Robot(robotCoords!!))
}

fun String.wider(): String =
    replace("#", "##")
        .replace("O", "[]")
        .replace(".", "..")
        .replace("@", "@.")
